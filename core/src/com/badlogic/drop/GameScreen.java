package com.badlogic.drop;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen implements Screen {
	final Drop jogo;
	
	Reciclagem lixeiraAzul;
	Reciclagem lixeiraVerde;
	Reciclagem lixeiraMarrom;
	Reciclagem lixeiraVermelha;
	ListaReciclagem residuos;
	Reciclagem residuoAtual;
	Sound somAcerto;
	Sound somErro;

	OrthographicCamera camera;
	SpriteBatch batch;
	Vector3 touchPos;
	int pontos;
	int indice;
	
	public GameScreen(final Drop passed_game) {
		jogo = passed_game; 
		pontos = 0;

		lixeiraAzul = new Reciclagem(new Texture (Gdx.files.internal("lixeira_azul.png")), new Rectangle(), "papel");
		lixeiraVerde = new Reciclagem(new Texture (Gdx.files.internal("lixeira_verde.png")), new Rectangle(), "vidro");
		lixeiraMarrom = new Reciclagem(new Texture (Gdx.files.internal("lixeira_marrom.png")), new Rectangle(), "organico");
		lixeiraVermelha = new Reciclagem(new Texture (Gdx.files.internal("lixeira_vermelha.png")), new Rectangle(), "plastico");

		
		// Carrega os sons de acerto e erro
		somAcerto = Gdx.audio.newSound(Gdx.files.internal("resposta_certa.mp3"));
		somErro = Gdx.audio.newSound(Gdx.files.internal("resposta_errada.mp3"));
		
	
		// Inicializa câmera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		touchPos = new Vector3();
		
		batch = new SpriteBatch();
		
	
		// Cria os resíduos
		residuos = new ListaReciclagem();
		residuos.adicionarElementos();
		residuos.embaralhar();

		// Escolhe o primeiro e mostra na tela
		indice = 0;
		residuoAtual = residuos.elemento(indice);
		spawnResiduo(residuoAtual);
	}

	@Override
	public void render(float delta) {
		/* Clear screen with a dark blue color.
		 * Arguments to ClearColor are r g b, alpha
		 */
		Gdx.gl.glClearColor(0.95f, 0.8f, 0.6f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		
		jogo.batch.setProjectionMatrix(camera.combined);
		jogo.batch.begin();
		jogo.font.draw(jogo.batch, "Acertos: " + pontos + " / " + residuos.tamanho(),  0, 400);

		// Desenha as lixeiras
		jogo.batch.draw(lixeiraAzul.imagem, lixeiraAzul.objeto.x, lixeiraAzul.objeto.y, lixeiraAzul.objeto.width, lixeiraAzul.objeto.height);
		jogo.batch.draw(lixeiraVerde.imagem, lixeiraVerde.objeto.x, lixeiraVerde.objeto.y, lixeiraVerde.objeto.width, lixeiraVerde.objeto.height);
		jogo.batch.draw(lixeiraMarrom.imagem, lixeiraMarrom.objeto.x, lixeiraMarrom.objeto.y, lixeiraMarrom.objeto.width, lixeiraMarrom.objeto.height);
		jogo.batch.draw(lixeiraVermelha.imagem, lixeiraVermelha.objeto.x, lixeiraVermelha.objeto.y, lixeiraVermelha.objeto.width, lixeiraVermelha.objeto.height);

		// Desenha o resíduo atual
		jogo.batch.draw(residuoAtual.imagem, residuoAtual.objeto.x, residuoAtual.objeto.y, residuoAtual.objeto.width, residuoAtual.objeto.height);

		jogo.batch.end();
		
		// Processa movimento com o mouse
		if (Gdx.input.isTouched()) {
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			residuoAtual.objeto.x = touchPos.x - residuoAtual.objeto.width / 2;
		}
		
		// Processa movimento com o teclado
		if (Gdx.input.isKeyPressed(Keys.S)) 
			residuoAtual.objeto.x = 100;
		if (Gdx.input.isKeyPressed(Keys.D)) 
			residuoAtual.objeto.x = 200;
		if (Gdx.input.isKeyPressed(Keys.J))
			residuoAtual.objeto.x = 300;
		if (Gdx.input.isKeyPressed(Keys.K))
			residuoAtual.objeto.x = 400;


		// Impede que ultrapasse o limite da tela
		if (residuoAtual.objeto.x < 0) 
			residuoAtual.objeto.x = 0;
		if (residuoAtual.objeto.x > 800 - residuoAtual.objeto.width) 
			residuoAtual.objeto.x = 800 - residuoAtual.objeto.width;
		

		// Move as lixeiras para baixo
		lixeiraAzul.objeto.y -= 200 * Gdx.graphics.getDeltaTime();
		lixeiraVerde.objeto.y -= 200 * Gdx.graphics.getDeltaTime();
		lixeiraMarrom.objeto.y -= 200 * Gdx.graphics.getDeltaTime();
		lixeiraVermelha.objeto.y -= 200 * Gdx.graphics.getDeltaTime();

		// Verifica se chegou no final da tela
		if (lixeiraAzul.objeto.y < -64) {
			somErro.play();
			reposicionaLixeiras();
			indice++;
			residuoAtual = residuos.elemento(indice);
			spawnResiduo(residuoAtual);
		}

		// Verifica se acertou e lida com a colisão
		verificarELidarColisao(residuoAtual, lixeiraAzul);
		verificarELidarColisao(residuoAtual, lixeiraVerde);
		verificarELidarColisao(residuoAtual, lixeiraMarrom);
		verificarELidarColisao(residuoAtual, lixeiraVermelha);
	}

	private void reposicionaLixeiras() {
		lixeiraAzul.objeto.y = 400;
		lixeiraVerde.objeto.y = 400;
		lixeiraMarrom.objeto.y = 400;
		lixeiraVermelha.objeto.y = 400;
	}

	private void spawnResiduo(Reciclagem r) {
		r.objeto.x = 500;
		r.objeto.y = 40;
		r.objeto.width = 64;
		r.objeto.height = 64;
	}

	private void verificarELidarColisao(Reciclagem residuo, Reciclagem lixeira) {
		if (residuo.objeto.overlaps(lixeira.objeto)) {
			if (residuo.tipo.equals(lixeira.tipo)) {
				somAcerto.play();
				pontos++;
			}
			else {
				somErro.play();
			}
			
			// Remove da tela
			residuo.objeto.y -= 100;

			// Gera o próximo resíduo
			indice++;
			if (indice == residuos.tamanho()) {
				dispose();
				jogo.setScreen(new EndScreen(jogo, pontos));
				return;
			}
			reposicionaLixeiras();
			residuoAtual = residuos.elemento(indice);
			spawnResiduo(residuoAtual);
		}
	}

	@Override
	public void dispose() {
		// Clear all the "native" resources
		// Libera (alguma coisa)
		// lixeiraAzul.dispose();
		// lixeiraVerde.dispose();
		// lixeiraVermelha.dispose();
		// lixeiraMarrom.dispose();
		somAcerto.dispose();
		somErro.dispose();
		batch.dispose();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}

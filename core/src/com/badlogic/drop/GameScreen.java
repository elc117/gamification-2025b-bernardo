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
	
	Texture imagemVoltarMenu;
	Rectangle botaoVoltarMenu;

	Reciclagem lixeiraAzul;
	Reciclagem lixeiraVerde;
	Reciclagem lixeiraMarrom;
	Reciclagem lixeiraVermelha;
	Reciclagem lixeiraAmarela;
	ListaReciclagem residuos;
	Reciclagem residuoAtual;
	Sound somAcerto;
	Sound somErro;

	OrthographicCamera camera;
	SpriteBatch batch;
	Vector3 touchPos;
	int pontos;
	int indice;

	String desafio;
	boolean desafioAlcancado;
	int acertosSeguidos;
	int maiorAcerto;
	int desafioPapel;
	int desafioVidro;
	int desafioPlastico;
	int desafioOrganico;


	public GameScreen(final Drop passed_game, String desafio) {
		jogo = passed_game; 
		pontos = 0;
		this.desafio = desafio;

		// Inicializa atributos para validar os desafios
		acertosSeguidos = 0;
		maiorAcerto = 0;
		desafioPapel = 0;
		desafioVidro = 0;
		desafioPlastico = 0;
		desafioOrganico = 0;

		lixeiraAzul = new Reciclagem(new Texture (Gdx.files.internal("lixeira_azul.png")), new Rectangle(), "papel");
		lixeiraVerde = new Reciclagem(new Texture (Gdx.files.internal("lixeira_verde.png")), new Rectangle(), "vidro");
		lixeiraMarrom = new Reciclagem(new Texture (Gdx.files.internal("lixeira_marrom.png")), new Rectangle(), "organico");
		lixeiraVermelha = new Reciclagem(new Texture (Gdx.files.internal("lixeira_vermelha.png")), new Rectangle(), "plastico");
		lixeiraAmarela = new Reciclagem(new Texture (Gdx.files.internal("lixeira_amarela.png")), new Rectangle(), "metal");
		
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
		jogo.batch.draw(lixeiraAmarela.imagem, lixeiraAmarela.objeto.x, lixeiraAmarela.objeto.y, lixeiraAmarela.objeto.width, lixeiraAmarela.objeto.height);
		jogo.batch.draw(imagemVoltarMenu, botaoVoltarMenu.x, botaoVoltarMenu.y, botaoVoltarMenu.width, botaoVoltarMenu.height);


		// Desenha o resíduo atual
		jogo.batch.draw(residuoAtual.imagem, residuoAtual.objeto.x, residuoAtual.objeto.y, residuoAtual.objeto.width, residuoAtual.objeto.height);

		jogo.batch.end();
		
		// Processa movimento com o mouse
		if (Gdx.input.isTouched()) {
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			residuoAtual.objeto.x = touchPos.x - residuoAtual.objeto.width / 2;

			// Verifica se clicou no botão para voltar ao menu
			if (botaoVoltarMenu.contains(touchPos.x, touchPos.y)) {
				jogo.setScreen(new MainMenuScreen(jogo));
			}

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
		if (Gdx.input.isKeyPressed(Keys.L))
			residuoAtual.objeto.x = 500;


		// Impede que ultrapasse o limite da tela
		if (residuoAtual.objeto.x < 0) 
			residuoAtual.objeto.x = 0;
		if (residuoAtual.objeto.x > 800 - residuoAtual.objeto.width) 
			residuoAtual.objeto.x = 800 - residuoAtual.objeto.width;
		

		// Move as lixeiras para baixo
		lixeiraAzul.objeto.y -= 170 * Gdx.graphics.getDeltaTime();
		lixeiraVerde.objeto.y -= 170 * Gdx.graphics.getDeltaTime();
		lixeiraMarrom.objeto.y -= 170 * Gdx.graphics.getDeltaTime();
		lixeiraVermelha.objeto.y -= 170 * Gdx.graphics.getDeltaTime();
		lixeiraAmarela.objeto.y -= 170 * Gdx.graphics.getDeltaTime();

		if (proximoResiduo() == 1) {
			indice++;
			if (indice == residuos.tamanho()) {
				pontos = (int) ((pontos * 100) / residuos.tamanho());
				desafioAlcancado = verificarDesafio();
				jogo.setScreen(new EndScreen(jogo, pontos, desafio, desafioAlcancado));
				return;
			}
			reposicionaLixeiras();
			residuoAtual = residuos.elemento(indice);
			spawnResiduo(residuoAtual);
		}

	}

	private boolean verificarDesafio() {
		if (desafio.equals("Acertar 5 resíduos seguidos") && maiorAcerto >= 5) return true;
		if (desafio.equals("Acertar 10 resíduos seguidos") && maiorAcerto >= 10) return true;
		if (desafio.equals("Acertar 15 resíduos seguidos") && maiorAcerto >= 15) return true;

		if (desafio.equals("Acertar todos resíduos do tipo papel") && desafioPapel == 4) return true;
		if (desafio.equals("Acertar todos resíduos do tipo vidro") && desafioVidro == 4) return true;
		if (desafio.equals("Acertar todos resíduos do tipo plastico") && desafioPlastico == 5) return true;
		if (desafio.equals("Acertar todos resíduos do tipo orgânico") && desafioOrganico == 5) return true;

		if (desafio.equals("Obter pontuação maior que 50%") && pontos > 50) return true;
		if (desafio.equals("Obter pontuação maior que 70%") && pontos > 70) return true;
		if (desafio.equals("Obter pontuação maior que 90%") && pontos > 90) return true;

		return false;

	}

	private int proximoResiduo() {
		if (lixeiraAzul.objeto.y < -64) {
			somErro.play();
			return 1;
		}

		if (verificarELidarColisao(residuoAtual, lixeiraAzul) == 1 || verificarELidarColisao(residuoAtual, lixeiraVerde) == 1 || verificarELidarColisao(residuoAtual, lixeiraMarrom) == 1 || verificarELidarColisao(residuoAtual, lixeiraVermelha) == 1 || verificarELidarColisao(residuoAtual, lixeiraAmarela) == 1) {
			return 1;
		}

		return 0;
	}

	private void reposicionaLixeiras() {
		lixeiraAzul.objeto.y = 400;
		lixeiraVerde.objeto.y = 400;
		lixeiraMarrom.objeto.y = 400;
		lixeiraVermelha.objeto.y = 400;
		lixeiraAmarela.objeto.y = 400;
	}

	private void spawnResiduo(Reciclagem r) {
		r.objeto.x = 600;
		r.objeto.y = 40;
		r.objeto.width = 64;
		r.objeto.height = 64;
	}

	private int verificarELidarColisao(Reciclagem residuo, Reciclagem lixeira) {
		if (residuo.objeto.overlaps(lixeira.objeto)) {
			if (residuo.tipo.equals(lixeira.tipo)) {

				acertosSeguidos++;
				if (acertosSeguidos > maiorAcerto) {
					maiorAcerto = acertosSeguidos;
				}
				if (residuo.tipo.equals("papel")) desafioPapel++;
				else if (residuo.tipo.equals("plastico")) desafioPlastico++;
				else if (residuo.tipo.equals("organico")) desafioOrganico++;
				else if (residuo.tipo.equals("vidro")) desafioVidro++;

				somAcerto.play();
				pontos++;
			}
			else {
				acertosSeguidos = 0;
				somErro.play();
			}
			return 1;
		}
		return 0;
	}

	@Override
	public void dispose() {
		lixeiraAzul.imagem.dispose();
		lixeiraVerde.imagem.dispose();
		lixeiraVermelha.imagem.dispose();
		lixeiraMarrom.imagem.dispose();
		lixeiraAmarela.imagem.dispose();
		somAcerto.dispose();
		somErro.dispose();

		for (int i = 0; i < residuos.tamanho(); i++) {
			residuos.elemento(i).imagem.dispose();
		}

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		imagemVoltarMenu = new Texture(Gdx.files.internal("img_voltar_menu.png"));
		botaoVoltarMenu = new Rectangle(600, 400, 200, 80);
		
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

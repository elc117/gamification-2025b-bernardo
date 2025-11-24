package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;


public class MainMenuScreen implements Screen {
	final Drop jogo;
	static private int WIDTH = 800;
	static private int HEIGHT = 480;
	ListaDesafios lista;
	String desafioAtual;

	Texture imagemTutorial;
	Rectangle botaoJogar;

	Texture imagemJogar;
	Rectangle botaoTutorial;

	Texture imagemDesafio;
	Rectangle botaoDesafio;

	Texture imagemPrincipal;
	
	OrthographicCamera camera;
	
	public MainMenuScreen(final Drop passed_game) {
		jogo = passed_game;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WIDTH, HEIGHT);

		lista = new ListaDesafios();
		lista.adicionarElementos();
		gerarDesafioAleatorio();

	}
	
	private void gerarDesafioAleatorio() {
		lista.embaralhar();
		desafioAtual = lista.elemento(0);
	} 

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.6f, 0.8f, 0.6f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		jogo.batch.setProjectionMatrix(camera.combined);
		
		jogo.batch.begin();
		jogo.batch.draw(imagemPrincipal, 300, 380, 200, 80);
		jogo.batch.draw(imagemJogar, botaoJogar.x, botaoJogar.y, botaoJogar.width, botaoJogar.height);
		jogo.batch.draw(imagemTutorial, botaoTutorial.x, botaoTutorial.y, botaoTutorial.width, botaoTutorial.height);
		jogo.font.setColor(0, 0, 0, 1);	// preto
		jogo.font.draw(jogo.batch, "Desafio: " + desafioAtual, 120, 100);
		jogo.batch.draw(imagemDesafio, botaoDesafio.x, botaoDesafio.y, botaoDesafio.width, botaoDesafio.height);
		jogo.batch.end();
		
		// Verifica se uma das opções foi clicada
		if (Gdx.input.isTouched()) {
			// Pega a posição do clique
			Vector3 posicao = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(posicao);

			if (botaoJogar.contains(posicao.x, posicao.y)) {
				jogo.setScreen(new GameScreen(jogo, desafioAtual));
			}

			if (botaoTutorial.contains(posicao.x, posicao.y)) {
				jogo.setScreen(new TutorialScreen(jogo));
			}

			if (botaoDesafio.contains(posicao.x, posicao.y)) {
				gerarDesafioAleatorio();
			}

		}

	}


	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		imagemJogar = new Texture(Gdx.files.internal("img_jogar.png"));
		botaoJogar = new Rectangle(300, 300, 200, 80);

		imagemTutorial = new Texture(Gdx.files.internal("img_tutorial.png"));
		botaoTutorial = new Rectangle(300, 200, 200, 80);
		
		imagemDesafio = new Texture(Gdx.files.internal("img_gerar_desafio.png"));
		botaoDesafio = new Rectangle(500, 80, 100, 40);

		imagemPrincipal = new Texture(Gdx.files.internal("img_mensagem_principal.png"));

	}

	@Override
	public void dispose() {
		imagemTutorial.dispose();
		imagemJogar.dispose();
		imagemPrincipal.dispose();
		imagemDesafio.dispose();
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
}

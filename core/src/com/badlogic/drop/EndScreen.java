package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class EndScreen implements Screen {
    final Drop jogo;

	float pontuacaoFinal;
	private int WIDTH = 800;
	private int HEIGHT = 480;
	
	OrthographicCamera camera;
	
	Texture imagemVoltarMenu;
	Rectangle botaoVoltarMenu;

	public EndScreen(final Drop passed_game, int acertos, int total) {
		jogo = passed_game;
		pontuacaoFinal = ((float) acertos / (float) total) * 100;
		pontuacaoFinal = (int) pontuacaoFinal;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WIDTH, HEIGHT);
	}
	
	@Override
	public void render(float delta) {
        Gdx.gl.glClearColor(0.6f, 0.8f, 0.6f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		jogo.batch.setProjectionMatrix(camera.combined);
		
		jogo.batch.begin();
		jogo.font.draw(jogo.batch, "Pontuação final: " + pontuacaoFinal + "%", 100, 400);
		jogo.batch.draw(imagemVoltarMenu, botaoVoltarMenu.x, botaoVoltarMenu.y, botaoVoltarMenu.width, botaoVoltarMenu.height);
		jogo.batch.end();

		// Verifica se o botão foi clicado
		if (Gdx.input.isTouched()) {
			Vector3 posicao = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(posicao);

			if (botaoVoltarMenu.contains(posicao.x, posicao.y)) {
				jogo.setScreen(new MainMenuScreen(jogo));
			}
		}

	}


	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		imagemVoltarMenu = new Texture(Gdx.files.internal("img_voltar_menu.png"));
		botaoVoltarMenu = new Rectangle(100, 100, 200, 80);
		
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
	public void dispose() {
		imagemVoltarMenu.dispose();
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
}

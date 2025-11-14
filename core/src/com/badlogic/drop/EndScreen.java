package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class EndScreen implements Screen {
    final Drop jogo;

	int pontuacaoFinal;
	static private int WIDTH = 800;
	static private int HEIGHT = 480;
	
	OrthographicCamera camera;

	public EndScreen(final Drop passed_game, int pontuacao) {
		jogo = passed_game;
		pontuacaoFinal = pontuacao;
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
		jogo.font.draw(jogo.batch, "Pontuação final: " + pontuacaoFinal + " / " + residuos.tamanho(), 100, 400);
		jogo.font.draw(jogo.batch, "Clique para jogar novamente", 100, 300);
		jogo.batch.end();

		// If player activates the game, dispose of this menu.
		if (Gdx.input.isTouched()) {
			jogo.setScreen(new GameScreen(jogo));
			dispose();
		}

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
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
}

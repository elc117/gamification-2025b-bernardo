package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainMenuScreen implements Screen {
	final Drop jogo;
	static private int WIDTH = 800;
	static private int HEIGHT = 480;
	
	OrthographicCamera camera;
	
	public MainMenuScreen(final Drop passed_game) {
		jogo = passed_game;
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
		jogo.font.draw(jogo.batch, "Clique em qualquer lugar para jogar", 100, 300);
		jogo.font.draw(jogo.batch, "Use o mouse ou as teclas S, D, J, K para mover o resíduo até a lixeira correta", 100, 200);
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

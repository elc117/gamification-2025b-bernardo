package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

class TutorialScreen implements Screen {
    final Drop jogo;

    private int WIDTH = 800;
    private int HEIGHT = 480;

    OrthographicCamera camera;

    public TutorialScreen(final Drop passed_game) {
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
		jogo.font.draw(jogo.batch, "Lixeira azul: papel\nLixeira verde: vidro\nLixeira marrom: orgânico\nLixeira vermelha: plástico", 100, 400);
		jogo.font.draw(jogo.batch, "Para mover use as teclas S, D, J e K ou clique com o mouse no lugar desejado", 100, 300);
        jogo.font.draw(jogo.batch, "Clique em qualquer lugar para voltar para a tela inicial", 100, 200);
		jogo.batch.end();

		// Ao clicar com o mouse volta pra tela inicial
		if (Gdx.input.isTouched()) {
			jogo.setScreen(new MainMenuScreen(jogo));
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
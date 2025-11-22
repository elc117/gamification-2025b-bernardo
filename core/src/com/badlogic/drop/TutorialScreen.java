package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;


class TutorialScreen implements Screen {
    final Drop jogo;

    private int WIDTH = 800;
    private int HEIGHT = 480;

    OrthographicCamera camera;

	Texture imagemVoltar;
	Rectangle botaoVoltar;

	Texture imagemMensagem;
	Texture imagemLixeiraAzul;
	Texture imagemLixeiraMarrom;
	Texture imagemLixeiraVerde;
	Texture imagemLixeiraVermelha;

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
		jogo.batch.draw(imagemLixeiraAzul, 100, 300, 80, 80);
		jogo.batch.draw(imagemLixeiraVerde, 200, 300, 80, 80);
		jogo.batch.draw(imagemLixeiraMarrom, 300, 300, 80, 80);
		jogo.batch.draw(imagemLixeiraVermelha, 400, 300, 80, 80);

		jogo.batch.draw(imagemMensagem, 100, 200, 400, 80);
        jogo.batch.draw(imagemVoltar, botaoVoltar.x, botaoVoltar.y, botaoVoltar.width, botaoVoltar.height);
		jogo.batch.end();

		// Verifica se o botão foi clicado
		if (Gdx.input.isTouched()) {
			Vector3 posicao = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(posicao);

			if (botaoVoltar.contains(posicao.x, posicao.y)) {
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
		imagemVoltar = new Texture(Gdx.files.internal("img_voltar.png"));
		botaoVoltar = new Rectangle(100, 100, 200, 80);

		imagemMensagem = new Texture(Gdx.files.internal("img_mensagem.png"));
		
		imagemLixeiraAzul = new Texture(Gdx.files.internal("lixeira_azul.png"));
		imagemLixeiraVermelha = new Texture(Gdx.files.internal("lixeira_vermelha.png"));
		imagemLixeiraVerde = new Texture(Gdx.files.internal("lixeira_verde.png"));
		imagemLixeiraMarrom = new Texture(Gdx.files.internal("lixeira_marrom.png"));
	}

	@Override
	public void dispose() {
		imagemVoltar.dispose();
	 	imagemMensagem.dispose();
	 	imagemLixeiraAzul.dispose();
	 	imagemLixeiraMarrom.dispose();
	 	imagemLixeiraVerde.dispose();
	 	imagemLixeiraVermelha.dispose();
		
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
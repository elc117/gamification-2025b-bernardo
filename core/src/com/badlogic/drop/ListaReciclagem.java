package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class ListaReciclagem {
    Array<Reciclagem> lista;


    public ListaReciclagem() {
        lista = new Array<>();

    }

    // Métodos
    public void adicionarElementos() {
        lista.add(new Reciclagem(new Texture (Gdx.files.internal("caixa_papelao.png")), new Rectangle(), "papel", 500, 40));
        lista.add(new Reciclagem(new Texture (Gdx.files.internal("papel.png")), new Rectangle(), "papel", 500, 40));
        lista.add(new Reciclagem(new Texture (Gdx.files.internal("revista.png")), new Rectangle(), "papel", 500, 40));

        lista.add(new Reciclagem(new Texture (Gdx.files.internal("copo_quebrado.png")), new Rectangle(), "vidro", 500, 40));
        lista.add(new Reciclagem(new Texture (Gdx.files.internal("garrafa_vidro.png")), new Rectangle(), "vidro", 500, 40));
        lista.add(new Reciclagem(new Texture (Gdx.files.internal("pote_vidro.png")), new Rectangle(), "vidro", 500, 40));


        lista.add(new Reciclagem(new Texture (Gdx.files.internal("maca.png")), new Rectangle(), "organico", 500, 40));
        lista.add(new Reciclagem(new Texture (Gdx.files.internal("ovo.png")), new Rectangle(), "organico", 500, 40));
        lista.add(new Reciclagem(new Texture (Gdx.files.internal("folha.png")), new Rectangle(), "organico", 500, 40));


        lista.add(new Reciclagem(new Texture (Gdx.files.internal("tampa_garrafa.png")), new Rectangle(), "plastico", 500, 40));
        lista.add(new Reciclagem(new Texture (Gdx.files.internal("garrafa_pet.png")), new Rectangle(), "plastico", 500, 40));
        lista.add(new Reciclagem(new Texture (Gdx.files.internal("copo_cafe.png")), new Rectangle(), "plastico", 500, 40));
        // lista.add(new Reciclagem(new Texture (Gdx.files.internal("embalagem.png")), new Rectangle(), "plastico", 500, 40));
        lista.add(new Reciclagem(new Texture (Gdx.files.internal("sacola_plastico.png")), new Rectangle(), "plastico", 500, 40));


    }

    public void embaralhar() {
        lista.shuffle();
    }

    public Reciclagem elemento(int indice) {
        return lista.get(indice);
    }

    public int tamanho() {
        return lista.size;
    }

}
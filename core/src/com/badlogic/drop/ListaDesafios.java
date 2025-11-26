package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;

public class ListaDesafios extends Lista<String> {

    @Override
    public void adicionarElementos() {
        lista.add("Acertar 5 resíduos seguidos");
        lista.add("Acertar 10 resíduos seguidos");
        lista.add("Acertar 15 resíduos seguidos");

        lista.add("Acertar todos resíduos do tipo papel");
        lista.add("Acertar todos resíduos do tipo vidro");
        lista.add("Acertar todos resíduos do tipo plástico");
        lista.add("Acertar todos resíduos do tipo orgânico");

        lista.add("Obter pontuação maior que 70%");
        lista.add("Obter pontuação maior que 90%");
        lista.add("Obter pontuação maior que 50%");

    }
    
}
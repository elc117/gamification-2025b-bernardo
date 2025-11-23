package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

abstract class Lista<T> {
    Array<T> lista = new Array<>();

    public void embaralhar() {
        lista.shuffle();
    }

    public T elemento(int indice) {
        return lista.get(indice);
    }

    public int tamanho() {
        return lista.size;
    }

    public abstract void adicionarElementos();
}
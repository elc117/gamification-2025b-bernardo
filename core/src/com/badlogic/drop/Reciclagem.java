package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;


public class Reciclagem {
    Texture imagem;
    Rectangle objeto;
    String tipo;

    public Reciclagem(Texture imagem, Rectangle objeto, String tipo) {
        this.imagem = imagem;
        this.objeto = objeto;
        this.tipo = tipo;

        this.objeto.width = 64;
        this.objeto.height = 64;
        this.objeto.y = 400;

        if (tipo.equals("papel")) {
            this.objeto.x = 100;
        }
        else if (tipo.equals("vidro")) {
            this.objeto.x = 200;
        }
        else if (tipo.equals("organico")) {
            this.objeto.x = 300;
        }
        else if (tipo.equals("plastico")) {
            this.objeto.x = 400;
        }

    }

    public Reciclagem(Texture imagem, Rectangle objeto, String tipo, float x, float y) {
        this.imagem = imagem;
        this.objeto = objeto;
        this.tipo = tipo;

        this.objeto.width = 64;
        this.objeto.height = 64;
        this.objeto.x = x;
        this.objeto.y = y;
    }


}
package com.mygdx.pipopipette.block;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.pipopipette.GameScreen;

public class HollowSquare extends Block{


    public HollowSquare(float x, float y) {
        this.dx=400;
        this.dy=400;
        this.rectangle = new Rectangle(x, y, dx, dy);
        this.x = x;
        this.y = y;
        this.sprite= new Sprite();

    }

    public void clicSquare() {
        if(Gdx.input.isTouched()){
            /*
            rectangle.contains permet de savoir si le point que l'on indique appartient au rectangle
            Gdx.input.get renvoie automatiquement la coordonnée X/Y sur laquelle on clique.
            le facteur 3.75 vient du fait que la résolution de notre écran est de 800 pixels, mais
            le camera.setOrtho() a été mis à 3000 (parce que les sprite sont trop gros).
            3000 divisé par 800 = 3.75
            sur la coordonnée Y, j'ai mis 3000-Gdx.input.getY() car pour une raison obscure,
            cette coordonnée est inversée par rapport à la coordonnée X
             */
            if(this.rectangle.contains(Gdx.input.getX()*3.75f, 3000f-Gdx.input.getY()*3.75f)){
                this.drawBlock(GameScreen.camera, GameScreen.spriteBatch, "red_square.png");
            }
        }
    }

}

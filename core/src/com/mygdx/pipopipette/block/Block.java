package com.mygdx.pipopipette.block;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.pipopipette.GameScreen;
import com.mygdx.pipopipette.PlayerManager;

public class Block {
    public Sprite sprite;
    public Rectangle rectangle;
    public float x;
    public float y;
    public float dx;
    public float dy;
    public boolean isFree;

    public Block(float x, float y) {
        this.x = x;
        this.y = y;
        sprite = new Sprite();
    }

    public void clickBlock(String texture) {
        //rectangle.contains permet de savoir si le point que l'on indique appartient au rectangle
        //Gdx.input.get renvoie automatiquement la coordonnée X/Y sur laquelle on clique.
        //le facteur 3.75 vient du fait que la résolution de notre écran est de 800 pixels, mais
        //le camera.setOrtho() a été mis à 3000 (parce que les sprite sont trop gros).
        //3000 divisé par 800 = 3.75
        //sur la coordonnée Y, j'ai mis 3000-Gdx.input.getY() car pour une raison obscure,
        //cette coordonnée est inversée par rapport à la coordonnée X
            if(this.rectangle.contains(Gdx.input.getX()*3.75f, 3000f-Gdx.input.getY()*3.75f) && isFree){
                isFree=false;
                System.out.println(isFree);
                PlayerManager.setCoup(GameScreen.players);
                this.drawBlock(texture);
            }

    }
    public void drawBlock(String texture) { // Drawn the sprite pass in argument.
        GameScreen.camera.update();
        sprite = new Sprite(new Texture(Gdx.files.internal(texture)));
        GameScreen.spriteBatch.begin();
        GameScreen.spriteBatch.draw(sprite,x,y,0,0,dx,dy,1,1,0);
        GameScreen.spriteBatch.end();
    }

}

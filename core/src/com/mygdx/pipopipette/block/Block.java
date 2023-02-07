package com.mygdx.pipopipette.block;

import static com.mygdx.pipopipette.GameScreen.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
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
    }

    public void clickBlock(String texture) {
        //rectangle.contains permet de savoir si le point que l'on indique appartient au rectangle
        //Gdx.input.get renvoie automatiquement la coordonnée X/Y sur laquelle on clique.

            Vector3 touchPos = new Vector3();                              //Création d'un vecteur à 3 coordonnées x,y,z
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);        // On récupère les coordonnées de touché
            camera.unproject(touchPos);                                    // On adapte les coordonnées à la camera
            if(this.rectangle.contains(touchPos.x, touchPos.y) && isFree){ // On test si l'endroit toucher est un rectangle et s'il est libre
                isFree=false;
                System.out.println(isFree);
                PlayerManager.setCoup(GameScreen.players);
                this.sprite = new Sprite(new Texture(Gdx.files.internal(texture)));
                this.drawBlock();
            }

    }
    public void drawBlock() {
        camera.update();
        GameScreen.spriteBatch.begin();
        GameScreen.spriteBatch.draw(this.sprite,x,y,0,0,dx,dy,1,1,0);
        GameScreen.spriteBatch.end();
    }

}

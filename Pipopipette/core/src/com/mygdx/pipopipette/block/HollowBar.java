package com.mygdx.pipopipette.block;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.pipopipette.GameScreen;

public class HollowBar extends Block {
    public float rotation;

    public HollowBar(float rotation, float x, float y){
        this.dx=500;
        this.dy=96;
        this.rectangle = new Rectangle(x, y, dx, dy);
        //La rotation semble assez inutile, il faudra la virer plus tard. Quand tu dessines un
        //sprite avec une certaine rotation (méthode batch.draw()), ça ne modifie pas la hitbox
        //du rectangle. Du coup, on a le sprite qui est tourné de 90° par rapport à la hitbox
        //qui n'a pas bougé. Et je n'ai pas trouvé de paramètre "rotation" pour le rectangle.
        //A la place j'invers la largeur et la longueur du rectangle en fonction de la rotation
        //que l'on met en argument
        this.rotation = rotation;
        if (rotation==90) {
            this.rectangle.setSize(96,500);
            this.dx=96;
            this.dy=500;
        }
        this.x = x;
        this.y = y;
        this.sprite = new Sprite();
    }

    public void clicBar() {
        if(Gdx.input.isTouched()){
            //rectangle.contains permet de savoir si le point que l'on indique appartient au rectangle
            //Gdx.input.get renvoie automatiquement la coordonnée X/Y sur laquelle on clique.
            //le facteur 3.75 vient du fait que la résolution de notre écran est de 800 pixels, mais
            //le camera.setOrtho() a été mis à 3000 (parce que les sprite sont trop gros).
            //3000 divisé par 800 = 3.75
            //sur la coordonnée Y, j'ai mis 3000-Gdx.input.getY() car pour une raison obscure,
            //cette coordonnée est inversée par rapport à la coordonnée X
            if(this.rectangle.contains(Gdx.input.getX()*3.75f, 3000f-Gdx.input.getY()*3.75f)){
                this.drawBlock(GameScreen.camera, GameScreen.spriteBatch, "red_bar.png");
            }
        }
    }



}

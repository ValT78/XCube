package com.mygdx.pipopipette.block;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class HollowBar {
    public Rectangle rectangle;
    public float rotation;
    public float x;
    public float y;
    public Sprite sprite;
    public float dx;
    public float dy;

    private SpriteBatch batch;


    public HollowBar(float rotation, float x, float y){
        this.dx=500;
        this.dy=96;
        this.rectangle = new Rectangle(x, y, dx, dy);
        this.rotation = rotation;
        if (rotation==90) {
            this.rectangle.setSize(96,500);
            this.dx=96;
            this.dy=500;
        }
        this.x = x;
        this.y = y;
        this.sprite = new Sprite(new Texture(Gdx.files.internal("grey_bar.png")));
    }

    public void clicBar(Camera camera, SpriteBatch batch ) {

        if(Gdx.input.isTouched()){

            System.out.println(Gdx.input.getX()+"gg"+ Gdx.input.getY());
            if(this.rectangle.contains(Gdx.input.getX()*3.75f, 3000f-Gdx.input.getY()*3.75f)){
                camera.update();
                batch.begin();
                this.sprite.setTexture(new Texture(Gdx.files.internal("red_bar.png")));
                batch.draw(this.sprite,this.x,this.y,0,0,this.dx,this.dy,1,1,0);
                batch.end();
            }
        }
    }



}

package com.mygdx.pipopipette.block;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class HollowBar {
    public Rectangle rectangle;
    public float rotation;
    public float x;
    public float y;
    public Sprite sprite;
    public float dx;
    public float dy;



    public HollowBar(float rotation, float x, float y){
        this.dx=500;
        this.dy=96;
        this.rectangle = new Rectangle(x, y, dx, dy);
        this.rotation = rotation;
        this.x = x;
        this.y = y;
        this.sprite = new Sprite(new Texture(Gdx.files.internal("grey_bar.png")));

    }

    public void clicBar() {
        if(Gdx.input.isTouched()){
            if(this.rectangle.contains(Gdx.input.getX(), Gdx.input.getY())){
                this.sprite = new Sprite(new Texture(Gdx.files.internal("red_bar.png")));
                System.out.println("ggggggggggggggg");
            }
        }
    }
}

package com.mygdx.pipopipette.block;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class HollowBlock {
    public Rectangle rectangle;
    public float x;
    public float y;
    public Sprite sprite;
    public float dx;
    public float dy;

    public HollowBlock(float x, float y) {
        this.dx=400;
        this.dy=400;
        this.rectangle = new Rectangle(x, y, dx, dy);
        this.x = x;
        this.y = y;
        this.sprite = new Sprite(new Texture(Gdx.files.internal("grey_block.png")));

    }

}

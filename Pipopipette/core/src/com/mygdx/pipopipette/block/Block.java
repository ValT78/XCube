package com.mygdx.pipopipette.block;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.pipopipette.GameScreen;

public class Block {
    public Sprite sprite;
    public Rectangle rectangle;
    public float x;
    public float y;
    public float dx;
    public float dy;

    public void drawBlock(Camera camera, SpriteBatch spriteBatch, String texture) {  // Drawn the sprite pass in argument.
        this.sprite = new Sprite(new Texture(Gdx.files.internal(texture)));
        camera.update();
        spriteBatch.begin();
        spriteBatch.draw(this.sprite,this.x,this.y,0,0,this.dx,this.dy,1,1,0);
        spriteBatch.end();
    }
}

package com.mygdx.xcube.block;

import static com.badlogic.gdx.math.MathUtils.round;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.xcube.XCube;

public class Button extends Block {
    private final String text;

    public Button(int x, int y, String sprite, String text) {
        this.x=x;
        this.y=y;
        this.sprite = new Sprite(new Texture(Gdx.files.internal(sprite)));
        this.dx=round(this.sprite.getWidth());
        this.dy=round(this.sprite.getHeight());
        this.rectangle = new Rectangle(x-dy,y,dy,dx);
        this.text=text;
    }

    public void drawButton(XCube game) {
        game.batch.draw(sprite,x,y,0,0,dx,dy,1,1,90);

        game.font.draw(game.batch, text,x-dy*15/16,y+dx*3/4);
    }

    public boolean contains(float x, float y) {
        return this.rectangle.contains(x,y);
    }
}

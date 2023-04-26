package com.mygdx.xcube.block;

import static com.badlogic.gdx.math.MathUtils.round;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.xcube.XCube;

public class Items extends Block {
    public Items(int x, int y, String sprite) {
        this.x=x;
        this.y=y;
        this.sprite = new Sprite(new Texture(Gdx.files.internal(sprite)));
        this.dx=round(this.sprite.getWidth());
        this.dy=round(this.sprite.getHeight());
        this.rectangle = new Rectangle(x,y,dy,dx);
        //System.out.println(dx);
        //System.out.println(dy);

    }
    public void drawItems(XCube game, float scale) {
        game.batch.draw(sprite,x,y,0,0,dx,dy,scale,scale,0);
    }
    public void resize(int x, int y){
         this.dx = x;
         this.dy = y;
    }
}

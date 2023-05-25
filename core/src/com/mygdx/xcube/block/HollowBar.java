package com.mygdx.xcube.block;

import static com.badlogic.gdx.math.MathUtils.round;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class HollowBar extends TerrainBlock {

    public HollowBar(boolean rotation, int x, int y){
        super(x,y);
        this.sprite = setSprite("greybarv2.png");

        dx=round(this.getSprite().getWidth());
        dy=round(this.getSprite().getHeight());
        rectangle = new Rectangle(x-dx*2, y+dy/4, dx*5, dy/2);
        if (rotation) {
            rectangle = new Rectangle(x+dy/4, y-dx*2, dy/2, dx*5);
            dx = dy;
            dy = round(this.getSprite().getWidth());
        }
        isSquare=false;
        this.spritebleu = setSprite("V2/bluebar1.png");
        this.spritebleu2 = setSprite("V2/bluebar2.png");
        this.spriterouge = setSprite("V2/redbar1.png");
        this.spriterouge2 = setSprite("V2/redbar2.png");

    }

}

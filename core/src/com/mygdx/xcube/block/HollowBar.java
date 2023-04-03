package com.mygdx.xcube.block;

import static com.badlogic.gdx.math.MathUtils.round;

import com.badlogic.gdx.math.Rectangle;

public class HollowBar extends TerrainBlock {

    public HollowBar(boolean rotation, int x, int y){
        super(x,y);
        this.setSprite("grey_bar.png");
        dx=round(this.getSprite().getWidth());
        dy=round(this.getSprite().getHeight());
        rectangle = new Rectangle(x, y, dx, dy);
        if (rotation) {
            rectangle.setSize(dy, dx);
            dx = dy;
            dy = round(this.getSprite().getWidth());
        }
        isSquare=false;
    }

}

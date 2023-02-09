package com.mygdx.xcube.block;

import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class HollowSquare extends Block{
    public ArrayList<HollowBar> neighbors = new ArrayList<>();
    public HollowSquare(float x, float y) {
        super(x,y);
        dx=400;
        dy=400;
        rectangle = new Rectangle(x, y, dx, dy);
        isFree=false;

    }

    //public HollowBar[] FindNeighbors(Array<HollowBar> bar, int i) {
    //    this.neighbors.add();
    //}

}

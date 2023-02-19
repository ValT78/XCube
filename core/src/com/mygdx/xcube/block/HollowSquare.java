package com.mygdx.xcube.block;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class HollowSquare extends Block{
    public HollowSquare[] vertical; //contient les blocs en haut et en bas de ce bloc
    public HollowSquare[] horizontal;
    public HollowSquare[] diagonal;
    public HollowSquare[] antidiagonal;
    public boolean isHorizontal; //Vaut true si les blocs à droite et à gauche existent
    public boolean isVertical;
    public boolean isDiagonal;
    public boolean isAntidiagonal;
    public HollowSquare(int x, int y) {
        super(x,y);
        this.vertical=new HollowSquare[2];
        this.horizontal= new HollowSquare[2];
        this.diagonal = new HollowSquare[2];
        this.antidiagonal = new HollowSquare[2];
        dx=400;
        dy=400;
        rectangle = new Rectangle(x, y, dx, dy);
        isSquare=true;
        isHorizontal=true;
        isVertical=true;
        isDiagonal=true;
        isAntidiagonal=true;
        this.setSprite("grey_square.png");

    }
    public int[] getCoords() {
        int[] tab = {x,y};

        return tab;
    } //retourne les coordonnées d'un carré


}

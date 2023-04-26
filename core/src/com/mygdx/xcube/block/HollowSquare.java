package com.mygdx.xcube.block;

import static com.badlogic.gdx.math.MathUtils.round;

import com.badlogic.gdx.math.Rectangle;

public class HollowSquare extends TerrainBlock {
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
        this.setSprite("V2/4case.png");
        this.vertical=new HollowSquare[2];
        this.horizontal= new HollowSquare[2];
        this.diagonal = new HollowSquare[2];
        this.antidiagonal = new HollowSquare[2];
        dx=round(this.getSprite().getWidth()/4);
        dy=round(this.getSprite().getHeight()/4);
        rectangle = new Rectangle(x, y, dx, dy);
        isSquare=true;
        isHorizontal=true;
        isVertical=true;
        isDiagonal=true;
        isAntidiagonal=true;

    }
    public int[] getCoords() {
        int[] tab = {x,y};

        return tab;
    } //retourne les coordonnées d'un carré


}

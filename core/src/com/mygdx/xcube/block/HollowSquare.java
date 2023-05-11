package com.mygdx.xcube.block;

import static com.badlogic.gdx.math.MathUtils.round;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

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

    public void addNeighbors(Array<HollowBar> bars) {
        for (HollowBar b: bars) {
            //System.out.println(this.x+"position carré"+this.y);
            //System.out.println(this.x - b.x-b.dy/2-this.dx/2);
            //System.out.println(this.y - b.y+b.dy/2-this.dy/2);
            if (Math.abs(this.y - (b.y-this.dy/2+b.dx/2+b.dy)) < 2 && Math.abs(this.x - (b.x-this.dx/2+b.dx/2)) < 2) {
                this.neighbors.add(b);
            }
            else if (Math.abs(this.y - (b.y-this.dy/2-b.dx/2)) < 2 && Math.abs(this.x - (b.x+b.dx/2-this.dx/2)) < 2 ) {
                this.neighbors.add(b);
            }
            else if (Math.abs(this.y - (b.y+b.dy/2-this.dy/2)) < 2  && Math.abs(this.x - (b.x+b.dy/2-this.dx/2+b.dx)) < 2 ) {
                this.neighbors.add(b);
            }
            else if (Math.abs(this.y - (b.y+b.dy/2-this.dy/2)) < 2  && Math.abs(this.x - (b.x-b.dy/2-this.dx/2)) < 2 ) {
                this.neighbors.add(b);
            }
        }
    }


}

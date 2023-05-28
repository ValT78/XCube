package com.mygdx.xcube.block;

import static com.badlogic.gdx.math.MathUtils.round;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.xcube.Terrain;

import java.util.ArrayList;

public class HollowSquare extends TerrainBlock {
    public HollowSquare[] vertical; //contient les blocs en haut et en bas de ce bloc
    public HollowSquare[] horizontal;
    public HollowSquare[] diagonal;
    public HollowSquare[] antidiagonal;
    public boolean isHorizontal; //Vaut true si les blocs à droite et à gauche existent
    public boolean isVertical;
    public boolean isDiagonal;
    public boolean isAntidiagonal;
    public ArrayList<HollowBar> neighbors;
    public int freeNeighbors;
    public HollowSquare(int x, int y) {
        super(x,y);
        this.sprite = setSprite("V2/4case.png");
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
        this.neighbors = new ArrayList<>();
        this.freeNeighbors=4;
        this.spritebleu = setSprite("V2/bluecross1.png");
        this.spritebleu2 = setSprite("V2/bluecross2.png");
        this.spriterouge = setSprite("V2/redcross1.png");
        this.spriterouge2 = setSprite("V2/redcross2.png");

    }

    public boolean FillNeighbors() {
        for (int i=0; i<neighbors.size(); i++) {
            if(neighbors.get(i).isFree) {
                return false;
            }
        }
        return true;
    }

    public void addNeighbors(Array<HollowBar> bars) { //Ajoute les 4 barres autour du carré dans cet ordre : bas, haut, gauche, droite
        for (HollowBar b: bars) {
            if (Math.abs(this.y - (b.y+b.dy/2-this.dy/2)) < 2  && Math.abs(this.x - (b.x-b.dy/2-this.dx/2)) < 2 ) {
                this.neighbors.add(b);
            }
            else if (Math.abs(this.y - (b.y+b.dy/2-this.dy/2)) < 2  && Math.abs(this.x - (b.x+b.dy/2-this.dx/2+b.dx)) < 2 ) {
                this.neighbors.add(b);
            }
            else if (Math.abs(this.y - (b.y-this.dy/2-b.dx/2)) < 2 && Math.abs(this.x - (b.x+b.dx/2-this.dx/2)) < 2 ) {
                this.neighbors.add(b);
            }
            if (Math.abs(this.y - (b.y-this.dy/2+b.dx/2+b.dy)) < 2 && Math.abs(this.x - (b.x-this.dx/2+b.dx/2)) < 2) {
                this.neighbors.add(b);
            }
        }
    }

    public void HaveNeighborsLite() {
        if(isFree) {
                int numberFree = 0;
                for(int i =0; i<4;i++) {
                    if(neighbors.get(i).isFree) {
                        numberFree++;
                    }
                }
                freeNeighbors = numberFree;

        }
        else {
            freeNeighbors = 0;
        }
    }

    public int ComputeLine2(HollowSquare[] neighbors, boolean color) { //color = true si IA vient de jouer
        HaveNeighborsLite();
        neighbors[0].HaveNeighborsLite();
        neighbors[1].HaveNeighborsLite();

        if(freeNeighbors==4) {
            if (neighbors[0].freeNeighbors>3 || neighbors[1].freeNeighbors>3){ // |_| ° ¤
                return -200;
            }
            return -4;
        }
        else if(freeNeighbors==3) {
            if (neighbors[0].freeNeighbors>3 || neighbors[1].freeNeighbors>3){ // |_| _ ¤
                return -200;
            }
            return -10;
        }
        else if(freeNeighbors==2) {
            if(!neighbors[0].isFree && !neighbors[1].isFree) {
                if(neighbors[0].isBlue==color && neighbors[1].isBlue==color) { // X |_ X
                    return -325;
                }
                else if(neighbors[0].isBlue!=color && neighbors[1].isBlue!=color) { // O |_ O
                    return 275;
                }
                else { // X |_ O
                    return 0;
                }
            }
            else if((!neighbors[0].isFree && neighbors[0].isBlue == color && neighbors[1].freeNeighbors<2) || (!neighbors[1].isFree && neighbors[1].isBlue == color && neighbors[0].freeNeighbors<2)) { // X |_ |_|
                return -200;
            }
            else if((!neighbors[0].isFree && neighbors[0].isBlue == color) || (!neighbors[1].isFree && neighbors[1].isBlue == color)) { // X |_ |_
                return -40;
            }
            else if(!neighbors[0].isFree && neighbors[1].freeNeighbors<2 || (!neighbors[1].isFree && neighbors[0].freeNeighbors<2)) { // O |_ |_|
                return 150;
            }
            else if(!neighbors[0].isFree || (!neighbors[1].isFree)) { // O |_ |_
                return 30;
            }
            else if (neighbors[0].freeNeighbors>3 || neighbors[1].freeNeighbors>3){ // |_| |_ ¤
                return -40;
            }
            else { // |_ |_ |_
                return 5;
            }
        }
        else if(isFree && freeNeighbors<2) {
            if(!neighbors[0].isFree && !neighbors[1].isFree) {
                if(neighbors[0].isBlue==color && neighbors[1].isBlue==color) { // X |_| X
                    return -1500;
                }
                else if(neighbors[0].isBlue!=color && neighbors[1].isBlue!=color) { // O |_| O
                    return 375;
                }
                else { // X |_| O
                    return 0;
                }
            }
            else if((!neighbors[0].isFree && neighbors[0].isBlue == color) || (!neighbors[1].isFree && neighbors[1].isBlue == color)) { // X |_| ¤
                return -200;
            }
            else if(!neighbors[0].isFree || !neighbors[1].isFree) { // O |_| ¤
                return -150;
            }
            else { // ¤ |_| ¤
                return -200;
            }
        }
        else {
            if(isBlue) {
                if(!neighbors[0].isFree && !neighbors[1].isFree) {
                    if(neighbors[0].isBlue!=color && neighbors[1].isBlue!=color) { // O X O
                        return 0;
                    }
                    else { // X X O
                        return -10;
                    }
                }
                if((!neighbors[0].isFree && neighbors[0].isBlue != color && neighbors[1].freeNeighbors<2) || (!neighbors[1].isFree && neighbors[1].isBlue != color && neighbors[1].freeNeighbors<2)) { // O X |_|
                    return -200;
                }
                else if(!neighbors[0].isFree && neighbors[0].isBlue != color || !neighbors[1].isFree && neighbors[1].isBlue != color) { // O X |_
                    return -80;
                }
                else if(!neighbors[0].isFree && neighbors[1].freeNeighbors > 2 || !neighbors[1].isFree && neighbors[0].freeNeighbors > 2) { // X X |_|
                    return -1500;
                }
                else if(!neighbors[0].isFree || !neighbors[1].isFree) { // X X |_
                    return -350;
                }
                else if(neighbors[0].freeNeighbors<2 || neighbors[1].freeNeighbors<2) { // |_| X ¤
                    return -200;
                }
                else { // |_ X ¤
                    return -40;
                }
            }
            else {
                if(!neighbors[0].isFree && !neighbors[1].isFree) {
                    if(neighbors[0].isBlue==color && neighbors[1].isBlue==color) { // X O X
                        return 50;
                    }
                    else { // X O O
                        return 10;
                    }
                }
                else if((!neighbors[0].isFree && neighbors[0].isBlue == color && neighbors[1].freeNeighbors<2) || (!neighbors[1].isFree && neighbors[1].isBlue == color && neighbors[1].freeNeighbors<2)) { // X O |_|
                    return -50;
                }
                else if((!neighbors[0].isFree && neighbors[0].isBlue == color) || (!neighbors[1].isFree && neighbors[1].isBlue == color)) { // X O |_
                    return 70;
                }
                else if((!neighbors[0].isFree && neighbors[1].freeNeighbors>2) || (!neighbors[1].isFree && neighbors[0].freeNeighbors>2)) { // O O |_|
                    return 700;
                }
                else if(!neighbors[0].isFree || !neighbors[1].isFree) { // O O |_
                    return 300;
                }
                else if(neighbors[0].freeNeighbors<2 || neighbors[1].freeNeighbors<2) { // |_| O ¤
                    return 150;
                }
                else { // |_ O ¤
                    return 30;
                }
            }
        }
    }

        public int ComputeLine(HollowSquare[] neighbors) {
            HaveNeighborsLite();
            neighbors[0].HaveNeighborsLite();
            neighbors[1].HaveNeighborsLite();
            if(freeNeighbors==4) {
                return -60;
            }
        else if(freeNeighbors==3) { // |
            if(neighbors[0].isFree && neighbors[0].freeNeighbors==2 && neighbors[1].isFree && neighbors[1].freeNeighbors==2) { // |_ | |_
                return 0;
            }
            else if((neighbors[0].isFree && neighbors[0].freeNeighbors==3) || (neighbors[1].isFree && neighbors[1].freeNeighbors==3)) { // |_| | ¤
                return -7;
            }
        }
        else if(neighbors[0].isBlue!=neighbors[1].isBlue && !neighbors[0].isFree && !neighbors[1].isFree) { // X ? O
            return -10;
        }
        else if(freeNeighbors==2) {
            /*if((neighbors[0].isFree && neighbors[0].freeNeighbors==3) || (neighbors[1].isFree && neighbors[1].freeNeighbors==3)) { // |_| |_ ¤
                return 20;
            }*/
            if(neighbors[0].isFree || neighbors[1].isFree) {
                if ((neighbors[0].freeNeighbors == 1 && !neighbors[1].isFree && neighbors[1].isBlue) || (neighbors[1].freeNeighbors == 1 && !neighbors[0].isFree && neighbors[0].isBlue)) { // X |_ |_|
                    return -20;
                } else if ((neighbors[0].freeNeighbors == 2 && !neighbors[1].isFree && neighbors[1].isBlue) || (neighbors[1].freeNeighbors == 2 && !neighbors[0].isFree && neighbors[0].isBlue)) { // X |_ |_
                    return -6;
                } else if ((neighbors[0].freeNeighbors == 1 && !neighbors[1].isFree && !neighbors[1].isBlue) || (neighbors[1].freeNeighbors == 1 && !neighbors[0].isFree && !neighbors[0].isBlue)) { // O |_ |_|
                    return 14;
                } else if ((neighbors[0].freeNeighbors == 2 && !neighbors[1].isFree && !neighbors[1].isBlue) || (neighbors[1].freeNeighbors == 2 && !neighbors[0].isFree && !neighbors[0].isBlue)) { // O |_ |_
                    return 4;
                }
            }
            else if(neighbors[0].isBlue==neighbors[1].isBlue) {
                if(neighbors[0].isBlue) { // X |_ X
                    return -60;
                }
                else { // O |_ O
                    return 40;
                }
            }
        }
        else if(freeNeighbors<2 && isFree) {
            if(neighbors[0].isFree && neighbors[1].isFree) { // ¤ |_| ¤
                return -15;
            }
            else if((neighbors[0].isFree && neighbors[1].isBlue) || (neighbors[1].isFree && neighbors[0].isBlue)) { // X |_| ¤
                return -17;
            }
            else if(neighbors[0].isFree || neighbors[1].isFree) { // O |_| ¤
                return 8;
            }
            else {
                if(neighbors[0].isBlue) { // X |_| X
                    return -300;
                }
                else { // O |_| O
                    return 100;
                }
            }
        }
        else {
            if((!neighbors[0].isFree && isBlue!=neighbors[0].isBlue) && (!neighbors[1].isFree && isBlue!=neighbors[1].isBlue)) { // X O X ou O X O
                return 0;
            }
            if(isBlue) {
                if(neighbors[0].isFree && neighbors[1].isFree) {
                    if(neighbors[0].freeNeighbors<2 || neighbors[1].freeNeighbors<2) { // |_| X ¤
                        return -17;
                    }
                    else { // |_ X |_
                        return -8;
                    }
                }
                else if((neighbors[0].isFree && !neighbors[1].isBlue) || (neighbors[1].isFree && !neighbors[0].isBlue)) { // O X ¤
                    return -60;
                }
                else if((neighbors[0].isFree && neighbors[1].isBlue) || (neighbors[1].isFree && neighbors[0].isBlue)) {
                    if(neighbors[0].freeNeighbors<2 || neighbors[1].freeNeighbors<2) { // X X |_|
                        return -300;
                    }
                    else if(neighbors[0].freeNeighbors>1 || neighbors[1].freeNeighbors>1){ // X X |_
                        return  -45;
                    }
                }
                else if(neighbors[0].isBlue!=neighbors[1].isBlue) { // X X O
                    return -12;
                }
            }
            if(!isBlue) {
                if(neighbors[0].isFree && neighbors[1].isFree) {
                    if(neighbors[0].freeNeighbors<2 || neighbors[1].freeNeighbors<2) { // |_| O ¤
                        return 13;
                    }
                    else { // |_ O |_
                        return 6;
                    }
                }
                else if((neighbors[0].isFree && neighbors[1].isBlue) || (neighbors[1].isFree && neighbors[0].isBlue)) { // X O ¤
                    return 45;
                }
                else if((neighbors[0].isFree && !neighbors[1].isBlue) || (neighbors[1].isFree && !neighbors[0].isBlue)) {
                    if(neighbors[0].freeNeighbors<2 || neighbors[1].freeNeighbors<2) { // O O |_|
                        return 100;
                    }
                    else if(neighbors[0].freeNeighbors>1 || neighbors[1].freeNeighbors>1){ // O O |_
                        return  30;
                    }
                }
                else if(neighbors[0].isBlue!=neighbors[1].isBlue) { // X O O
                    return 8;
                }
            }
        }
    return 0;
    }

}

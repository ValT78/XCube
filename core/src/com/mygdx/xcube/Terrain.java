package com.mygdx.xcube;

import com.badlogic.gdx.utils.Array;
import com.mygdx.xcube.block.TerrainBlock;
import com.mygdx.xcube.block.HollowBar;
import com.mygdx.xcube.block.HollowSquare;

public class Terrain {
    private final Array<HollowBar> bar;
    private final Array<HollowSquare> square;
    private final int spaceBlock;
    private final int unitX;
    private final int unitY;
    private final int unitSquare;
    private final int originX;
    private final int originY;
    private Array<TerrainBlock> lastPlay;


    public Terrain() {
        this.unitSquare = new HollowSquare(0,0).getSize()[0];
        this.unitX = new HollowBar(false,0,0).getSize()[0];
        this.unitY = new HollowBar(false,0,0).getSize()[1];
        this.spaceBlock=unitX + unitY;
        this.originX = 3*unitY/2;
        this.originY = (9*unitY + 9*unitX)/2;
        this.bar = generateBar();           // Stock la liste des barres
        this.square=generateSquare();       // Stock la liste de carrés
        this.lastPlay=new Array<>();
    }
    public Array<TerrainBlock> getLastPlay() {
        return lastPlay;
    }
    public Array<HollowBar> getBar() {
        return bar;
    }                //Permet d'appeler les barres
    public Array<HollowSquare> getSquare() {
        return square;
    }       // Permet d'appeler les carrés
    public Array<HollowBar>  generateBar() {

        Array<HollowBar> bar = new Array<>();
        int x = originX + unitX;
        int y = originY;

        for(int i=0; i<5; i++){
            for(int k=0; k<4; k++ ){
                HollowBar b= new HollowBar(true,x,y);
                x += spaceBlock;
                bar.add(b);
            }
            x = originX + unitX;
            y += spaceBlock;

        }

        x = originX;
        y = originY + unitX;

        for(int i=0; i<4; i++) {
            for (int k = 0; k < 5; k++) {
                HollowBar b = new HollowBar(false,x,y);

                bar.add(b);

                x += spaceBlock;
            }
            x = originX;
            y += spaceBlock;
        }
        return bar;
    }
    public Array<HollowSquare> generateSquare() { //construit une grille de carré et leur attribut les barres qui les entourent
        Array<HollowSquare> squares = new Array<>();

        int x = originX+unitX+unitY/2-unitSquare/2;
        int y = originY+unitX+unitY/2-unitSquare/2;

        for(int i=0; i<4; i++) {
            for (int j = 0; j < 4; j++) {
                HollowSquare square1 = new HollowSquare(x, y);
                square1.addNeighbors(getBar());
                squares.add(square1);
                x += spaceBlock;
            }
            x = originX+unitX+unitY/2-unitSquare/2;
            y += spaceBlock;
        }
        return squares;
    }

    public void setupAlign() {
        for (int i = 0; i<this.square.size; i++) {
            createAlign(this.square.get(i), this.square);
        }
    }

    public void createDLC(int place, boolean side, boolean turn) {
        int x = originX-spaceBlock;
        int y = originY-spaceBlock;
        if(side) {
            y+=spaceBlock*place;
            if(turn) {x+=spaceBlock*5;}
        }
        else {
            x+=spaceBlock*place;
            if(turn) {y+=spaceBlock*5;}
        }
        HollowSquare square1 = new HollowSquare(x+unitX+unitY/2-unitSquare/2, y+unitX+unitY/2-unitSquare/2);
        this.square.add(square1);

        HollowBar bar1 = locateBar(x+unitX,y, this.bar);
        if(bar1==null) {
            bar1 = new HollowBar(true, x + unitX, y);
        }
        this.bar.add(bar1);
        square1.neighbors.add(bar1);

        HollowBar bar2 =locateBar(x+unitX,y+spaceBlock, this.bar);
        if(bar2 ==null) {
            bar2 = new HollowBar(true, x + unitX, y + spaceBlock);
        }
        this.bar.add(bar2);
        square1.neighbors.add(bar2);

        HollowBar bar3 = locateBar(x,y+unitX, this.bar);
        if(bar3==null) {
            bar3 = new HollowBar(false, x, y + unitX);
        }
            this.bar.add(bar3);
            square1.neighbors.add(bar3);

        HollowBar bar4 = locateBar(x+spaceBlock,y+unitX, this.bar);
        if(bar4==null) {
            bar4 = new HollowBar(false, x + spaceBlock, y + unitX);
        }
        this.bar.add(bar4);
        square1.neighbors.add(bar4);
    }

    public HollowSquare locateSquare(int x, int y, Array<HollowSquare> squares) { //retourne le carré de coordonnée (x,y)
        int[] coord = {x,y};
        for (HollowSquare square: squares) {
            if(coord[0] == square.getCoords()[0] && coord[1] == square.getCoords()[1]) {
                return square;
            }
        }
        return null;
    }
    public HollowBar locateBar(int x, int y, Array<HollowBar> bars) { //retourne la barre de coordonnée (x,y)
        int[] coord = {x,y};
        for (HollowBar bar: bars) {
            if(coord[0] == bar.getCoords()[0] && coord[1] == bar.getCoords()[1]) {
                return bar;
            }
        }
        return null;
    }

    public void createAlign(HollowSquare square, Array<HollowSquare> squares) { //Remplie les carrés voisins d'un carré
        int[] coord = square.getCoords();
        HollowSquare right = locateSquare(coord[0]+spaceBlock, coord[1], squares);
        HollowSquare left = locateSquare(coord[0]-spaceBlock, coord[1], squares);
        if(right!=null && left!=null) { //si les cases à droite et à gauche existent, on les ajoute dans le tableau de taille 2 : horizontal
            square.horizontal[0] = right;
            square.horizontal[1] = left;


        }
        else { //Sinon, on précise que ce carré ne peut pas s'aligner à l'horizontal
            square.isHorizontal=false;
        }

        HollowSquare up = locateSquare(coord[0], coord[1]+spaceBlock, squares);
        HollowSquare down = locateSquare(coord[0], coord[1]-spaceBlock, squares);
        if(up!=null && down!=null) { //pareil mais pour les cases verticales
            square.vertical[0] = up;
            square.vertical[1] = down;

        }
        else {
            square.isVertical=false;
        }

        HollowSquare upRight = locateSquare(coord[0]+spaceBlock, coord[1]+spaceBlock, squares);
        HollowSquare downLeft = locateSquare(coord[0]-spaceBlock, coord[1]-spaceBlock, squares);
        if(upRight!=null && downLeft!=null) { //pareil mais pour les diagonales
            square.diagonal[0] = upRight;
            square.diagonal[1]=downLeft;

        }
        else {
            square.isDiagonal=false;
        }

        HollowSquare upLeft = locateSquare(coord[0]-spaceBlock, coord[1]+spaceBlock, squares);
        HollowSquare downRight = locateSquare(coord[0]+spaceBlock, coord[1]-spaceBlock, squares);
        if(upLeft!=null && downRight!=null) { //pareil pour l'antidiagonal
            square.antidiagonal[0]=upLeft;
            square.antidiagonal[1]=downRight;

        }
        else {
            square.isAntidiagonal=false;
        }


    }


}

package com.mygdx.xcube;

import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.xcube.block.HollowBar;
import com.mygdx.xcube.block.HollowSquare;

public class Terrain {
    private final Array<HollowBar> bar;
    private final Array<HollowSquare> square;
    private final int spaceBlock = 600;
    private final int originX = 300;
    private final int originY = 300;


    public Terrain() {
        this.bar = generateBar();           // Stock la liste des barres
        this.square=generateSquare();       // Stock la liste de carrés
    }

    public Array<HollowBar> getBar() {
        return bar;
    }                //Permet d'appeler les barres
    public Array<HollowSquare> getSquare() {
        return square;
    }       // Permet d'appeler les carrés
    public Array<HollowBar>  generateBar() {

        Array<HollowBar> bar = new Array<>();
        int x = originX;
        int y = originY-100;

        for(int i=0; i<5; i++){
            for(int k=0; k<4; k++ ){
                HollowBar b= new HollowBar(0,x,y);
                x += spaceBlock;
                bar.add(b);
            }
            x = originX;
            y += spaceBlock;

        }

        x = originX-100;
        y = originY;

        for(int i=0; i<4; i++) {
            for (int k = 0; k < 5; k++) {
                HollowBar b = new HollowBar(90,x,y);

                bar.add(b);

                x += spaceBlock;
            }
            x = originX-100;
            y += spaceBlock;
        }
        return bar;
    }
    public Array<HollowSquare> generateSquare() { //construit une grille de carré et leur attribut les barres qui les entourent

        Array<HollowSquare> block = new Array<>();
        int x = originX+50;
        int y = originY+50;

        for(int i=0; i<4; i++) {
            for (int j = 0; j < 4; j++) {
                HollowSquare square1 = new HollowSquare(x, y);
                square1.neighbors.add(bar.get((i*4)+j));
                square1.neighbors.add(bar.get((i*4)+j + 4));
                square1.neighbors.add(bar.get((i*5)+j + 20));
                square1.neighbors.add(bar.get((i*5)+j + 21));
                block.add(square1);
                x += spaceBlock;
            }
            x = originX+50;
            y += spaceBlock;
        }
        for (int i = 0; i<block.size; i++) {
            createAlign(block.get(i), block);
        }


        return block;
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

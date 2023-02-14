package com.mygdx.xcube;

import com.badlogic.gdx.utils.Array;
import com.mygdx.xcube.block.HollowBar;
import com.mygdx.xcube.block.HollowSquare;

public class Terrain {
    private Array<HollowBar> bar;
    private Array<HollowSquare> square;


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
    public Array generateBar() {

        Array<HollowBar> bar = new Array<HollowBar>();
        float x = 300;
        float y = -400;

        for(int i=0; i<5; i++){
            y = y+ 600;
            x = 300;
            for(int k=0; k<4; k++ ){
                HollowBar b= new HollowBar(0,x,y);
                x = x + 600;
                bar.add(b);
            }
        }

        x = 300;
        y = -300;
        for(int i=0; i<4; i++) {
            y += 600;
            x = 200;
            for (int k = 0; k < 5; k++) {
                bar.add(new HollowBar(90,x,y));
                x = x + 600;

            }


        }
        return bar;
    }
    public Array<HollowSquare> generateSquare() {

        Array<HollowSquare> block = new Array<HollowSquare>();
        float x;
        float y = -250;

        for(int i=0; i<4; i++) {
            y += 600;
            x = 350;
            for (int j = 0; j < 4; j++) {
                HollowSquare square1 = new HollowSquare(x, y);
                square1.neighbors.add(bar.get((i*4)+j));
                square1.neighbors.add(bar.get((i*4)+j + 4));
                square1.neighbors.add(bar.get((i*5)+j + 20));
                square1.neighbors.add(bar.get((i*5)+j + 21));
                block.add(square1);
                x += 600;
            }
        }
        return block;
    }
}

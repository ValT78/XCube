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

        x = -400;
        y = 300;
        for(int i=0; i<5; i++) {
            x = x + 600;
            y = 300;
            for (int k = 0; k < 4; k++) {
                bar.add(new HollowBar(90,x,y));
                y = y + 600;

            }


        }
        return bar;
    }
    public Array<HollowSquare> generateSquare() {

        Array<HollowSquare> block = new Array<HollowSquare>();
        float x = -250;
        float y;

        for(int i=0; i<4; i++) {
            x = x + 600;
            y = 350;
            for (int k = 0; k < 4; k++) {
                block.add(new HollowSquare(x, y));
                y = y + 600;
            }
        }
        return block;
    }
}

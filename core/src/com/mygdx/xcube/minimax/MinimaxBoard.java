package com.mygdx.xcube.minimax;

import java.util.ArrayList;
import java.util.Arrays;

class MinimaxBoard {

    // represents a board as arrays of squares and bars, from top to bottom and left to right (0 : free, 1 : AI, 2 : opponent)
    // note : only works for a 4x4 grid

    private int squares[];
    private int horizontalBars[];
    private int verticalBars[];

    public MinimaxBoard() {
        this.squares = new int[16];
        this.horizontalBars = new int[20];
        this.verticalBars = new int[20];
        Arrays.fill(this.squares, 0);
        Arrays.fill(this.horizontalBars, 0);
        Arrays.fill(this.verticalBars, 0);
    }

    // play a move (to update)
    public MinimaxBoard play(MinimaxMove move) {
        return this;
    }

}

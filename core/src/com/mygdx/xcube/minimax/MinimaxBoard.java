package com.mygdx.xcube.minimax;

import java.util.ArrayList;
import java.util.Arrays;

class MinimaxBoard {

    // represents a board as arrays of squares and bars, from top to bottom and left to right
    // note : only works for a 4x4 grid

    private int squares[]; // reprensents the squares (0 : free, 1 : AI, -1 : opponent)
    private int horizontalBars[]; // represents the horizontal bars (0 : free, 1 : not free)
    private int verticalBars[]; // represents the vertircal bars (0 : free, 1 : not free)

    public MinimaxBoard() {
        this.squares = new int[16];
        this.horizontalBars = new int[20];
        this.verticalBars = new int[20];
        Arrays.fill(this.squares, 0);
        Arrays.fill(this.horizontalBars, 0);
        Arrays.fill(this.verticalBars, 0);
    }

    public int[] getSquares() {
        return this.squares;
    }
    public int[] getHorizontalBars() {
        return this.horizontalBars;
    }
    public int[] getVerticalBars() {
        return this.verticalBars;
    }

    // play a move (to update)
    public MinimaxBoard play(MinimaxMove move) {

        return this;

    }

    // returns the score associated with this board (to update)
    public Double heuristic() {

        Double score = 0;

        for (int i = 0 ; i < squares ; i++) {

            score += squares[i];

        }

        return score;
        
    }

}

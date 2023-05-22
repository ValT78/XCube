package com.mygdx.xcube.minimax;

class MinimaxMove {

    // represents a move

    private boolean isSquareAI; // true if it represents a square played by the AI
    private boolean isSquareOpponent; // true if it represents a square played by the opponent
    private boolean isHorizontalBar; // true if it represents a horizontal bar
    private boolean isVerticalBar; // true if it represents a vertical bar
    private int index; // index of the block in the corresponding array of the board

    public MinimaxMove(boolean isSquareAI, boolean isSquareOpponent, boolean isHorizontalBar, boolean isVerticalBar, int index) {
        this.isSquareAI = isSquareAI;
        this.isSquareOpponent = isSquareOpponent;
        this.isHorizontalBar = isHorizontalBar;
        this.isVerticalBar = isVerticalBar;
        this.index = index;
    }

    public boolean getIsSquareAI() {
        return this.isSquareAI;
    }
    public boolean getIsSquarOpponent() {
        return this.isSquareOpponent;
    }
    public boolean getIsHorizontalBar() {
        return this.isHorizontalBar;
    }
    public boolean getIsVerticalBar() {
        return this.isVerticalBar;
    }
    public int getIndex() {
        return this.index;
    }

}

package com.mygdx.xcube.minimax;

class MinimaxMove {

    // represents a move

    private boolean isSquare; // true if it represents a square
    private boolean isHorizontalBar; // true if it represents a horizontal bar
    private boolean isVerticalBar; // true if it represents a vertical bar
    private int index; // index of the block in the corresponding array of the board

    public Move(boolean isSquare, boolean isHorizontalBar, boolean isVerticalBar, int index) {
        this.isSquare = isSquare;
        this.isHorizontalBar = isHorizontalBar;
        this.isVerticalBar = isVerticalBar;
        this.index = index;
    }

    public boolean getIsSquare() {
        return this.isSquare;
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

package com.mygdx.xcube.minimax;

class MinimaxNode {

    private boolean isMaxPlayer; // true if the current node is maximizing the score
    private boolean isFirstAction; // true if this node is the first of the two actions a player has during their turn
    private boolean isTerminal; // true if the board presents a win condition
    private Board board;

    public MinimaxNode(boolean isMaxPlayer, boolean isFirstAction, Board board) {
        this.isMaxPlayer = isMaxPlayer;
        this.isFirstAction = isFirstAction;
        this.board = board;
        this.isTerminal = this.someoneWins(board);
    }

    public boolean getIsMaxPlayer() {
        return this.isMaxPlayer;
    }
    public boolean getIsFirstAction() {
        return this.isFirstAction;
    }
    public boolean getIsTerminal() {
        return this.isTerminal;
    }
    public Board getBoard() {
        return this.board;
    }


    // check if the board is terminal
    public boolean someoneWins(Board board) {
        return false;
    }

    // returns the list of possibles moves from this board
    public Move[] possibleMoves() {
        Move[] res = new Move[1];
        Move newMove = new Move();
        res[0] = newMove;
        return res;
    }

    // returns the score associated with this board
    public Double heuristic() {
        return Double.valueOf(1);
    }

}

package com.mygdx.xcube.minimax;

class MinimaxNode {

    private boolean isMaxPlayer; // true if the current node is maximizing the score
    private boolean isFirstAction; // true if this node is the first of the two actions a player has during their turn
    private boolean isTerminal; // true if the board presents a win condition
    private MinimaxBoard board;

    public MinimaxNode(boolean isMaxPlayer, boolean isFirstAction, MinimaxBoard board) {
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
    public MinimaxBoard getBoard() {
        return this.board;
    }


    // check if the board is terminal (to update)
    public boolean someoneWins(MinimaxBoard board) {
        return false;
    }

    // returns the list of possibles moves from this board
    public ArrayList<MinimaxMove> possibleMoves() {

        ArrayList<MinimaxMove> moves = new ArrayList<MinimaxMove>;
        int squares[] = this.board.getSquares();
        int horizontalBars[] = this.board.getHorizontalBars();
        int verticalBars[] = this.board.getVerticalBars(); 

        // square case (checks if the square is surrounded by bars)
        for (int i = 0 ; i < squares.length ; i++) {
            if (squares[i] == 0 && (horizontalBars[i] == 1 && horizontalBars[i + 4] == 1) && (verticalBars[i + (i / 4 % 4)] == 1 && verticalBars[i + (i / 4 % 4) + 1] == 1)) {
                MinimaxMove newMove = new MinimaxMove(this.isMaxPlayer, ! this.isMaxPlayer, false, false, i);
                moves.add(newMove);
            }
        }
        // horizontal bar case
        for (int i = 0 ; i < horizontalBars.length ; i++) {
            if (horizontalBars[i] == 0) {
                MinimaxMove newMove = new MinimaxMove(false, false, true, false, i);
                moves.add(newMove);
            }
        }
        // vertical bar case
        for (int i = 0 ; i < verticalBars.length ; i++) {
            if (verticalBars[i] == 0) {
                MinimaxMove newMove = new MinimaxMove(false, false, false, true, i);
                moves.add(newMove);
            }
        }

        return moves;

    }

    // returns the score associated with this board
    public Double heuristic() {
        return this.board.heuristic();
    }

}

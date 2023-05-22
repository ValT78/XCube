package com.mygdx.xcube.minimax;

import java.util.ArrayList;
import java.util.Random;

class Minimax {


    // Uses the minimax algorithm to return the best score possible given a board, looking 'depth' step ahead
    private static Double minimax(MinimaxNode node, int depth) {
       

        // If the node is a leaf or if it presents a win condition, return the heuristic score of the board
        if (depth == 0 || node.getIsTerminal()) {
            return node.heuristic();
        }


        // Maximizing player case (AI)
        if (node.getIsMaxPlayer()) {

            Double value = Double.NEGATIVE_INFINITY;

            // First action case
            if (node.getIsFirstAction()) {

                for (MinimaxMove move: node.possibleMoves()) { // for each possible move from the current board,

                    MinimaxBoard newBoard = node.getBoard().play(move); // get the board after the move
                    MinimaxNode child = new MinimaxNode(true, false, newBoard); // create the next node (maximizing player, second action)
                    value = Math.max(value, minimax(child, depth - 1)); // compute the score of the current node

                }

            // Second action case
            } else {

                for (MinimaxMove move: node.possibleMoves()) { // for each possible move from the current board,

                    MinimaxBoard newBoard = node.getBoard().play(move); // get the board after the move
                    MinimaxNode child = new MinimaxNode(false, true, newBoard); // create the next node (minimizing player, first action)
                    value = Math.max(value, minimax(child, depth - 1)); // compute the score of the current node

                }

            }

            return value;


        // Minimizing player case (opponent)
        } else {

            Double value = Double.POSITIVE_INFINITY;
            
            // First action case
            if (node.getIsFirstAction()) {

                for (MinimaxMove move: node.possibleMoves()) { // for each possible move from the current board,

                    MinimaxBoard newBoard = node.getBoard().play(move); // get the board after the move
                    MinimaxNode child = new MinimaxNode(false, false, newBoard); // create the next node (minimizing player, second action)
                    value = Math.min(value, minimax(child, depth - 1)); // compute the score of the current node

                }

            // Second action case
            } else {

                for (MinimaxMove move: node.possibleMoves()) { // for each possible move from the current board,

                    MinimaxBoard newBoard = node.getBoard().play(move); // get the board after the move
                    MinimaxNode child = new MinimaxNode(true, true, newBoard); // create the next node (maximizing player, first action)
                    value = Math.min(value, minimax(child, depth - 1)); // compute the score of the current node

                }

            }

            return value;

        }

    }




    // Uses alpha beta pruning on the minimax algorithm to reduce the number of explored nodes
    private static Double alphabeta(MinimaxNode node, int depth, Double alpha, Double beta) {
   
        // If the node is a leaf or if it presents a win condition, return the heuristic score of the board
        if (depth == 0 || node.getIsTerminal()) {
            return node.heuristic();
        }


        // Maximizing player case (AI)
        if (node.getIsMaxPlayer()) {

            Double value = Double.NEGATIVE_INFINITY;

            // First action case
            if (node.getIsFirstAction()) {

                for (MinimaxMove move: node.possibleMoves()) { // for each possible move from the current board,

                    MinimaxBoard newBoard = node.getBoard().play(move); // get the board after the move
                    MinimaxNode child = new MinimaxNode(true, false, newBoard); // create the next node (maximizing player, second action)
                    value = Math.max(value, alphabeta(child, depth - 1, alpha, beta)); // compute the score of the current node
                    if (value > beta) {
                        break; // beta break
                    }
                    alpha = Math.max(alpha, value);

                }

            // Second action case
            } else {

                for (MinimaxMove move: node.possibleMoves()) { // for each possible move from the current board,

                    MinimaxBoard newBoard = node.getBoard().play(move); // get the board after the move
                    MinimaxNode child = new MinimaxNode(false, true, newBoard); // create the next node (minimizing player, first action)
                    value = Math.max(value, alphabeta(child, depth - 1, alpha, beta)); // compute the score of the current node
                    if (value > beta) {
                        break; // alpha break
                    }
                    alpha = Math.max(alpha, value);

                }

            }

            return value;


        // Minimizing player case (opponent)
        } else {

            Double value = Double.POSITIVE_INFINITY;
            
            // First action case
            if (node.getIsFirstAction()) {

                for (MinimaxMove move: node.possibleMoves()) { // for each possible move from the current board,

                    MinimaxBoard newBoard = node.getBoard().play(move); // get the board after the move
                    MinimaxNode child = new MinimaxNode(false, false, newBoard); // create the next node (minimizing player, second action)
                    value = Math.min(value, alphabeta(child, depth - 1, alpha, beta)); // compute the score of the current node
                    if (value < alpha) {
                        break;
                    }
                    beta = Math.min(beta, value);

                }

            // Second action case
            } else {

                for (MinimaxMove move: node.possibleMoves()) { // for each possible move from the current board,

                    MinimaxBoard newBoard = node.getBoard().play(move); // get the board after the move
                    MinimaxNode child = new MinimaxNode(true, true, newBoard); // create the next node (maximizing player, first action)
                    value = Math.min(value, alphabeta(child, depth - 1, alpha, beta)); // compute the score of the current node
                    if (value < alpha) {
                        break;
                    }
                    beta = Math.min(beta, value);

                }

            }

            return value;

        }

    }



    // Given a board and a number of steps (4k + 2), compute using the minimax algorithm (with alpha beta pruning) the best score and returns the next move to play 
    public static MinimaxMove chooseMove(MinimaxBoard board, int nbSteps) {

        MinimaxNode root = new MinimaxNode(true, true, board); // root of the tree
        Double bestScore = Double.NEGATIVE_INFINITY; // the best score
        Random rd = new Random();

        ArrayList<MinimaxMove> bestMoves = new ArrayList<MinimaxMove>(); // the list of moves that maximize the score

        for (MinimaxMove move: root.possibleMoves()) {

            MinimaxBoard newBoard = root.getBoard().play(move);
            MinimaxNode child = new MinimaxNode(true, false, newBoard);
            Double score = alphabeta(child, nbSteps - 1, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY); // compute score of this node using minimax

            if (score > bestScore) { // if we find a new best score,
                bestMoves.clear(); // clear the list of best moves
                bestMoves.add(move); // and add the maximizing move to the list
            } else if (score == bestScore) { // if we find another best move,
                bestMoves.add(move); // add it to the list
            }

        }

        int index = rd.nextInt(bestMoves.size());

        return bestMoves.get(index);

    }


}

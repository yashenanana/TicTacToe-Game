package com.einarbeitung.tictactoe.model;

import java.util.List;

/**
 * Class representing attributes of a game to be loaded and displayed to client.
 */
public class LoadedGame extends Stats {

    String[] currentSquares;
    List<String[]> history;
    boolean isAscending;

    public LoadedGame(int id, String winner, int[] winningMoves, int currentMove,  boolean isAscending, String[] currentSquares, List<String[]> history) {
        super(id, winner, winningMoves, currentMove);
        this.currentSquares = currentSquares;
        this.history = history;
        this.isAscending = isAscending;
    }

    public String[] getCurrentSquares() {
        return currentSquares;
    }

    public List<String[]> getHistory() {
        return history;
    }

    public boolean getIsAscending() {
        return isAscending;
    }
}

package com.einarbeitung.tictactoe.model;

/**
 * Class representing game statistics to be displayed on client end.
 */
public class Stats {
    private int id;
    private String winner;
    private int[] winningMoves;
    private int currentMove;


    public Stats(int id, String winner, int[] winningMoves, int currentMove) {
        this.id = id;
        this.winner = winner;
        this.winningMoves = winningMoves;
        this.currentMove = currentMove;
    }

    public int getCurrentMove() {
        return currentMove;
    }

    public String getWinner() {
        return winner;
    }

    public int[] getWinningMoves() {
        return winningMoves;
    }

    public int getId(){
        return id;
    }
}

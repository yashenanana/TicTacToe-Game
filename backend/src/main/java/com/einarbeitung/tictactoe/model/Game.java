package com.einarbeitung.tictactoe.model;

import jakarta.persistence.*;

import java.util.ArrayList;

/**
 * Class representing attributes of a game to be saved in the database.
 */

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Lob
    private ArrayList<String[]> history;
    private int currentMove;
    private boolean isAscending;
    private boolean xIsNext;
    private String[] currentSquares;
    private boolean jumped;
    private String winner;
    private int[] winningMoves;
    private boolean loaded;

    public Game(ArrayList<String[]> history, int currentMove, String winner, int[] winningMoves) {
        this.history =  history;
        this.currentMove = currentMove;
        this.isAscending = true;
        this.xIsNext = currentMove%2==0;
        this.currentSquares = history.get(currentMove);
        this.winner = winner;
        this.winningMoves = winningMoves;
        this.jumped = false;
        this.loaded = false;
    }

    public Game() {
    }

    public ArrayList<String[]> getHistory() {
        return history;
    }
    public void setHistory(ArrayList<String[]> history) {
        this.history = history;
    }

    public int getCurrentMove() {
        return currentMove;
    }
    public void setCurrentMove(int currentMove) {
        this.currentMove = currentMove;
    }

    public boolean isAscending() {
        return isAscending;
    }
    public void setAscending(boolean ascending) {
        isAscending = ascending;
    }

    public boolean isXIsNext() {
        return xIsNext;
    }
    public void setXIsNext(boolean xIsNext) {
        this.xIsNext = xIsNext;
    }

    public String[] getCurrentSquares() {
        return currentSquares;
    }
    public void setCurrentSquares(String[] currentSquares) {
        this.currentSquares = currentSquares;
    }

    public String getWinner() {
        return winner;
    }
    public void setWinner(String winner) {
        this.winner = winner;
    }

    public int[] getWinningMoves() {
        return winningMoves;
    }
    public void setWinningMoves(int[] winningMoves) {
        this.winningMoves = winningMoves;
    }

    public int getId() {
        return id;
    }

    public void setJumped(boolean jumped) {
        this.jumped = jumped;
    }
    public boolean isJumped() {
        return jumped;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public boolean isLoaded() {
        return loaded;
    }
}


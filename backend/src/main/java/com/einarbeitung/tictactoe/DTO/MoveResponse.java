package com.einarbeitung.tictactoe.DTO;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * DTO representing server response after new move is added.
 * @param currentMove Updated latest move
 * @param winner Updated winner
 * @param winningMoves Updated combination of winning moves
 * @param history Updated round history
 */
public record MoveResponse(int currentMove, String winner, int[] winningMoves, List<String[]> history) {
    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();
        for (int i = 0; i < history.size(); i++) {
            for (int j = 0; j < history.get(i).length; j++) {
                bld.append(history.get(i)[j]);
            }
            bld.append("\n");
        }
        return "Person{" +
                "currentMove=" + currentMove +
                ", winner='" + winner + '\'' +
                ", winningMoves=" + Arrays.toString(winningMoves) +
                ", history=" + bld +
        '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoveResponse that = (MoveResponse) o;
        return (currentMove != that.currentMove || winner != null ? !winner.equals(that.winner) : that.winner != null || Objects.equals(history, that.history) || Arrays.equals(winningMoves, that.winningMoves));
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(currentMove, winner, history);
        result = 31 * result + Arrays.hashCode(winningMoves);
        return result;
    }

}

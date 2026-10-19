package com.einarbeitung.tictactoe.DTO;

/**
 * DTO representing client request to add a new move.
 * @param position Position of new move on the board
 */
public record MoveRequest(int position) {
}

package com.einarbeitung.tictactoe.DTO;

/**
 * DTO representing client request to timeskip to specific round.
 * @param move Move number of requested round
 */
public record JumpToRequest(int move) {
}

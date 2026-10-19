package com.einarbeitung.tictactoe.DTO;

/**
 * DTO representing server response after isAscending has been set.
 * @param isAscending New isAscending value
 */
public record SetAscendingResponse(boolean isAscending) {}

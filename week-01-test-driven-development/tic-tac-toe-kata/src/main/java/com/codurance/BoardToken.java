package com.codurance;

public enum BoardToken {
    PLAYER_ONE_TOKEN('X'),
    PLAYER_TWO_TOKEN('O'),
    EMPTY_SPACE('-');

    public final char symbol;

    BoardToken(char symbol) {
        this.symbol = symbol;
    }
}

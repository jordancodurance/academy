package com.codurance;

public enum BowlingThrow {
    SPARE('/'),
    STRIKE('X'),
    MISS('-');

    public final char symbol;

    BowlingThrow(char symbol) {
        this.symbol = symbol;
    }
}

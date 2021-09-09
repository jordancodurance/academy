package com.codurance;

public enum SpecialRoll {
    STRIKE("X"),
    SPARE("/"),
    MISS("-");

    public final String symbol;

    SpecialRoll(String symbol) {
        this.symbol = symbol;
    }
}

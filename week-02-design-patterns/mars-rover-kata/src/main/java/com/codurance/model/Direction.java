package com.codurance.model;

public enum Direction {

    NORTH("N"),
    EAST("E"),
    SOUTH("S"),
    WEST("W");

    public final String compass;

    Direction(String compass) {
        this.compass = compass;
    }

}

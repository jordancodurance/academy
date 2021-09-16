package com.codurance.landscape.direction;

public enum CompassDirection {

    NORTH("N"),
    EAST("E"),
    SOUTH("S"),
    WEST("W");

    public final String value;

    CompassDirection(String value) {
        this.value = value;
    }

}

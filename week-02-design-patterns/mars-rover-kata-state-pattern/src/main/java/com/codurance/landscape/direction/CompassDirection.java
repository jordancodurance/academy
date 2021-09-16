package com.codurance.landscape.direction;

public enum CompassDirection {

    NORTH("N"),
    NORTHEAST("NE"),
    EAST("E"),
    SOUTHEAST("SE"),
    SOUTH("S"),
    SOUTHWEST("SW"),
    WEST("W"),
    NORTHWEST("NW");

    public final String value;

    CompassDirection(String value) {
        this.value = value;
    }

}

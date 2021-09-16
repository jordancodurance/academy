package com.codurance.landscape.direction;

import com.codurance.landscape.Coordinate;

public abstract class Direction {

    public abstract CompassDirection getCompass();

    public abstract Direction rotateLeft();

    public abstract Direction rotateRight();

    public abstract Coordinate move(Coordinate currentCoordinate);

    protected int decrementWithinRange(int value, int range) {
        return (value - 1 + range) % range;
    }

    protected int incrementWithinRange(int value, int range) {
        return (value + 1) % range;
    }

}

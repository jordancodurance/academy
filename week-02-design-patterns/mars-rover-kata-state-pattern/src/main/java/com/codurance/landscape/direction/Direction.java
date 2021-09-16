package com.codurance.landscape.direction;

import com.codurance.landscape.Coordinate;

public abstract class Direction {

    public abstract CompassDirection getCompass();

    public abstract Direction rotateLeft();

    public abstract Direction rotateRight();

    public abstract Coordinate move(Coordinate currentCoordinate);

}

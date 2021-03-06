package com.codurance.landscape.direction;

import com.codurance.landscape.Coordinate;

import static com.codurance.landscape.direction.CompassDirection.SOUTH;

public class SouthDirection extends Direction {

    @Override
    public CompassDirection getCompass() {
        return SOUTH;
    }

    @Override
    public Direction rotateLeft() {
        return new SouthEastDirection();
    }

    @Override
    public Direction rotateRight() {
        return new SouthWestDirection();
    }

    @Override
    public Coordinate move(Coordinate currentCoordinate) {
        return new Coordinate(
                currentCoordinate.x,
                currentCoordinate.getPreviousYAxis()
        );
    }

}

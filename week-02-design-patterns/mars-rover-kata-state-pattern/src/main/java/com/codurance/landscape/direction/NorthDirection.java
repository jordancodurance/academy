package com.codurance.landscape.direction;

import com.codurance.landscape.Coordinate;

import static com.codurance.landscape.direction.CompassDirection.NORTH;

public class NorthDirection extends Direction {

    @Override
    public CompassDirection getCompass() {
        return NORTH;
    }

    @Override
    public Direction rotateLeft() {
        return new NorthWestDirection();
    }

    @Override
    public Direction rotateRight() {
        return new NorthEastDirection();
    }

    @Override
    public Coordinate move(Coordinate currentCoordinate) {
        return new Coordinate(
                currentCoordinate.x,
                currentCoordinate.getNextYAxis()
        );
    }

}

package com.codurance.landscape.direction;

import com.codurance.landscape.Coordinate;

import static com.codurance.landscape.direction.CompassDirection.NORTHEAST;

public class NorthEastDirection extends Direction {

    @Override
    public CompassDirection getCompass() {
        return NORTHEAST;
    }

    @Override
    public Direction rotateLeft() {
        return new NorthDirection();
    }

    @Override
    public Direction rotateRight() {
        return new EastDirection();
    }

    @Override
    public Coordinate move(Coordinate currentCoordinate) {
        return new Coordinate(
                currentCoordinate.getNextXAxis(),
                currentCoordinate.getNextYAxis()
        );
    }

}

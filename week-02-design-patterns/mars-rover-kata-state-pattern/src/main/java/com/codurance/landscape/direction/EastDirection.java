package com.codurance.landscape.direction;

import com.codurance.landscape.Coordinate;

import static com.codurance.landscape.direction.CompassDirection.EAST;

public class EastDirection extends Direction {

    @Override
    public CompassDirection getCompass() {
        return EAST;
    }

    @Override
    public Direction rotateLeft() {
        return new NorthEastDirection();
    }

    @Override
    public Direction rotateRight() {
        return new SouthEastDirection();
    }

    @Override
    public Coordinate move(Coordinate currentCoordinate) {
        return new Coordinate(
                currentCoordinate.getNextXAxis(),
                currentCoordinate.y
        );
    }

}

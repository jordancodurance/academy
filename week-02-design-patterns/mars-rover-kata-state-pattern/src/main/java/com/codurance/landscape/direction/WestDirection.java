package com.codurance.landscape.direction;

import com.codurance.landscape.Coordinate;

import static com.codurance.landscape.direction.CompassDirection.WEST;

public class WestDirection extends Direction {

    @Override
    public CompassDirection getCompass() {
        return WEST;
    }

    @Override
    public Direction rotateLeft() {
        return new SouthWestDirection();
    }

    @Override
    public Direction rotateRight() {
        return new NorthWestDirection();
    }

    @Override
    public Coordinate move(Coordinate currentCoordinate) {
        return new Coordinate(
                currentCoordinate.getPreviousXAxis(),
                currentCoordinate.y
        );
    }

}

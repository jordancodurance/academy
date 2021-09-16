package com.codurance.landscape.direction;

import com.codurance.landscape.Coordinate;
import com.codurance.landscape.Grid;

import static com.codurance.landscape.direction.CompassDirection.SOUTH;

public class SouthDirection extends Direction {

    @Override
    public CompassDirection getCompass() {
        return SOUTH;
    }

    @Override
    public Direction rotateLeft() {
        return new EastDirection();
    }

    @Override
    public Direction rotateRight() {
        return new WestDirection();
    }

    @Override
    public Coordinate move(Coordinate currentCoordinate) {
        int currentY = currentCoordinate.y;
        int previousY = decrementWithinRange(currentY, Grid.BOUNDARY);
        return new Coordinate(currentCoordinate.x, previousY);
    }

}

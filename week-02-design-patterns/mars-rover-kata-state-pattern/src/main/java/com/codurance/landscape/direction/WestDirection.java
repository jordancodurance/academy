package com.codurance.landscape.direction;

import com.codurance.landscape.Coordinate;
import com.codurance.landscape.Grid;

import static com.codurance.landscape.direction.CompassDirection.WEST;

public class WestDirection extends Direction {

    @Override
    public CompassDirection getCompass() {
        return WEST;
    }

    @Override
    public Direction rotateLeft() {
        return new SouthDirection();
    }

    @Override
    public Direction rotateRight() {
        return new NorthDirection();
    }

    @Override
    public Coordinate move(Coordinate currentCoordinate) {
        int currentX = currentCoordinate.x;
        int previousX = decrementWithinRange(currentX, Grid.BOUNDARY);
        return new Coordinate(previousX, currentCoordinate.y);
    }

}

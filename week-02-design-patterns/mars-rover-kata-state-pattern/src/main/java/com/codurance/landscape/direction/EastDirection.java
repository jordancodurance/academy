package com.codurance.landscape.direction;

import com.codurance.landscape.Coordinate;
import com.codurance.landscape.Grid;

import static com.codurance.landscape.direction.CompassDirection.EAST;

public class EastDirection extends Direction {

    @Override
    public CompassDirection getCompass() {
        return EAST;
    }

    @Override
    public Direction rotateLeft() {
        return new NorthDirection();
    }

    @Override
    public Direction rotateRight() {
        return new SouthDirection();
    }

    @Override
    public Coordinate move(Coordinate currentCoordinate) {
        int currentX = currentCoordinate.x;
        int nextX = incrementWithinRange(currentX, Grid.BOUNDARY);
        return new Coordinate(nextX, currentCoordinate.y);
    }

}

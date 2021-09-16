package com.codurance.landscape.direction;

import com.codurance.landscape.Coordinate;
import com.codurance.landscape.Grid;

import static com.codurance.landscape.direction.CompassDirection.NORTH;

public class NorthDirection extends Direction {

    @Override
    public CompassDirection getCompass() {
        return NORTH;
    }

    @Override
    public Direction rotateLeft() {
        return new WestDirection();
    }

    @Override
    public Direction rotateRight() {
        return new EastDirection();
    }

    @Override
    public Coordinate move(Coordinate currentCoordinate) {
        int currentY = currentCoordinate.y;
        int nextY = incrementWithinRange(currentY, Grid.BOUNDARY);
        return new Coordinate(currentCoordinate.x, nextY);
    }

}

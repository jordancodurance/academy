package com.codurance.landscape.direction;

import com.codurance.landscape.Coordinate;
import static com.codurance.landscape.direction.CompassDirection.NORTHWEST;

public class NorthWestDirection extends Direction {
    @Override
    public CompassDirection getCompass() {
        return NORTHWEST;
    }

    @Override
    public Direction rotateLeft() {
        return new WestDirection();
    }

    @Override
    public Direction rotateRight() {
        return new NorthDirection();
    }

    @Override
    public Coordinate move(Coordinate currentCoordinate) {
        return new Coordinate(
                currentCoordinate.getPreviousXAxis(),
                currentCoordinate.getNextYAxis()
        );
    }
}

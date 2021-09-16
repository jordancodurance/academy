package com.codurance.landscape.direction;

import com.codurance.landscape.Coordinate;
import static com.codurance.landscape.direction.CompassDirection.SOUTHEAST;

public class SouthEastDirection extends Direction{
    @Override
    public CompassDirection getCompass() {
        return SOUTHEAST;
    }

    @Override
    public Direction rotateLeft() {
        return new EastDirection();
    }

    @Override
    public Direction rotateRight() {
        return new SouthDirection();
    }

    @Override
    public Coordinate move(Coordinate currentCoordinate) {
        return new Coordinate(
                currentCoordinate.getNextXAxis(),
                currentCoordinate.getPreviousYAxis()
        );
    }
}

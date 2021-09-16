package com.codurance.landscape.direction;

import com.codurance.landscape.Coordinate;
import static com.codurance.landscape.direction.CompassDirection.SOUTHWEST;


public class SouthWestDirection extends Direction{
    @Override
    public CompassDirection getCompass() {
        return SOUTHWEST;
    }

    @Override
    public Direction rotateLeft() {
        return new SouthDirection();
    }

    @Override
    public Direction rotateRight() {
        return new WestDirection();
    }

    @Override
    public Coordinate move(Coordinate currentCoordinate) {
        return new Coordinate(
                currentCoordinate.getPreviousXAxis(),
                currentCoordinate.getPreviousYAxis()
        );
    }
}

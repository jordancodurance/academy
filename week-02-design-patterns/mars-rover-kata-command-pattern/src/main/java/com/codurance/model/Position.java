package com.codurance.model;

import static com.codurance.model.Direction.NORTH;

public class Position {

    public final Direction direction;
    public final Coordinate coordinate;

    public Position(Direction direction, Coordinate coordinate) {
        this.direction = direction;
        this.coordinate = coordinate;
    }

}

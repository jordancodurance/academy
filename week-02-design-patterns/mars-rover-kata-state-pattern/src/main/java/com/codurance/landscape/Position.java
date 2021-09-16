package com.codurance.landscape;

import com.codurance.landscape.direction.Direction;

public class Position {

    public final Coordinate coordinate;
    public final Direction direction;

    public Position(Coordinate coordinate, Direction direction) {
        this.coordinate = coordinate;
        this.direction = direction;
    }
}

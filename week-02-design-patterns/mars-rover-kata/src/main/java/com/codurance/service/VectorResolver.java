package com.codurance.service;

import com.codurance.error.ObstacleEncounteredException;
import com.codurance.model.Coordinate;
import com.codurance.model.Direction;
import com.codurance.model.Grid;
import com.codurance.model.Obstacle;

import static com.codurance.model.Direction.EAST;
import static com.codurance.model.Direction.NORTH;
import static com.codurance.model.Direction.SOUTH;
import static com.codurance.model.Direction.WEST;
import static com.codurance.model.Direction.values;
import static com.codurance.model.Grid.BOUNDARY;
import static java.lang.String.format;

public class VectorResolver {

    private final Grid grid;

    public VectorResolver(Grid grid) {
        this.grid = grid;
    }

    public Direction resolveNextRightRotation(Direction currentDirection) {
        Direction[] directions = values();
        int currentIndex = currentDirection.ordinal();
        int nextIndex = incrementWithinRange(currentIndex, directions.length);

        return directions[nextIndex];
    }

    private int incrementWithinRange(int value, int range) {
        return (value + 1) % range;
    }

    public Direction resolveNextLeftRotation(Direction currentDirection) {
        Direction[] directions = values();
        int currentIndex = currentDirection.ordinal();
        int previousIndex = decrementWithinRange(currentIndex, directions.length);

        return directions[previousIndex];
    }

    private int decrementWithinRange(int value, int range) {
        return (value - 1 + range) % range;
    }

    public Coordinate resolveNextCoordinate(Coordinate currentCoordinate, Direction currentDirection) throws ObstacleEncounteredException {
        Coordinate nextCoordinate = resolveNextPotentialCoordinate(currentCoordinate, currentDirection);

        Coordinate coordinateOnGrid = grid.get(nextCoordinate);
        if (coordinateOnGrid instanceof Obstacle) {
            throw new ObstacleEncounteredException(format("Encountered obstacle at %d, %d", nextCoordinate.x, nextCoordinate.y));
        }

        return nextCoordinate;
    }

    public Coordinate resolveNextPotentialCoordinate(Coordinate currentCoordinate, Direction currentDirection) {
        int x = currentCoordinate.x;
        int y = currentCoordinate.y;

        if (currentDirection == NORTH)
            y = incrementWithinRange(y, BOUNDARY);

        if (currentDirection == SOUTH)
            y = decrementWithinRange(y, BOUNDARY);

        if (currentDirection == EAST)
            x = incrementWithinRange(x, BOUNDARY);

        if (currentDirection == WEST)
            x = decrementWithinRange(x, BOUNDARY);

        return new Coordinate(x, y);
    }

}

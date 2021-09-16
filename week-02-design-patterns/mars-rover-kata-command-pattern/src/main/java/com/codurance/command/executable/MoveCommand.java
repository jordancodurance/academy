package com.codurance.command.executable;

import com.codurance.interaction.ObstacleEncounteredException;
import com.codurance.interaction.Coordinate;
import com.codurance.interaction.Direction;
import com.codurance.interaction.Obstacle;
import com.codurance.interaction.Position;
import com.codurance.interaction.Grid;

import static com.codurance.interaction.Direction.EAST;
import static com.codurance.interaction.Direction.NORTH;
import static com.codurance.interaction.Direction.SOUTH;
import static com.codurance.interaction.Direction.WEST;
import static com.codurance.interaction.Grid.BOUNDARY;
import static java.lang.String.format;

public class MoveCommand extends VectorCommand {
    private final Grid grid;

    public MoveCommand(Position currentPosition, Grid grid) {
        super(currentPosition);

        this.grid = grid;
    }

    @Override
    public Position execute() {
        Coordinate nextCoordinate = resolveNextPotentialCoordinate();

        Coordinate coordinateOnGrid = grid.get(nextCoordinate);
        if (coordinateOnGrid instanceof Obstacle) {
            throw new ObstacleEncounteredException(format("Encountered obstacle at %d, %d", nextCoordinate.x, nextCoordinate.y));
        }

        return new Position(currentPosition.direction, nextCoordinate);
    }

    public Coordinate resolveNextPotentialCoordinate() {
        int x = currentPosition.coordinate.x;
        int y = currentPosition.coordinate.y;
        Direction currentDirection = currentPosition.direction;

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

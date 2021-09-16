package com.codurance.command.executable;

import com.codurance.landscape.Coordinate;
import com.codurance.landscape.Grid;
import com.codurance.landscape.Obstacle;
import com.codurance.landscape.ObstacleEncounteredException;
import com.codurance.landscape.Position;

import static java.lang.String.format;

public class MoveCommand extends VectorCommand {
    private final Grid grid;

    public MoveCommand(Position currentPosition, Grid grid) {
        super(currentPosition);

        this.grid = grid;
    }

    @Override
    public Position execute() {
        Coordinate nextCoordinate = currentPosition.direction.move(currentPosition.coordinate);

        Coordinate coordinateOnGrid = grid.get(nextCoordinate);
        if (coordinateOnGrid instanceof Obstacle) {
            throw new ObstacleEncounteredException(format("Encountered obstacle at %d, %d", nextCoordinate.x, nextCoordinate.y));
        }

        return new Position(nextCoordinate, currentPosition.direction);
    }
}

package com.codurance;

import com.codurance.error.ObstacleEncounteredException;
import com.codurance.model.Command;
import com.codurance.model.Coordinate;
import com.codurance.model.Direction;
import com.codurance.model.Grid;
import com.codurance.service.VectorResolver;

import java.util.List;

import static com.codurance.model.Command.LEFT;
import static com.codurance.model.Command.MOVE;
import static com.codurance.model.Command.RIGHT;
import static com.codurance.model.Direction.NORTH;
import static com.codurance.service.CommandParser.parseKnownCommands;
import static java.lang.String.format;

public class Rover {

    private Direction direction = NORTH;
    private Coordinate coordinate = new Coordinate(0, 0);

    private final VectorResolver vectorResolver;

    public Rover(Grid grid) {
        this.vectorResolver = new VectorResolver(grid);
    }

    public String execute(String command) {
        try {
            return attemptCommandExecution(command);
        } catch (ObstacleEncounteredException exception) {
            return "O:" + getFormattedCurrentPosition();
        }
    }

    private String attemptCommandExecution(String command) throws ObstacleEncounteredException {
        List<Command> parsedCommands = parseKnownCommands(command);
        for (Command parsedCommand : parsedCommands) {
            if (parsedCommand == RIGHT)
                direction = vectorResolver.resolveNextRightRotation(direction);

            if (parsedCommand == LEFT)
                direction = vectorResolver.resolveNextLeftRotation(direction);

            if (parsedCommand == MOVE)
                coordinate = vectorResolver.resolveNextCoordinate(coordinate, direction);
        }

        return getFormattedCurrentPosition();
    }

    private String getFormattedCurrentPosition() {
        return format("%d:%d:%s", coordinate.x, coordinate.y, direction.compass);
    }

}


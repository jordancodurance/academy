package com.codurance;

import com.codurance.error.ObstacleEncounteredException;
import com.codurance.model.*;
import com.codurance.service.VectorResolver;

import java.util.List;

import static com.codurance.model.RequestedCommand.LEFT;
import static com.codurance.model.RequestedCommand.MOVE;
import static com.codurance.model.RequestedCommand.RIGHT;
import static com.codurance.model.Direction.NORTH;
import static com.codurance.service.RequestedCommandParser.parseKnownRequestedCommands;
import static java.lang.String.format;

public class Rover {

    private Position position = new Position(NORTH, new Coordinate(0, 0));

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
        List<RequestedCommand> requestedCommands = parseKnownRequestedCommands(command);
        for (RequestedCommand requestedCommand : requestedCommands) {
            Direction currentDirection = position.direction;
            Coordinate currentCoordinate = position.coordinate;

            if (requestedCommand == RIGHT)
                position = new Position(vectorResolver.resolveNextRightRotation(currentDirection), currentCoordinate);

            if (requestedCommand == LEFT)
                position = new Position(vectorResolver.resolveNextLeftRotation(currentDirection), currentCoordinate);

            if (requestedCommand == MOVE)
                position = new Position(currentDirection, vectorResolver.resolveNextCoordinate(currentCoordinate, currentDirection));
        }

        return getFormattedCurrentPosition();
    }

    private String getFormattedCurrentPosition() {
        Direction direction = position.direction;
        Coordinate coordinate = position.coordinate;

        return format("%d:%d:%s", coordinate.x, coordinate.y, direction.compass);
    }

}


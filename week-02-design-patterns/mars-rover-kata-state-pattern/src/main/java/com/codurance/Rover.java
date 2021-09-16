package com.codurance;

import com.codurance.command.executable.VectorCommand;
import com.codurance.command.requested.RequestedCommand;
import com.codurance.landscape.Coordinate;
import com.codurance.landscape.Grid;
import com.codurance.landscape.ObstacleEncounteredException;
import com.codurance.landscape.Position;
import com.codurance.landscape.direction.CompassDirection;
import com.codurance.landscape.direction.NorthDirection;

import java.util.List;

import static com.codurance.command.executable.ExecutableCommandFactory.create;
import static com.codurance.command.requested.RequestedCommandParser.parseKnownRequestedCommands;
import static com.codurance.landscape.Obstacle.OBSTACLE_INDICATOR;
import static java.lang.String.format;

public class Rover {

    private final Grid grid;

    private Position position = new Position(
            new Coordinate(0, 0),
            new NorthDirection()
    );

    public Rover(Grid grid) {
        this.grid = grid;
    }

    public String execute(String command) {
        try {
            List<RequestedCommand> requestedCommands = parseKnownRequestedCommands(command);
            for (RequestedCommand requestedCommand : requestedCommands) {
                VectorCommand executableCommand = create(requestedCommand, position, grid);
                position = executableCommand.execute();
            }

            return getFormattedCurrentPosition();
        } catch (ObstacleEncounteredException exception) {
            return format("%s:%s", OBSTACLE_INDICATOR, getFormattedCurrentPosition());
        }
    }

    private String getFormattedCurrentPosition() {
        Coordinate coordinate = position.coordinate;
        CompassDirection compassDirection = position.direction.getCompass();

        return format("%d:%d:%s", coordinate.x, coordinate.y, compassDirection.value);
    }

}
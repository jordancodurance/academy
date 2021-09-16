package com.codurance;

import com.codurance.command.executable.VectorCommand;
import com.codurance.command.requested.RequestedCommand;
import com.codurance.landscape.Coordinate;
import com.codurance.landscape.direction.Direction;
import com.codurance.landscape.Grid;
import com.codurance.landscape.ObstacleEncounteredException;
import com.codurance.landscape.Position;
import com.codurance.landscape.direction.NorthDirection;

import java.util.List;

import static com.codurance.command.executable.ExecutableCommandFactory.create;
import static com.codurance.command.requested.RequestedCommandParser.parseKnownRequestedCommands;
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
            return "O:" + getFormattedCurrentPosition();
        }
    }

    private String getFormattedCurrentPosition() {
        Direction direction = position.direction;
        Coordinate coordinate = position.coordinate;

        return format("%d:%d:%s", coordinate.x, coordinate.y, direction.getCompass().value);
    }

}
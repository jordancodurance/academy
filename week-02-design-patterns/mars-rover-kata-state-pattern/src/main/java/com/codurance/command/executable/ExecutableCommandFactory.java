package com.codurance.command.executable;

import com.codurance.command.requested.RequestedCommand;
import com.codurance.landscape.Grid;
import com.codurance.landscape.Position;

import static com.codurance.command.requested.RequestedCommand.*;

public class ExecutableCommandFactory {

    public static VectorCommand create(RequestedCommand requestedCommand, Position position, Grid grid) throws UnhandledExecutableCommandCreationException {
        if (requestedCommand == RIGHT) {
            return new RotateRightCommand(position);
        }

        if (requestedCommand == LEFT) {
            return new RotateLeftCommand(position);
        }

        if (requestedCommand == MOVE) {
            return new MoveCommand(position, grid);
        }

        throw new UnhandledExecutableCommandCreationException(String.format("Unhandled executable command creation: %s", requestedCommand.input));
    }

}

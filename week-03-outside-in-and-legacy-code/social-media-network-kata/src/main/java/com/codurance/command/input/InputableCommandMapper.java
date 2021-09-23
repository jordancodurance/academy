package com.codurance.command.input;

import static java.lang.String.format;

public class InputableCommandMapper {

    public InputtedCommand map(String command) {
        InputtedCommand[] availableInputtedCommands = InputtedCommand.values();
        for (InputtedCommand inputtedCommand : availableInputtedCommands) {
            if (inputtedCommand.name.equals(command)) return inputtedCommand;
        }

        throw new InvalidInputtedCommandException(format("Unable to map to %s command", command));
    }

}

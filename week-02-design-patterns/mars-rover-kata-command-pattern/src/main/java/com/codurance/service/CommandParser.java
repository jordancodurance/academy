package com.codurance.service;

import com.codurance.error.UnknownCommandException;
import com.codurance.model.Command;

import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class CommandParser {

    public static List<Command> parseKnownCommands(String command) throws UnknownCommandException {
        return Arrays
                .stream(command.split(""))
                .map(CommandParser::findKnownCommand)
                .collect(toList());
    }

    private static Command findKnownCommand(String character) throws UnknownCommandException {
        List<Command> knownCommands = asList(Command.values());

        return knownCommands
                .stream()
                .filter(value -> value.input.equals(character))
                .findFirst()
                .orElseThrow(() ->
                        new UnknownCommandException(format("Unknown command %s, allowed commands are only L, R, M", character))
                );
    }

}

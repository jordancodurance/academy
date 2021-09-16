package com.codurance.command.requested;

import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class RequestedCommandParser {

    public static List<RequestedCommand> parseKnownRequestedCommands(String command) throws UnknownRequestedCommandException {
        return Arrays
                .stream(command.split(""))
                .map(RequestedCommandParser::findKnownRequestedCommand)
                .collect(toList());
    }

    private static RequestedCommand findKnownRequestedCommand(String character) throws UnknownRequestedCommandException {
        List<RequestedCommand> knownRequestedCommands = asList(RequestedCommand.values());

        return knownRequestedCommands
                .stream()
                .filter(value -> value.input.equals(character))
                .findFirst()
                .orElseThrow(() ->
                        new UnknownRequestedCommandException(format("Unknown command %s, allowed commands are only L, R, M", character))
                );
    }

}

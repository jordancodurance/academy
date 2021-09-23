package com.codurance;

import com.codurance.command.executable.ExecutableCommand;
import com.codurance.command.executable.ExecutableCommandFactory;
import com.codurance.command.input.InputableCommandMapper;
import com.codurance.command.input.InputtedCommand;
import com.codurance.io.Console;

public class SocialNetworkService {

    private static final String REQUEST_SEPARATOR = ": ";

    private final Console console;
    private final InputableCommandMapper inputableCommandMapper;
    private final ExecutableCommandFactory executableCommandFactory;

    public SocialNetworkService(Console console, InputableCommandMapper inputableCommandMapper, ExecutableCommandFactory executableCommandFactory) {
        this.console = console;
        this.inputableCommandMapper = inputableCommandMapper;
        this.executableCommandFactory = executableCommandFactory;
    }

    public void submit() {
        String input = console.readInput();
        String[] request = input.split(REQUEST_SEPARATOR);

        String command = request[0];
        InputtedCommand inputtedCommand = inputableCommandMapper.map(command);

        String requestBody = request[1];
        ExecutableCommand executableCommand = executableCommandFactory.create(inputtedCommand);
        executableCommand.execute(requestBody);
    }

}

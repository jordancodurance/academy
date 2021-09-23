package com.codurance.command.executable;

import com.codurance.command.input.InputtedCommand;
import com.codurance.io.Console;
import com.codurance.time.TimestampProvider;
import com.codurance.user.UserRepository;

import static com.codurance.command.input.InputtedCommand.FOLLOW;
import static com.codurance.command.input.InputtedCommand.POST;
import static com.codurance.command.input.InputtedCommand.READ;
import static com.codurance.command.input.InputtedCommand.WALL;
import static java.lang.String.format;

public class ExecutableCommandFactory {

    private final UserRepository userRepository;
    private final TimestampProvider timestampProvider;
    private final Console console;

    public ExecutableCommandFactory(UserRepository userRepository, TimestampProvider timestampProvider, Console console) {
        this.userRepository = userRepository;
        this.timestampProvider = timestampProvider;
        this.console = console;
    }

    public ExecutableCommand create(InputtedCommand inputtedCommand) {
        if (inputtedCommand == POST) return new ExecutablePostCommand(userRepository);
        if (inputtedCommand == READ) return new ExecutableReadCommand(userRepository, timestampProvider, console);
        if (inputtedCommand == FOLLOW) return new ExecutableFollowCommand(userRepository);
        if (inputtedCommand == WALL) return new ExecutableWallCommand(userRepository, timestampProvider, console);

        throw new ExecutableCommandNotImplementedException(format("Executable command for inputted command '%s' not implemented", inputtedCommand.name));
    }

}

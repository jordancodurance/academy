package command.executable;

import command.requested.RequestedCommand;
import network.Server;
import time.DurationFormatter;
import user.UserRepository;

import static command.requested.RequestedCommand.FOLLOW;
import static command.requested.RequestedCommand.POST;
import static command.requested.RequestedCommand.READ;

public class ExecutableCommandFactory {

    private final UserRepository userRepository;
    private final DurationFormatter durationFormatter;
    private final Server server;

    public ExecutableCommandFactory(UserRepository userRepository, DurationFormatter durationFormatter, Server server) {
        this.userRepository = userRepository;
        this.durationFormatter = durationFormatter;
        this.server = server;
    }

    public ExecutableCommand create(RequestedCommand requestedCommand) {
        if (requestedCommand == POST) return new PostCommand(userRepository);
        if (requestedCommand == READ) return new ReadCommand(userRepository, durationFormatter, server);
        if(requestedCommand == FOLLOW) return new FollowCommand(userRepository);
        return new WallCommand(userRepository, durationFormatter, server);
    }

}

import command.executable.ExecutableCommand;
import command.executable.ExecutableCommandFactory;
import command.requested.RequestedCommand;
import network.Server;

import static command.requested.RequestedCommandMapper.map;

public class SocialNetworkApplication {

    private static final String REQUEST_SEPARATOR = ": ";

    private final Server server;
    private final ExecutableCommandFactory executableCommandFactory;

    public SocialNetworkApplication(Server server, ExecutableCommandFactory executableCommandFactory) {
        this.server = server;
        this.executableCommandFactory = executableCommandFactory;
    }

    public void handleRequest() {
        String request = server.readNextRequest();
        String[] parsedRequest = request.split(REQUEST_SEPARATOR);

        String command = parsedRequest[0];
        RequestedCommand requestedCommand = map(command);
        ExecutableCommand executableCommand = executableCommandFactory.create(requestedCommand);

        String body = parsedRequest[1];
        executableCommand.execute(body);
    }

}

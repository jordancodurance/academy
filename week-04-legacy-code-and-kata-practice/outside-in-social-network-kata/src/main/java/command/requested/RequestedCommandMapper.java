package command.requested;

import java.util.List;

import static java.lang.String.format;

public class RequestedCommandMapper {

    public static RequestedCommand map(String command) {
        List<RequestedCommand> requestedCommands = List.of(RequestedCommand.values());
        for(RequestedCommand requestedCommand : requestedCommands) {
            if (requestedCommand.command.equals(command)) return requestedCommand;
        }
        throw new RequestedCommandNotFoundException(format("Unable to map to requested command: %s", command));
    }

}

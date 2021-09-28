package command.requested;

public enum RequestedCommand {
    POST("posting"),
    READ("reading"),
    FOLLOW("following"),
    WALL("wall");

    public final String command;

    RequestedCommand(String command) {
        this.command = command;
    }
}

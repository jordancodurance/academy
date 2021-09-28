package command.requested;

public class RequestedCommandNotFoundException extends RuntimeException {

    public RequestedCommandNotFoundException(String message) {
        super(message);
    }

}

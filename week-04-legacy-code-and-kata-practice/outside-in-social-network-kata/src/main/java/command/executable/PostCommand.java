package command.executable;

import user.NoExistingUserException;
import user.UserRepository;

public class PostCommand implements ExecutableCommand {

    private static final String POST_REQUEST_SEPARATOR = " -> ";

    private final UserRepository userRepository;

    public PostCommand(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(String request) {
        String[] postRequest = request.split(POST_REQUEST_SEPARATOR);

        String username = postRequest[0];
        if (!userRepository.isExistingUser(username)) throw new NoExistingUserException();

        String message = postRequest[1];
        userRepository.updateTimeline(username, message);
    }

}

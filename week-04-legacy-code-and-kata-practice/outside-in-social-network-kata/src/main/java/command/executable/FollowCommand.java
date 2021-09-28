package command.executable;

import user.NoExistingUserException;
import user.UserRepository;

public class FollowCommand implements ExecutableCommand {

    private static final String FOLLOW_SEPARATOR = " follows ";

    private final UserRepository userRepository;

    public FollowCommand(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(String request) {
        String[] followRequest = request.split(FOLLOW_SEPARATOR);

        String username = followRequest[0];
        if (!userRepository.isExistingUser(username)) throw new NoExistingUserException();

        String usernameToFollow = followRequest[1];
        if (!userRepository.isExistingUser(usernameToFollow)) throw new NoExistingUserException();

        userRepository.updateFollows(username, usernameToFollow);
    }

}

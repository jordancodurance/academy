package com.codurance.command.executable;

import com.codurance.user.UserRepository;

public class ExecutableFollowCommand extends ExecutableCommand {

    private static final String FOLLOWS_SEPARATOR = " follows ";

    private final UserRepository userRepository;

    public ExecutableFollowCommand(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(String requestBody) {
        String[] follow = requestBody.split(FOLLOWS_SEPARATOR);
        String subscriberUsername = follow[0];
        String toFollowUsername = follow[1];

        userRepository.followUser(subscriberUsername, toFollowUsername);
    }

}

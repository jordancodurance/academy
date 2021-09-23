package com.codurance.command.executable;

import com.codurance.user.UserRepository;

public class ExecutablePostCommand extends ExecutableCommand {

    private static final String POST_SEPARATOR = " -> ";

    private final UserRepository userRepository;

    public ExecutablePostCommand(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(String requestBody) {
        String[] post = requestBody.split(POST_SEPARATOR);
        String username = post[0];
        String message = post[1];

        userRepository.updateTimeline(username, message);
    }

}

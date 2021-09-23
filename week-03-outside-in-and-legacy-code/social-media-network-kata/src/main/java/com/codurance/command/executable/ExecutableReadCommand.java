package com.codurance.command.executable;

import com.codurance.io.Console;
import com.codurance.time.TimestampProvider;
import com.codurance.user.Post;
import com.codurance.user.User;
import com.codurance.user.UserRepository;
import org.ocpsoft.prettytime.PrettyTime;

import static java.lang.String.format;

public class ExecutableReadCommand extends ExecutableCommand {

    private final UserRepository userRepository;
    private final TimestampProvider timestampProvider;
    private final Console console;

    public ExecutableReadCommand(UserRepository userRepository, TimestampProvider timestampProvider, Console console) {
        this.userRepository = userRepository;
        this.timestampProvider = timestampProvider;
        this.console = console;
    }

    @Override
    public void execute(String requestBody) {
        User user = userRepository.get(requestBody);
        for (Post post : user.timeline) {
            PrettyTime prettyTime = new PrettyTime(timestampProvider.now());
            String duration = prettyTime.format(post.postedOn);
            String output = format("%s (%s)", post.message, duration);
            console.output(output);
        }
    }

}

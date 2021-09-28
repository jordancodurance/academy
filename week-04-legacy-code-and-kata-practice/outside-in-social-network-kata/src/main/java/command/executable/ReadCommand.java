package command.executable;

import network.Server;
import time.DurationFormatter;
import user.NoExistingUserException;
import user.Post;
import user.UserRepository;

import java.time.Instant;
import java.util.List;

import static java.lang.String.format;

public class ReadCommand implements ExecutableCommand {


    private final UserRepository userRepository;
    private final DurationFormatter durationFormatter;
    private final Server server;

    public ReadCommand(UserRepository userRepository, DurationFormatter durationFormatter, Server server) {
        this.userRepository = userRepository;
        this.durationFormatter = durationFormatter;
        this.server = server;
    }

    @Override
    public void execute(String request) {
        if (!userRepository.isExistingUser(request)) throw new NoExistingUserException();

        List<Post> requestedTimeline = userRepository.findChronologicalTimeline(request);
        if (requestedTimeline.isEmpty()) {
            String response = format("%s hasn't posted to their timeline yet", request);
            server.outputResponse(response);
        }

        readFromTimeline(requestedTimeline);
    }

    private void readFromTimeline(List<Post> timeline) {
        for (Post post : timeline) {
            Instant postedOn = post.getPostedOn();
            String duration = durationFormatter.fromNow(postedOn);
            String response = format("%s (%s)", post.getMessage(), duration);
            server.outputResponse(response);
        }
    }

}

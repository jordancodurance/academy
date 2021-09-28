package command.executable;

import network.Server;
import time.DurationFormatter;
import user.NoExistingUserException;
import user.Post;
import user.User;
import user.UserRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class WallCommand implements ExecutableCommand {


    private final UserRepository userRepository;
    private final DurationFormatter durationFormatter;
    private final Server server;

    public WallCommand(UserRepository userRepository, DurationFormatter durationFormatter, Server server) {
        this.userRepository = userRepository;
        this.durationFormatter = durationFormatter;
        this.server = server;
    }

    @Override
    public void execute(String request) {
        Optional<User> user = userRepository.findUser(request);
        if (user.isEmpty()) throw new NoExistingUserException();

        List<Post> postsOnWall = getPostsOnWall(user.get());
        if (postsOnWall.isEmpty())
            server.outputResponse("No posts on your wall");

        readPosts(postsOnWall);
    }

    private List<Post> getPostsOnWall(User user) {
        List<Post> postsOnWall = new ArrayList<>();
        List<String> usernamesForWall = getUsernamesForWall(user);
        for (String username : usernamesForWall) {
            List<Post> userTimeline = userRepository.findChronologicalTimeline(username);
            postsOnWall.addAll(userTimeline);
        }

        return postsOnWall
                .stream()
                .sorted(comparing(Post::getPostedOn).reversed())
                .collect(toList());
    }

    private List<String> getUsernamesForWall(User user) {
        List<String> usernamesOnWall = new ArrayList<>();
        usernamesOnWall.add(user.getUsername());
        usernamesOnWall.addAll(user.getFollows());

        return usernamesOnWall;
    }

    private void readPosts(List<Post> posts) {
        for (Post post : posts) {
            Instant postedOn = post.getPostedOn();
            String duration = durationFormatter.fromNow(postedOn);
            String response = format("%s - %s (%s)", post.getPostedBy(), post.getMessage(), duration);
            server.outputResponse(response);
        }
    }

}

package user;

import time.TimestampProvider;
import user.User.UserBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.reverse;
import static java.util.Optional.ofNullable;

public class InMemoryUserRepository implements UserRepository {

    private final HashMap<String, User> userStore = new HashMap<>();

    private final TimestampProvider timestampProvider;

    public InMemoryUserRepository(TimestampProvider timestampProvider) {
        this.timestampProvider = timestampProvider;
    }

    @Override
    public boolean isExistingUser(String username) {
        return userStore.containsKey(username);
    }

    @Override
    public void createUser(String username) {
        List<Post> timeline = new ArrayList<>();
        List<String> follows = new ArrayList<>();
        User user = new UserBuilder().withUsername(username).withFollows(follows).withTimeline(timeline).createUser();
        userStore.put(username, user);
    }

    @Override
    public Optional<User> findUser(String username) {
        User user = userStore.get(username);

        return ofNullable(user);
    }

    @Override
    public List<Post> findChronologicalTimeline(String username) {
        User user = userStore.get(username);
        List<Post> timeline = user.getTimeline();
        reverse(timeline);

        return timeline;
    }

    @Override
    public void updateTimeline(String username, String message) {
        User user = userStore.get(username);
        Post post = new Post(message, timestampProvider.now(), username);
        user.getTimeline().add(post);
    }

    @Override
    public void updateFollows(String username, String usernameToFollow) {
        User user = userStore.get(username);
        user.getFollows().add(usernameToFollow);
    }
}

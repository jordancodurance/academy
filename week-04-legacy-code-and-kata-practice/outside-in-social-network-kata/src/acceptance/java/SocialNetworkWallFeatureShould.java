import command.executable.ExecutableCommandFactory;
import network.Server;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import time.DurationFormatter;
import time.TimestampProvider;
import user.InMemoryUserRepository;
import user.UserRepository;

import java.time.Instant;
import java.time.LocalDateTime;

import static java.lang.String.format;
import static java.time.ZoneOffset.UTC;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SocialNetworkWallFeatureShould {

    private final Instant NOW = toInstant("2021-09-23T10:15:00");

    private final TimestampProvider timestampProvider = mock(TimestampProvider.class);
    private final Server server = mock(Server.class);
    private final UserRepository userRepository = new InMemoryUserRepository(timestampProvider);
    private final DurationFormatter durationFormatter = new DurationFormatter(timestampProvider);
    private final ExecutableCommandFactory executableCommandFactory = new ExecutableCommandFactory(userRepository, durationFormatter, server);

    private final SocialNetworkApplication application = new SocialNetworkApplication(server, executableCommandFactory);

    @Test
    void show_wall_with_no_subscription_for_new_users() {
        createUser("Jeff");

        readFromWall("Jeff");

        verify(server).outputResponse("No posts on your wall");
    }

    @Test
    void show_subscriptions_on_wall_after_charlie_follows_alice() {
        given(timestampProvider.now()).willReturn(toInstant("2021-09-23T10:14:58"), toInstant("2021-09-23T10:10:00"), NOW);
        setUpUserCharlie();
        setUpUserAlice();
        followUser("Charlie", "Alice");

        readFromWall("Charlie");

        InOrder inOrder = inOrder(server);
        inOrder.verify(server).outputResponse("Charlie - I'm in New York today! Anyone want to have a coffee? (2 seconds ago)");
        inOrder.verify(server).outputResponse("Alice - I love the weather today (5 minutes ago)");
    }

    @Test
    void show_subscriptions_on_wall_after_charlie_follows_alice_and_bob() {
        given(timestampProvider.now()).willReturn(toInstant("2021-09-23T10:14:45"), toInstant("2021-09-23T10:13:00"), toInstant("2021-09-23T10:14:00"), toInstant("2021-09-23T10:10:00"), NOW);
        setUpUserCharlie();
        setUpUserBob();
        setUpUserAlice();
        followUser("Charlie", "Alice");
        followUser("Charlie", "Bob");

        readFromWall("Charlie");

        InOrder inOrder = inOrder(server);
        inOrder.verify(server).outputResponse("Charlie - I'm in New York today! Anyone want to have a coffee? (15 seconds ago)");
        inOrder.verify(server).outputResponse("Bob - Good game though. (1 minute ago)");
        inOrder.verify(server).outputResponse("Bob - Damn! We lost! (2 minutes ago)");
        inOrder.verify(server).outputResponse("Alice - I love the weather today (5 minutes ago)");
    }

    private Instant toInstant(String dateTime) {
        LocalDateTime parsedDateTime = LocalDateTime.parse(dateTime);
        return parsedDateTime.toInstant(UTC);
    }

    private void setUpUserAlice() {
        createUser("Alice");
        postToTimeline("Alice", "I love the weather today");
    }

    private void setUpUserBob() {
        createUser("Bob");
        postToTimeline("Bob", "Damn! We lost!");
        postToTimeline("Bob", "Good game though.");
    }

    private void setUpUserCharlie() {
        createUser("Charlie");
        postToTimeline("Charlie", "I'm in New York today! Anyone want to have a coffee?");
    }

    private void createUser(String username) {
        userRepository.createUser(username);
    }

    private void postToTimeline(String username, String message) {
        String request = format("posting: %s -> %s", username, message);
        given(server.readNextRequest()).willReturn(request);
        application.handleRequest();
    }

    private void followUser(String username, String usernameToFollow) {
        String request = format("following: %s follows %s", username, usernameToFollow);
        given(server.readNextRequest()).willReturn(request);
        application.handleRequest();
    }

    private void readFromWall(String username) {
        String request = String.format("wall: %s", username);
        given(server.readNextRequest()).willReturn(request);
        application.handleRequest();
    }

}

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

public class SocialNetworkTimelineFeatureShould {

    private final Instant NOW = toInstant("2021-09-23T10:15:00");

    private final TimestampProvider timestampProvider = mock(TimestampProvider.class);
    private final Server server = mock(Server.class);
    private final UserRepository userRepository = new InMemoryUserRepository(timestampProvider);
    private final DurationFormatter durationFormatter = new DurationFormatter(timestampProvider);
    private final ExecutableCommandFactory executableCommandFactory = new ExecutableCommandFactory(userRepository, durationFormatter, server);

    private final SocialNetworkApplication application = new SocialNetworkApplication(server, executableCommandFactory);

    @Test
    void allow_users_to_read_from_timeline_of_new_user_charlie() {
        createUser("Charlie");

        readFromTimeline("Charlie");

        verify(server).outputResponse("Charlie hasn't posted to their timeline yet");
    }

    @Test
    void allow_users_to_read_messages_posted_from_the_timeline_of_alice() {
        given(timestampProvider.now()).willReturn(toInstant("2021-09-23T10:10:00"), NOW);
        createUser("Alice");
        postToTimeline("Alice", "I love the weather today");

        readFromTimeline("Alice");

        verify(server).outputResponse("I love the weather today (5 minutes ago)");
    }

    @Test
    void allow_users_to_read_messages_posted_from_the_timeline_of_bob() {
        given(timestampProvider.now()).willReturn(toInstant("2021-09-23T10:13:00"), toInstant("2021-09-23T10:14:00"), NOW);
        createUser("Bob");
        postToTimeline("Bob", "Damn! We lost!");
        postToTimeline("Bob", "Good game though.");

        readFromTimeline("Bob");

        InOrder inOrder = inOrder(server);
        inOrder.verify(server).outputResponse("Good game though. (1 minute ago)");
        inOrder.verify(server).outputResponse("Damn! We lost! (2 minutes ago)");
    }

    private Instant toInstant(String dateTime) {
        LocalDateTime parsedDateTime = LocalDateTime.parse(dateTime);
        return parsedDateTime.toInstant(UTC);
    }

    private void createUser(String username) {
        userRepository.createUser(username);
    }

    private void postToTimeline(String username, String message) {
        String request = format("posting: %s -> %s", username, message);
        given(server.readNextRequest()).willReturn(request);
        application.handleRequest();
    }

    private void readFromTimeline(String username) {
        String request = String.format("reading: %s", username);
        given(server.readNextRequest()).willReturn(request);
        application.handleRequest();
    }

}

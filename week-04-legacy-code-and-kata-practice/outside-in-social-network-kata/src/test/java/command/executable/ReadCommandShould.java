package command.executable;

import network.Server;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import time.DurationFormatter;
import user.NoExistingUserException;
import user.Post;
import user.UserRepository;

import java.time.Instant;
import java.util.List;

import static java.time.Instant.ofEpochMilli;
import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ReadCommandShould {

    @Mock
    private UserRepository userRepository;

    @Mock
    private DurationFormatter durationFormatter;

    @Mock
    private Server server;

    private ReadCommand target;

    @BeforeEach
    void setUp() {
        target = new ReadCommand(userRepository, durationFormatter, server);
    }

    @Test
    void
    declare_default_no_posts_yet_for_user_with_empty_timeline() {
        given(userRepository.isExistingUser("Alice")).willReturn(true);

        target.execute("Alice");

        verify(server).outputResponse("Alice hasn't posted to their timeline yet");
    }

    @Test void
    read_posts_from_an_existing_users_timeline() {
        Instant messagePostedOn = ofEpochMilli(1632753975283L);
        List<Post> timeline = of(
                new Post("I love the weather today", messagePostedOn, "Alice")
        );
        given(userRepository.isExistingUser("Alice")).willReturn(true);
        given(userRepository.findChronologicalTimeline("Alice")).willReturn(timeline);
        given(durationFormatter.fromNow(messagePostedOn)).willReturn("5 minutes ago");

        target.execute("Alice");

        verify(server).outputResponse("I love the weather today (5 minutes ago)");
    }

    @Test void
    block_reading_a_timeline_for_a_user_that_does_not_exist() {
        assertThrows(NoExistingUserException.class, () -> target.execute("Alice"));
    }
}

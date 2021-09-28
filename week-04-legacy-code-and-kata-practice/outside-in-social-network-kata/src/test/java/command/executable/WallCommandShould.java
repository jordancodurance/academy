package command.executable;

import network.Server;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import time.DurationFormatter;
import user.NoExistingUserException;
import user.Post;
import user.User;
import user.UserRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static java.time.Instant.ofEpochMilli;
import static java.util.Collections.emptyList;
import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class WallCommandShould {

    @Mock
    private UserRepository userRepository;

    @Mock
    private DurationFormatter durationFormatter;

    @Mock
    private Server server;

    WallCommand target;

    @BeforeEach
    void setUp() {
        target = new WallCommand(userRepository, durationFormatter, server);
    }

    @Test
    void declare_default_no_posts_yet_for_user_with_empty_wall() {
        User charlie = new User.UserBuilder().withUsername("Charlie").withFollows(emptyList()).createUser();
        given(userRepository.findUser("Charlie")).willReturn(Optional.of(charlie));

        target.execute("Charlie");

        verify(server).outputResponse("No posts on your wall");
    }

    @Test
    void allow_existing_user_to_read_from_their_wall() {
        withAliceTimelineSetUp();
        withCharlieTimelineSetUp();
        User charlie = new User.UserBuilder().withUsername("Charlie").withFollows(of("Alice")).createUser();
        given(userRepository.findUser("Charlie")).willReturn(Optional.of(charlie));

        target.execute("Charlie");

        InOrder inOrder = inOrder(server);
        inOrder.verify(server).outputResponse("Charlie - I'm in New York today! Anyone want to have a coffee? (2 seconds ago)");
        inOrder.verify(server).outputResponse("Alice - I love the weather today (5 minutes ago)");
    }

    @Test
    void block_reading_wall_for_user_that_does_not_exist() {
        assertThrows(NoExistingUserException.class, () -> target.execute("Alice"));
    }

    private void withAliceTimelineSetUp() {
        Instant alicePostedOn = ofEpochMilli(1632824813564L);
        List<Post> alicesTimeline = of(
                new Post("I love the weather today", alicePostedOn, "Alice")
        );
        given(userRepository.findChronologicalTimeline("Alice")).willReturn(alicesTimeline);
        given(durationFormatter.fromNow(alicePostedOn)).willReturn("5 minutes ago");
    }

    private void withCharlieTimelineSetUp() {
        Instant charliePostedOn = ofEpochMilli(1632824821352L);
        List<Post> charliesTimeline = of(
                new Post("I'm in New York today! Anyone want to have a coffee?", charliePostedOn, "Charlie")
        );
        given(userRepository.findChronologicalTimeline("Charlie")).willReturn(charliesTimeline);
        given(durationFormatter.fromNow(charliePostedOn)).willReturn("2 seconds ago");
    }

}

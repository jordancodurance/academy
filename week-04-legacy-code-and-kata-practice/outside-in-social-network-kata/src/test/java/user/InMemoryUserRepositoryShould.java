package user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import time.TimestampProvider;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static java.time.Instant.ofEpochMilli;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class InMemoryUserRepositoryShould {

    @Mock
    private TimestampProvider timestampProvider;

    private InMemoryUserRepository target;

    @BeforeEach
    void setUp() {
        target = new InMemoryUserRepository(timestampProvider);
    }

    @Test void
    determine_a_user_exists_when_user_with_username_was_created() {
        target.createUser("sam");

        assertTrue(target.isExistingUser("sam"));
    }

    @Test void
    determine_a_user_does_not_exist_when_user_with_username_has_not_been_created_yet() {
        assertFalse(target.isExistingUser("random-username"));
    }

    @Test void
    create_a_new_user_with_an_empty_timeline_and_no_follows() {
        target.createUser("username");

        User user = target.findUser("username").get();

        assertEquals("username", user.getUsername());
        assertTrue(user.getTimeline().isEmpty());
        assertTrue(user.getFollows().isEmpty());
    }

    @Test void
    find_user_created_using_username() {
        target.createUser("username");

        Optional<User> user = target.findUser("username");

        assertTrue(user.isPresent());
    }

    @Test void
    handle_finding_users_that_have_not_been_created() {
        Optional<User> user = target.findUser("not-a-user");

        assertFalse(user.isPresent());
    }

    @Test void
    add_timestamped_post_to_users_timeline() {
        Instant postedOn = ofEpochMilli(1632759141949L);
        target.createUser("jordan");
        given(timestampProvider.now()).willReturn(postedOn);

        target.updateTimeline("jordan", "bad game");

        User user = target.findUser("jordan").get();
        Post post = user.getTimeline().get(0);
        assertPostContents(post, "bad game", postedOn, "jordan");
    }

    @Test void
    find_chronological_timeline_of_created_user() {
        Instant firstPostedOn = ofEpochMilli(1632759141949L);
        Instant secondPostedOn = ofEpochMilli(1632759141949L);
        target.createUser("bob");
        given(timestampProvider.now()).willReturn(firstPostedOn, secondPostedOn);
        target.updateTimeline("bob", "my first post");
        target.updateTimeline("bob", "my second post");

        List<Post> timeline = target.findChronologicalTimeline("bob");

        assertPostContents(timeline.get(0), "my second post", secondPostedOn, "bob");
        assertPostContents(timeline.get(1), "my first post", firstPostedOn, "bob");
    }

    @Test void
    update_follows() {
        target.createUser("Charlie");

        target.updateFollows("Charlie", "Bob");

        User user = target.findUser("Charlie").get();
        assertEquals("Bob", user.getFollows().get(0));
    }

    private void assertPostContents(Post post, String expectedMessage, Instant expectedPostedOn, String expectedPostedBy) {
        assertEquals(expectedMessage, post.getMessage());
        assertEquals(expectedPostedOn, post.getPostedOn());
        assertEquals(expectedPostedBy, post.getPostedBy());
    }

}
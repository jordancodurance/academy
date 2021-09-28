package command.executable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import user.NoExistingUserException;
import user.UserRepository;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PostCommandShould {

    @Mock
    private UserRepository userRepository;

    private PostCommand target;

    @BeforeEach
    void setUp() {
        target = new PostCommand(userRepository);
    }

    @Test void
    allow_existing_users_to_post_message_to_their_timeline() {
        given(userRepository.isExistingUser("Alice")).willReturn(true);

        target.execute("Alice -> I love the weather today");

        verify(userRepository).updateTimeline("Alice", "I love the weather today");
    }

    @Test void
    block_users_that_do_not_exist_from_posting() {
        assertThrows(NoExistingUserException.class, () -> target.execute("Alice -> I love the weather today"));
    }

}
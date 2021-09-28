package command.executable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import user.NoExistingUserException;
import user.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FollowCommandShould {

    @Mock
    private UserRepository userRepository;

    private FollowCommand target;

    @BeforeEach
    void setUp() {
        target = new FollowCommand(userRepository);
    }

    @Test
    void allow_existing_user_to_follow_another_existing_user() {
        given(userRepository.isExistingUser("Alice")).willReturn(true);
        given(userRepository.isExistingUser("Charlie")).willReturn(true);

        target.execute("Charlie follows Alice");

        verify(userRepository).updateFollows("Charlie", "Alice");
    }

    @Test
    void block_follow_request_when_user_following_does_not_exist() {
        assertThrows(NoExistingUserException.class, () -> target.execute("Alice follows Charlie"));
    }

    @Test
    void block_follow_request_when_user_to_follow_does_not_exist() {
        given(userRepository.isExistingUser("Alice")).willReturn(true);

        assertThrows(NoExistingUserException.class, () -> target.execute("Alice follows Charlie"));
    }

}
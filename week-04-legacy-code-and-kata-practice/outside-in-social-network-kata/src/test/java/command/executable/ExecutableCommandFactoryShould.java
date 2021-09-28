package command.executable;

import network.Server;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import time.DurationFormatter;
import user.UserRepository;

import static command.requested.RequestedCommand.FOLLOW;
import static command.requested.RequestedCommand.POST;
import static command.requested.RequestedCommand.READ;
import static command.requested.RequestedCommand.WALL;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ExecutableCommandFactoryShould {

    @Mock
    private UserRepository userRepository;

    @Mock
    private DurationFormatter durationFormatter;

    @Mock
    private Server server;

    ExecutableCommandFactory target = new ExecutableCommandFactory(userRepository, durationFormatter, server);

    @Test
    void create_executable_post_command_for_post_request() {
        ExecutableCommand executableCommand = target.create(POST);

        assertTrue(executableCommand instanceof PostCommand);
    }

    @Test
    void create_executable_read_command_for_read_request() {
        ExecutableCommand executableCommand = target.create(READ);

        assertTrue(executableCommand instanceof ReadCommand);
    }

    @Test
    void create_executable_follow_command_for_follow_request() {
        ExecutableCommand executableCommand = target.create(FOLLOW);

        assertTrue(executableCommand instanceof FollowCommand);
    }

    @Test
    void create_executable_wall_command_for_wall_request() {
        ExecutableCommand executableCommand = target.create(WALL);

        assertTrue(executableCommand instanceof WallCommand);
    }

}

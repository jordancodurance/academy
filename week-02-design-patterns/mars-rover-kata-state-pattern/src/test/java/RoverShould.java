import com.codurance.Rover;
import com.codurance.command.requested.UnknownRequestedCommandException;
import com.codurance.landscape.Grid;
import com.codurance.landscape.Obstacle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoverShould {

    private final Grid grid = new Grid();
    private final Rover target = new Rover(grid);

    @ParameterizedTest
    @CsvSource({
            "R, 0:0:E",
            "RR, 0:0:S",
            "RRR, 0:0:W",
            "RRRR, 0:0:N",
            "RRRRR, 0:0:E"
    })
    void rotate_right(String command, String expectedOutput) {
        assertEquals(expectedOutput, target.execute(command));
    }

    @ParameterizedTest
    @CsvSource({
            "L, 0:0:W",
            "LL, 0:0:S",
            "LLL, 0:0:E",
            "LLLL, 0:0:N",
            "LLLLL, 0:0:W"
    })
    void rotate_left(String command, String expectedOutput) {
        assertEquals(expectedOutput, target.execute(command));
    }

    @ParameterizedTest
    @CsvSource({
            "M, 0:1:N",
            "RRM, 0:9:S"
    })
    void move_latitudinally(String command, String expectedOutput) {
        assertEquals(expectedOutput, target.execute(command));
    }

    @ParameterizedTest
    @CsvSource({
            "LM, 9:0:W",
            "RM, 1:0:E"
    })
    void move_longitudinally(String command, String expectedOutput) {
        assertEquals(expectedOutput, target.execute(command));
    }

    @Test void
    avoid_obstacle() {
        grid.add(new Obstacle(0, 3));
        Rover target = new Rover(grid);

        assertEquals("O:0:2:N", target.execute("MMMM"));
    }

    @ParameterizedTest
    @CsvSource({
            "MMRMMLM, 2:3:N",
            "MMMMMMMMMM, 0:0:N"
    })
    void execute_complex_command(String command, String expectedOutput) {
        assertEquals(expectedOutput, target.execute(command));
    }

    @Test void
    reject_unknown_command() {
        UnknownRequestedCommandException exception = assertThrows(UnknownRequestedCommandException.class, () -> target.execute("X"));

        assertEquals("Unknown command X, allowed commands are only L, R, M", exception.getMessage());
    }
}

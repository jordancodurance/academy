import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.params.provider.Arguments.of;

class GameOfLifeShould {

    @ParameterizedTest
    @MethodSource("provideOneDimensionalBoard")
    public void generate_the_next_generation_for_a_simple_board(boolean[][] board, boolean[][] expectedBoard) {
        GameOfLife game = new GameOfLife(board);

        game.nextGeneration();

        assertArrayEquals(expectedBoard, game.getCurrentGeneration());
    }

    private static Stream<Arguments> provideOneDimensionalBoard() {
        return Stream.of(
                of(new boolean[][]{{true}}, new boolean[][]{{false}}),
                of(new boolean[][]{{true, true}}, new boolean[][]{{false, false}}),
                of(new boolean[][]{{true, true, true}}, new boolean[][]{{false, false, false}}),
                of(new boolean[][]{{true, true, true, true}}, new boolean[][]{{false, false, false, false}}),
                of(new boolean[][]{{false, true, true, false}}, new boolean[][]{{false, false, false, false}})
        );
    }

    @Test
    public void generate_the_next_generation_for_a_complex_Board() {
        boolean[][] board = {
                {false, true, true, false},
                {true, true, true, true},
                {true, true, true, true},
                {false, true, true, false},
        };
        GameOfLife game = new GameOfLife(board);
        boolean[][] expectedBoard = {
                {true, false, false, true},
                {false, false, false, false},
                {false, false, false, false},
                {true, false, false, true}
        };

        game.nextGeneration();

        assertArrayEquals(expectedBoard, game.getCurrentGeneration());
    }

}
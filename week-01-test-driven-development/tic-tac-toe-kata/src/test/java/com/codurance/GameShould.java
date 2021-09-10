package com.codurance;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.codurance.BoardToken.EMPTY_SPACE;
import static com.codurance.BoardToken.PLAYER_ONE_TOKEN;
import static com.codurance.BoardToken.PLAYER_TWO_TOKEN;
import static com.codurance.GameStatus.DRAWN;
import static com.codurance.GameStatus.NOT_STARTED;
import static com.codurance.GameStatus.PLAYER_ONE_WON;
import static com.codurance.GameStatus.PLAYER_TWO_WON;
import static com.codurance.GameStatus.PLAYING;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameShould {

    private final Game game = new Game();

    @Test void
    have_a_not_started_status_when_no_turns_taken() {
        assertEquals(NOT_STARTED, game.getCurrentStatus());
    }

    @Test void
    have_a_playing_status_when_a_turn_is_made() {
        game.turn(0, 0);

        assertEquals(PLAYING, game.getCurrentStatus());
    }

    @Test void
    place_the_first_turn_on_the_board_for_player_one() {
        BoardToken[][] expectedBoard = {
                {PLAYER_ONE_TOKEN, EMPTY_SPACE, EMPTY_SPACE},
                {EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE},
                {EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE}
        };

        BoardToken[][] updatedBoard = game.turn(0, 0);

        assertArrayEquals(expectedBoard, updatedBoard);
    }

    @Test void
    place_the_second_turn_on_the_board_for_player_two() {
        BoardToken[][] expectedBoard = {
                {PLAYER_ONE_TOKEN, PLAYER_TWO_TOKEN, EMPTY_SPACE},
                {EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE},
                {EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE}
        };

        game.turn(0, 0);
        BoardToken[][] updatedBoard = game.turn(0, 1);

        assertArrayEquals(expectedBoard, updatedBoard);
    }

    @Test void
    place_the_third_turn_on_the_board_for_player_one() {
        BoardToken[][] expectedBoard = {
                {PLAYER_ONE_TOKEN, PLAYER_TWO_TOKEN, PLAYER_ONE_TOKEN},
                {EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE},
                {EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE}
        };

        game.turn(0, 0);
        game.turn(0, 1);
        BoardToken[][] updatedBoard = game.turn(0, 2);

        assertArrayEquals(expectedBoard, updatedBoard);
    }

    @Test void
    only_allow_a_play_on_an_empty_space_on_the_board() {
        game.turn(0, 0);

        SpaceAlreadyUsedException exception = assertThrows(SpaceAlreadyUsedException.class, () -> game.turn(0, 0));
        Assertions.assertEquals("Can not place O at 0 , 0", exception.getMessage());
    }

    @Test void
    have_a_winning_status_for_player_with_a_horizontal_play_of_3() {
        game.turn(0, 0);
        game.turn(1, 0);
        game.turn(0, 1);
        game.turn(1, 1);
        game.turn(0, 2);

        assertEquals(PLAYER_ONE_WON, game.getCurrentStatus());
    }

    @Test
    void have_a_winning_status_for_player_with_a_vertical_play_of_3() {
        game.turn(0, 1);
        game.turn(0, 0);
        game.turn(1, 1);
        game.turn(1, 0);
        game.turn(2, 1);
        game.turn(2, 0);

        assertEquals(PLAYER_TWO_WON, game.getCurrentStatus());
    }

    @Test
    void have_a_winning_status_for_player_with_a_diagonal_right_play_of_3() {
        game.turn(0, 0);
        game.turn(1, 0);
        game.turn(1, 1);
        game.turn(2, 0);
        game.turn(2, 2);

        assertEquals(PLAYER_ONE_WON, game.getCurrentStatus());
    }

    @Test
    void have_a_winning_status_for_player_with_a_diagonal_left_play_of_3() {
        game.turn(0, 0);
        game.turn(2, 0);
        game.turn(1, 0);
        game.turn(1, 1);
        game.turn(2, 2);
        game.turn(0, 2);

        assertEquals(PLAYER_TWO_WON, game.getCurrentStatus());
    }

    @Test
    void have_a_drawn_status_when_all_the_spaces_on_the_board_are_filled_and_there_is_no_winning_combination() {
        game.turn(0, 0);
        game.turn(1, 0);
        game.turn(2, 0);
        game.turn(0, 1);
        game.turn(2, 1);
        game.turn(1, 1);
        game.turn(0, 2);
        game.turn(2, 2);
        game.turn(1, 2);

        assertEquals(DRAWN, game.getCurrentStatus());
    }

}
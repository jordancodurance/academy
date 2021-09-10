package com.codurance;

import java.util.ArrayList;
import java.util.List;

import static com.codurance.BoardToken.EMPTY_SPACE;
import static com.codurance.BoardToken.PLAYER_ONE_TOKEN;
import static com.codurance.BoardToken.PLAYER_TWO_TOKEN;
import static com.codurance.GameStatusResolver.determineStatus;
import static java.lang.String.format;

public class Game {
    private final BoardToken[][] gameBoard = {
            {EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE},
            {EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE},
            {EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE}
    };
    private final List<BoardToken> history = new ArrayList<>();

    public GameStatus getCurrentStatus() {
        return determineStatus(history, gameBoard);
    }

    public BoardToken[][] turn(int xCoordinate, int yCoordinate) {
        BoardToken nextTurn = determineNextTurn();

        move(xCoordinate, yCoordinate, nextTurn);

        return gameBoard;
    }

    private BoardToken determineNextTurn() {
        if (history.isEmpty()) return PLAYER_ONE_TOKEN;

        BoardToken lastTurn = history.get(history.size() - 1);

        return lastTurn == PLAYER_ONE_TOKEN ? PLAYER_TWO_TOKEN : PLAYER_ONE_TOKEN;
    }

    private void move(int xCoordinate, int yCoordinate, BoardToken token) {
        if (gameBoard[xCoordinate][yCoordinate] != EMPTY_SPACE)
            throw new SpaceAlreadyUsedException(format("Can not place %c at %d , %d", token.symbol, xCoordinate, yCoordinate));

        gameBoard[xCoordinate][yCoordinate] = token;
        history.add(token);
    }
}
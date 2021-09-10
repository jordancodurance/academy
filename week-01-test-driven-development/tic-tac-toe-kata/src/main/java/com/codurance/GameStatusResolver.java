package com.codurance;

import java.util.List;

import static com.codurance.BoardToken.PLAYER_ONE_TOKEN;
import static com.codurance.GameStatus.DRAWN;
import static com.codurance.GameStatus.NOT_STARTED;
import static com.codurance.GameStatus.PLAYER_ONE_WON;
import static com.codurance.GameStatus.PLAYER_TWO_WON;
import static com.codurance.GameStatus.PLAYING;

public class GameStatusResolver {
    public static GameStatus determineStatus(List<BoardToken> turns, BoardToken[][] gameBoard) {
        if (turns.isEmpty()) return NOT_STARTED;

        BoardToken lastTurn = turns.get(turns.size() - 1);
        if (hasPlayerHasWon(lastTurn, gameBoard)) {
            return lastTurn == PLAYER_ONE_TOKEN ? PLAYER_ONE_WON : PLAYER_TWO_WON;
        } else if (turns.size() == 9) {
            return DRAWN;
        }

        return PLAYING;
    }

    private static boolean hasPlayerHasWon(BoardToken token, BoardToken[][] gameBoard) {
        return hasAnyWinningHorizontalRows(token, gameBoard) ||
                hasAnyWinningVerticalColumns(token, gameBoard) ||
                hasAnyWinningDiagonalLine(token, gameBoard);
    }

    private static boolean hasAnyWinningHorizontalRows(BoardToken token, BoardToken[][] gameBoard) {
        boolean hasWinningRow = false;
        for (int x = 0; x < gameBoard[0].length; x++) {
            hasWinningRow = (gameBoard[x][0] == token && gameBoard[x][1] == token && gameBoard[x][2] == token);
            if (hasWinningRow) break;
        }

        return hasWinningRow;
    }

    private static boolean hasAnyWinningVerticalColumns(BoardToken token, BoardToken[][] gameBoard) {
        boolean hasWinningColumn = false;
        for (int y = 0; y < gameBoard[1].length; y++) {
            hasWinningColumn = (gameBoard[0][y] == token && gameBoard[1][y] == token && gameBoard[2][y] == token);
            if (hasWinningColumn) break;
        }

        return hasWinningColumn;
    }

    private static boolean hasAnyWinningDiagonalLine(BoardToken token, BoardToken[][] gameBoard) {
        boolean hasAnyWinningDiagonalLeft = gameBoard[0][0] == token && gameBoard[1][1] == token && gameBoard[2][2] == token;
        boolean hasAnyWinningDiagonalRight = gameBoard[2][0] == token && gameBoard[1][1] == token && gameBoard[0][2] == token;

        return hasAnyWinningDiagonalLeft || hasAnyWinningDiagonalRight;
    }
}

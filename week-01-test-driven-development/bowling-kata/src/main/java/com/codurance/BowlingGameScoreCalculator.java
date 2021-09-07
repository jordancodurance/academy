package com.codurance;

import static com.codurance.BowlingThrow.STRIKE;
import static com.codurance.ThrowScoreCalculator.calculateThrowScore;

public class BowlingGameScoreCalculator {

    private static final String BONUS_GAME_SEPARATOR = "\\|\\|";
    private static final String FRAME_BOUNDARY = "\\|";
    private static final int MAX_SCORE = 300;

    public int calculateScore(String game) {
        String[] games = game.split(BONUS_GAME_SEPARATOR);
        String mainGame = games[0].replaceAll(FRAME_BOUNDARY, "");
        if (games.length == 1) return calculateMainGameScore(mainGame);

        String bonusGame = games[1];
        if (hasAllStrikes(mainGame, bonusGame)) return MAX_SCORE;

        return calculateMainGameScore(mainGame) + calculateBonusGameScore(bonusGame);
    }

    private boolean hasAllStrikes(String mainGame, String bonusGame) {
        String fullGame = mainGame + bonusGame;
        for (char recordedThrow : fullGame.toCharArray()) {
            if (recordedThrow != STRIKE.symbol) return false;
        }

        return true;
    }

    private int calculateMainGameScore(String game) {
        char[] recordedThrows = game.replaceAll(FRAME_BOUNDARY, "").toCharArray();
        return calculateGameScore(recordedThrows);
    }

    private int calculateGameScore(char[] recordedThrows) {
        int score = 0;

        for (int trackedThrow = 0; trackedThrow < recordedThrows.length; trackedThrow++)
            score += calculateThrowScore(recordedThrows, trackedThrow);

        return score;
    }

    private int calculateBonusGameScore(String game) {
        char[] bonusThrows = game.toCharArray();
        return calculateGameScore(bonusThrows);
    }

}

package com.codurance;

import static com.codurance.SpecialRoll.MISS;
import static com.codurance.SpecialRoll.SPARE;
import static com.codurance.SpecialRoll.STRIKE;
import static java.lang.Integer.parseInt;

public class BowlingGameScoreCalculator {

    private static final String FRAME_BOUNDARY = "\\|";
    private static final int NUMBER_OF_FRAMES = 10;
    private static final int PINS_PER_ROLL = 10;

    public int calculateScore(String game) {
        String unformattedGame = game.replaceAll(FRAME_BOUNDARY, "");
        int score = 0;
        int roll = 0;
        for (int frame = 0; frame < NUMBER_OF_FRAMES; frame++) {
            String pins = unformattedGame.substring(roll, roll + 1);
            String secondPins = unformattedGame.substring(roll + 1, roll + 2);
            if (secondPins.equals("/")) {
                score += calculateSpareScore(roll, unformattedGame);
                roll += 2;
            } else if (pins.equals("X")) {
                score += calculateStrikeScore(roll, unformattedGame);
                roll++;
            } else {
                score += calculateFrameScore(roll, unformattedGame);
                roll += 2;
            }
        }
        return score;
    }

    private int calculateSpareScore(int roll, String game) {
        return PINS_PER_ROLL + calculateRollScore(roll + 2, game);
    }

    private int calculateStrikeScore(int roll, String game) {
        return PINS_PER_ROLL + calculateRollScore(roll + 1, game) + calculateRollScore(roll + 2, game);
    }

    private int calculateFrameScore(int roll, String game) {
        return calculateRollScore(roll, game) + calculateRollScore(roll + 1, game);
    }

    private int calculateRollScore(int roll, String game) {
        String pins = game.substring(roll, roll + 1);
        if (pins.equals(STRIKE.symbol)) {
            return PINS_PER_ROLL;
        } else if (pins.equals(SPARE.symbol)) {
            return PINS_PER_ROLL - getPreviousRollScore(roll, game);
        } else if (pins.equals(MISS.symbol)) {
            return 0;
        }

        return parseInt(pins);
    }

    private int getPreviousRollScore(int roll, String game) {
        String previousPins = game.substring(roll - 1, roll);
        return parseInt(previousPins);
    }

}

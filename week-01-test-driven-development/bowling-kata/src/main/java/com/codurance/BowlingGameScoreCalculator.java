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
            if (isStrikeFrame(roll, unformattedGame)) {
                score += calculateStrikeFrameScore(roll, unformattedGame);
                roll++;
            } else if (isSpareFrame(roll, unformattedGame)) {
                score += calculateSpareFrameScore(roll, unformattedGame);
                roll += 2;
            } else {
                score += calculateNormalFrameScore(roll, unformattedGame);
                roll += 2;
            }
        }
        return score;
    }

    private boolean isStrikeFrame(int roll, String game) {
        return game.substring(roll, roll + 1).equals(STRIKE.symbol);
    }

    private int calculateStrikeFrameScore(int roll, String game) {
        return PINS_PER_ROLL + calculateRollScore(roll + 1, game) + calculateRollScore(roll + 2, game);
    }

    private boolean isSpareFrame(int roll, String game) {
        return game.substring(roll + 1, roll + 2).equals(SPARE.symbol);
    }

    private int calculateSpareFrameScore(int roll, String game) {
        return PINS_PER_ROLL + calculateRollScore(roll + 2, game);
    }

    private int calculateNormalFrameScore(int roll, String game) {
        return calculateRollScore(roll, game) + calculateRollScore(roll + 1, game);
    }

    private int calculateRollScore(int roll, String game) {
        String pins = game.substring(roll, roll + 1);
        if (pins.equals(STRIKE.symbol)) {
            return PINS_PER_ROLL;
        } else if (pins.equals(SPARE.symbol)) {
            String previousPins = game.substring(roll - 1, roll);
            return PINS_PER_ROLL - parseInt(previousPins);
        } else if (pins.equals(MISS.symbol)) {
            return 0;
        }

        return parseInt(pins);
    }

}

package com.codurance;

import static com.codurance.BowlingThrow.MISS;
import static com.codurance.BowlingThrow.SPARE;
import static com.codurance.BowlingThrow.STRIKE;
import static java.lang.Character.getNumericValue;

public class ThrowScoreCalculator {

    private static final int MAXIMUM_PINS = 10;
    private static final int LOWEST_PIN_SCORE = 0;

    public static int calculateThrowScore(char[] recordedThrows, int trackedCurrentThrow) {
        char recordedCurrentThrow = recordedThrows[trackedCurrentThrow];
        if (recordedCurrentThrow == STRIKE.symbol) return calculateStrikeScore(recordedThrows, trackedCurrentThrow);
        else if (recordedCurrentThrow == SPARE.symbol) return calculateSpareScore(recordedThrows, trackedCurrentThrow);
        else if (recordedCurrentThrow == MISS.symbol) return LOWEST_PIN_SCORE;

        return getNumericValue(recordedCurrentThrow);
    }

    private static int calculateStrikeScore(char[] recordedThrows, int trackedCurrentThrow) {
        if (isLastFrame(recordedThrows, trackedCurrentThrow)) return MAXIMUM_PINS;

        int nextThrowScore = calculateThrowScore(recordedThrows, trackedCurrentThrow + 1);

        return MAXIMUM_PINS + nextThrowScore;
    }

    private static boolean isLastFrame(char[] recordedThrows, int trackedCurrentThrow) {
        return recordedThrows.length == trackedCurrentThrow + 1;
    }

    public static int calculateSpareScore(char[] recordedThrows, int trackedCurrentThrow) {
        int previousThrowScore = calculateThrowScore(recordedThrows, trackedCurrentThrow - 1);
        int currentSpareThrowScore = MAXIMUM_PINS - previousThrowScore;
        if (isLastFrame(recordedThrows, trackedCurrentThrow)) return currentSpareThrowScore;

        int nextThrowScore = calculateThrowScore(recordedThrows, trackedCurrentThrow + 1);

        return currentSpareThrowScore + nextThrowScore;
    }

}

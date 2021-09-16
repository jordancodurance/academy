package com.codurance.command.executable;

import com.codurance.interaction.Direction;
import com.codurance.interaction.Position;

import static com.codurance.interaction.Direction.values;

public class RotateRightCommand extends VectorCommand {

    public RotateRightCommand(Position currentPosition) {
        super(currentPosition);
    }

    @Override
    public Position execute() {
        Direction direction = currentPosition.direction;
        Direction[] directions = values();

        int currentIndex = direction.ordinal();
        int nextIndex = incrementWithinRange(currentIndex, directions.length);
        Direction nextDirection = directions[nextIndex];
        return new Position(nextDirection, currentPosition.coordinate);
    }

}


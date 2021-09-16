package com.codurance.command.executable;

import com.codurance.interaction.Direction;
import com.codurance.interaction.Position;

import static com.codurance.interaction.Direction.values;

public class RotateLeftCommand extends VectorCommand {

    public RotateLeftCommand(Position currentPosition) {
        super(currentPosition);
    }

    @Override
    public Position execute() {
        Direction direction = currentPosition.direction;
        Direction[] directions = values();

        int currentIndex = direction.ordinal();
        int previousIndex = decrementWithinRange(currentIndex, directions.length);
        Direction nextDirection = directions[previousIndex];
        return new Position(nextDirection, currentPosition.coordinate);
    }

}

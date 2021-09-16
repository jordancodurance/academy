package com.codurance.command.executable;

import com.codurance.landscape.Position;
import com.codurance.landscape.direction.Direction;

public class RotateLeftCommand extends VectorCommand {

    public RotateLeftCommand(Position currentPosition) {
        super(currentPosition);
    }

    @Override
    public Position execute() {
        Direction newDirection = currentPosition.direction.rotateLeft();
        return new Position(currentPosition.coordinate, newDirection);
    }

}

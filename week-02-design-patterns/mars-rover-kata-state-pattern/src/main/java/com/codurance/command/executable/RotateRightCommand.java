package com.codurance.command.executable;

import com.codurance.landscape.Position;
import com.codurance.landscape.direction.Direction;

public class RotateRightCommand extends VectorCommand {

    public RotateRightCommand(Position currentPosition) {
        super(currentPosition);
    }

    @Override
    public Position execute() {
        Direction newDirection = currentPosition.direction.rotateRight();
        return new Position(currentPosition.coordinate, newDirection);
    }

}


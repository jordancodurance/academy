package com.codurance.command.executable;

import com.codurance.landscape.Position;

public abstract class VectorCommand {

    protected final Position currentPosition;

    public VectorCommand(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public abstract Position execute();

    protected int decrementWithinRange(int value, int range) {
        return (value - 1 + range) % range;
    }

    protected int incrementWithinRange(int value, int range) {
        return (value + 1) % range;
    }

}

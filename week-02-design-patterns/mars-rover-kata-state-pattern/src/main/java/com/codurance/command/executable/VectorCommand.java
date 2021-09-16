package com.codurance.command.executable;

import com.codurance.landscape.Position;

public abstract class VectorCommand {

    protected final Position currentPosition;

    public VectorCommand(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public abstract Position execute();

}

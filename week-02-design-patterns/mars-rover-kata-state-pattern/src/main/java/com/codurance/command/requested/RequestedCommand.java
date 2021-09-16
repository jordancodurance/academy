package com.codurance.command.requested;

public enum RequestedCommand {

    LEFT("L"),
    RIGHT("R"),
    MOVE("M");

    public final String input;

    RequestedCommand(String input) {
        this.input = input;
    }

}

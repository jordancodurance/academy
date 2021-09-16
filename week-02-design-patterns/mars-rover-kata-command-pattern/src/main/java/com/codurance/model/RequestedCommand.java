package com.codurance.model;

public enum RequestedCommand {

    LEFT("L"),
    RIGHT("R"),
    MOVE("M");

    public final String input;

    RequestedCommand(String input) {
        this.input = input;
    }

}

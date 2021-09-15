package com.codurance.model;

public enum Command {

    LEFT("L"),
    RIGHT("R"),
    MOVE("M");

    public final String input;

    Command(String input) {
        this.input = input;
    }

}

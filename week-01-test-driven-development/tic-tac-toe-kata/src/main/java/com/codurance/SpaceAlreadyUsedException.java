package com.codurance;

public class SpaceAlreadyUsedException extends RuntimeException {
    public SpaceAlreadyUsedException(String message) {
        super(message);
    }
}

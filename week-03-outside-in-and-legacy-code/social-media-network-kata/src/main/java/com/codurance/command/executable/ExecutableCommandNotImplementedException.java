package com.codurance.command.executable;

public class ExecutableCommandNotImplementedException extends RuntimeException {

    public ExecutableCommandNotImplementedException(String message) {
        super(message);
    }

}

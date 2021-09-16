package com.codurance.command.requested;

public class UnknownRequestedCommandException extends RuntimeException {

    public UnknownRequestedCommandException(String detailMessage) {
        super(detailMessage);
    }

}

package com.codurance.error;

public class UnknownCommandException extends RuntimeException {

    public UnknownCommandException(String detailMessage) {
        super(detailMessage);
    }

}

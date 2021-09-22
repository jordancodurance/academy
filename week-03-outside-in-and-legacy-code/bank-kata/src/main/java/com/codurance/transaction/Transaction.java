package com.codurance.transaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

public abstract class Transaction {

    LocalDateTime timestamp;
    int amount;

    public Transaction(LocalDateTime timestamp, int amount) {
        this.timestamp = timestamp;
        this.amount = amount;
    }

    public String getFormattedDate() {
        DateTimeFormatter formatter = ofPattern("dd/MM/yyyy");

        return timestamp.format(formatter);
    }

    public abstract String getFormattedAmount();

    public abstract int getTransactionalAmount();

}

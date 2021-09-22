package com.codurance.transaction;

import java.time.LocalDateTime;

import static java.lang.String.valueOf;

public class DepositTransaction extends Transaction {

    public DepositTransaction(LocalDateTime timestamp, int amount) {
        super(timestamp, amount);
    }

    @Override
    public String getFormattedAmount() {
        return valueOf(amount);
    }

    @Override
    public int getTransactionalAmount() {
        return amount;
    }

}

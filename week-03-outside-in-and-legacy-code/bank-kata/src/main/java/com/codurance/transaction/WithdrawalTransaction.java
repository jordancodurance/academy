package com.codurance.transaction;

import java.time.LocalDateTime;

import static java.lang.String.format;

public class WithdrawalTransaction extends Transaction {

    public WithdrawalTransaction(LocalDateTime timestamp, int amount) {
        super(timestamp, amount);
    }

    @Override
    public String getFormattedAmount() {
        return format("-%d", amount);
    }

    @Override
    public int getTransactionalAmount() {
        return -amount;
    }

}

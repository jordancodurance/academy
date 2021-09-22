package com.codurance.transaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class AccountTransactionHandlerShould {

    @Mock
    TimestampProvider timestampProvider;

    AccountTransactionHandler target;

    @BeforeEach
    void setUp() {
        target = new AccountTransactionHandler(timestampProvider);
    }

    @Test
    void have_no_recorded_transactions_by_default() {
        assertEquals(emptyList(), target.getRecordedTransactions());
    }

    @Test
    void record_deposit() {
        LocalDateTime providedTimestamp = now();
        given(timestampProvider.now()).willReturn(providedTimestamp);

        target.recordDeposit(500);

        Transaction transaction = target.getRecordedTransactions().get(0);
        assertTrue(transaction instanceof DepositTransaction);
        assertEquals(providedTimestamp, transaction.timestamp);
        assertEquals(500, transaction.amount);
    }

    @Test
    void record_withdrawal() {
        LocalDateTime providedTimestamp = now();
        given(timestampProvider.now()).willReturn(providedTimestamp);
        withAccountBalance(500);

        target.recordWithdrawal(500);

        Transaction transaction = target.getRecordedTransactions().get(1);
        assertTrue(transaction instanceof WithdrawalTransaction);
        assertEquals(providedTimestamp, transaction.timestamp);
        assertEquals(500, transaction.amount);
    }

    @Test
    void prevent_withdrawal_for_insufficient_funds() {
        withAccountBalance(250);

        InsufficientFundsException exception = assertThrows(InsufficientFundsException.class, ()-> target.recordWithdrawal(500));

        assertEquals("Insufficient funds to withdraw from 250", exception.getMessage());
    }

    private void withAccountBalance(int amount) {
        target.recordDeposit(amount);
    }
}

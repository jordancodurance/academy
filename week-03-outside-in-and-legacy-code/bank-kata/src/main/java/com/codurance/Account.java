package com.codurance;

import com.codurance.printing.AccountStatementPrinter;
import com.codurance.transaction.AccountTransactionHandler;
import com.codurance.transaction.Transaction;

import java.util.List;

public class Account implements AccountService {

    private final AccountTransactionHandler transactionHandler;
    private final AccountStatementPrinter statementPrinter;

    public Account(AccountTransactionHandler transactionHandler, AccountStatementPrinter statementPrinter) {
        this.transactionHandler = transactionHandler;
        this.statementPrinter = statementPrinter;
    }

    @Override
    public void deposit(int amount) {
        transactionHandler.recordDeposit(amount);
    }

    @Override
    public void withdraw(int amount) {
        transactionHandler.recordWithdrawal(amount);
    }

    @Override
    public void printStatement() {
        List<Transaction> recordedTransactions = transactionHandler.getRecordedTransactions();
        statementPrinter.printFormattedStatement(recordedTransactions);
    }

}

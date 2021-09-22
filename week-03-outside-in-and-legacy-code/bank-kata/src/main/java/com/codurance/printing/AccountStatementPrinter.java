package com.codurance.printing;

import com.codurance.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static java.util.Collections.reverse;

public class AccountStatementPrinter {

    private final Printer printer;

    public AccountStatementPrinter(Printer printer) {
        this.printer = printer;
    }

    public void printFormattedStatement(List<Transaction> transactions) {
        List<String> transactionLines = buildPrintableTransactionLines(transactions);
        reverse(transactionLines);

        List<String> statementLines = new ArrayList<>();
        statementLines.add(buildHeader());
        statementLines.addAll(transactionLines);

        printStatement(statementLines);
    }

    private List<String> buildPrintableTransactionLines(List<Transaction> transactions) {
        List<String> printableLines = new ArrayList<>();
        int runningBalance = 0;
        for (Transaction transaction : transactions) {
            String formattedDate = transaction.getFormattedDate();
            String formattedAmount = transaction.getFormattedAmount();
            runningBalance += transaction.getTransactionalAmount();
            String line = format("%s || %s   || %d", formattedDate, formattedAmount, runningBalance);
            printableLines.add(line);
        }

        return printableLines;
    }

    private String buildHeader() {
        return "Date       || Amount || Balance";
    }

    private void printStatement(List<String> statementLines) {
        for (String line : statementLines) {
            printer.printLine(line);
        }
    }

}
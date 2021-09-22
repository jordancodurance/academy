package com.codurance.printing;

import com.codurance.transaction.DepositTransaction;
import com.codurance.transaction.Transaction;
import com.codurance.transaction.WithdrawalTransaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.time.LocalDateTime.of;
import static org.mockito.Mockito.inOrder;

@ExtendWith(MockitoExtension.class)
public class AccountStatementPrinterShould {

    @Mock Printer printer;

    @Test void
    print_formatted_statement() {
        AccountStatementPrinter target = new AccountStatementPrinter(printer);
        List<Transaction> transactions = List.of(
                new DepositTransaction(of(2012, 1, 10, 11, 53), 1000),
                new DepositTransaction(of(2012, 1, 13, 12, 0), 1000),
                new WithdrawalTransaction(of(2012, 1, 14, 9, 30), 500)
        );

        target.printFormattedStatement(transactions);

        InOrder inOrder = inOrder(printer);
        inOrder.verify(printer).printLine("Date       || Amount || Balance");
        inOrder.verify(printer).printLine("14/01/2012 || -500   || 1500");
        inOrder.verify(printer).printLine("13/01/2012 || 1000   || 2000");
        inOrder.verify(printer).printLine("10/01/2012 || 1000   || 1000");
    }

}

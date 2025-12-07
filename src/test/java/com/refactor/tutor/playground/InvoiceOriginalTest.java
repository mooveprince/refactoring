package com.refactor.tutor.playground;

import com.refactor.tutor.model.Play;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceOriginalTest {

    @Test
    void createStatement(){
        Performance[] performances = {
                new Performance(Play.HAMLET, 55),
                new Performance(Play.AS_LIKE, 35),
                new Performance(Play.OTHELLO, 40)
        };
        InvoiceOriginal invoice = new InvoiceOriginal("BigCo", performances);

        String expectedStatement =
                "Statement for BigCo\n" +
                        "  Hamlet: $650.00 (55 seats)\n" +
                        "  As You Like It: $580.00 (35 seats)\n" +
                        "  Othello: $500.00 (40 seats)\n" +
                        "Amount owed is $1,730.00\n" +
                        "You earned 47 credits\n";

        String actualStatement = invoice.createStatement();

        assertEquals(expectedStatement, actualStatement);
    }

}
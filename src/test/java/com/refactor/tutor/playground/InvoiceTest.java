package com.refactor.tutor.playground;

import com.refactor.tutor.model.PlayType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceTest {

    @Test
    void createStatement(){
        Performance[] performances = {
                new Performance(new Play("Hamlet", PlayType.TRAGEDY), 55),
                new Performance(new Play("As You Like It", PlayType.COMEDY), 35),
                new Performance(new Play("Othello", PlayType.TRAGEDY), 40)
        };
        Invoice invoice = new Invoice("BigCo", performances);

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
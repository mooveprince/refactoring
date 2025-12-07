package com.refactor.tutor.playground;

import com.refactor.tutor.model.Play;

import java.math.BigDecimal;

public class Invoice {

    String customer;
    Performance[] performances;

    public Invoice(String customer, Performance[] performances) {
        this.customer = customer;
        this.performances = performances;
    }

    public String customer() {
        return customer;
    }

    public Performance[] performances() {
        return performances;
    }

    public String createStatement() {

        BigDecimal totalAmount = BigDecimal.ZERO;
        int volumeCredits = 0;

        String result = "Statement for " + customer + "\n";
        String format = "$%,.2f";

        for (Performance perf : performances) {

            Play play = perf.getPlay();

            BigDecimal thisAmount = perf.amount();

            volumeCredits += perf.volumeCredits();

            result += "  " + play.getName() + ": " + String.format(format, thisAmount.divide(BigDecimal.valueOf(100))) + " (" + perf.getAudience() + " seats)\n";
            totalAmount = totalAmount.add(thisAmount);
        }

        result += "Amount owed is " + String.format(format, totalAmount.divide(BigDecimal.valueOf(100))) + "\n";
        result += "You earned " + volumeCredits + " credits\n";

        return result;
    }

}

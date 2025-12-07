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

        String result = "Statement for " + customer + "\n";
        String format = "$%,.2f";

        for (Performance perf : performances) {
            Play play = perf.getPlay();
            BigDecimal thisAmount = perf.amount();
            result += "  " + play.getName() + ": " + String.format(format, thisAmount.divide(BigDecimal.valueOf(100))) + " (" + perf.getAudience() + " seats)\n";
        }

        result += "Amount owed is " + String.format(format, totalAmount().divide(BigDecimal.valueOf(100))) + "\n";
        result += "You earned " + totalVolumeCredits() + " credits\n";

        return result;
    }


    private BigDecimal totalAmount() {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (Performance perf : performances) {
            totalAmount = totalAmount.add(perf.amount());
        }
        return totalAmount;
    }

    private int totalVolumeCredits() {
        int totalVolumeCredits = 0;
        for (Performance perf : performances) {
            totalVolumeCredits += perf.volumeCredits();
        }
        return totalVolumeCredits;
    }

}

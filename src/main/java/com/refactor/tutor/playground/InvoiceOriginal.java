package com.refactor.tutor.playground;

import com.refactor.tutor.model.Play;

import java.math.BigDecimal;

public class InvoiceOriginal {

    String customer;
    Performance[] performances;

    public InvoiceOriginal(String customer, Performance[] performances) {
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
            int audience = perf.getAudience();

            BigDecimal thisAmount ;

            //calculate amount
            switch (play.getType().toLowerCase()) {
                case "tragedy" -> {
                    thisAmount = BigDecimal.valueOf(40000);
                    if (audience > 30) {
                        thisAmount = thisAmount.add(BigDecimal.valueOf(1000L * (audience - 30)));
                    }
                }
                case "comedy" -> {
                    thisAmount = BigDecimal.valueOf(30000);
                    if (audience > 20) {
                        thisAmount = thisAmount.add(BigDecimal.valueOf(10000L + 500L * (audience - 20)));
                    }
                    thisAmount = thisAmount.add(BigDecimal.valueOf(300L * audience));
                }
                default -> throw new IllegalArgumentException("unknown type: " + play.getType());
            }


            // calculate volume credits
            volumeCredits += Math.max(audience - 30, 0);
            if ("comedy".equalsIgnoreCase(play.getType())) {
                volumeCredits += Math.floor(audience / 5);
            }


            result += "  " + play.getName() + ": " + String.format(format, thisAmount.divide(BigDecimal.valueOf(100))) + " (" + perf.getAudience() + " seats)\n";
            totalAmount = totalAmount.add(thisAmount);
        }

        result += "Amount owed is " + String.format(format, totalAmount.divide(BigDecimal.valueOf(100))) + "\n";
        result += "You earned " + volumeCredits + " credits\n";

        return result;
    }
}

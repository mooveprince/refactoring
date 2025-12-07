package com.refactor.tutor.playground;

import com.refactor.tutor.model.Invoice;
import com.refactor.tutor.model.Performance;
import com.refactor.tutor.model.Play;

import java.math.BigDecimal;

public class Statement {


    String createStatementData(Invoice invoice) {

        BigDecimal totalAmount = BigDecimal.ZERO;
        int volumeCredits = 0;

        String result = "Statement for " + invoice.customer() + "\n";
        String format = "$%,.2f";

        for (Performance perf : invoice.performances()) {

            Play play = perf.playId();

            BigDecimal thisAmount = amountFor(perf);

            volumeCredits += volumeCredits(perf);

            result += "  " + play.getName() + ": " + String.format(format, thisAmount.divide(BigDecimal.valueOf(100))) + " (" + perf.audience() + " seats)\n";
            totalAmount = totalAmount.add(thisAmount);
        }

        result += "Amount owed is " + String.format(format, totalAmount.divide(BigDecimal.valueOf(100))) + "\n";
        result += "You earned " + volumeCredits + " credits\n";

        return result;

    }


    BigDecimal amountFor (Performance performance) {

        BigDecimal result;

        Play play = performance.playId();

        switch (play.getType().toLowerCase()) {
            case "tragedy" -> {
                result = BigDecimal.valueOf(40000);
                if (performance.audience() > 30) {
                    result = result.add(BigDecimal.valueOf(1000L * (performance.audience() - 30)));
                }
            }
            case "comedy" -> {
                result = BigDecimal.valueOf(30000);
                if (performance.audience() > 20) {
                    result = result.add(BigDecimal.valueOf(10000L + 500L * (performance.audience() - 20)));
                }
                result = result.add(BigDecimal.valueOf(300L * performance.audience()));
            }
            default -> throw new IllegalArgumentException("unknown type: " + play.getType());
        }

        return result;

    }

    int volumeCredits (Performance performance) {

        int result = 0;

        Play play = performance.playId();

        // calculate volume credits
        result += Math.max(performance.audience() - 30, 0);
        if ("comedy".equalsIgnoreCase(play.getType())) {
            result += Math.floor(performance.audience() / 5);
        }

        return result;

    }

}

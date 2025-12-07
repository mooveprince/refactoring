package com.refactor.tutor.original;

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

            // calculate volume credits
            volumeCredits += Math.max(perf.audience() - 30, 0);
            if ("comedy".equalsIgnoreCase(play.getType())) {
                volumeCredits += Math.floor(perf.audience() / 5);
            }

            // print line for this order
            BigDecimal thisAmount;
            switch (play.getType().toLowerCase()) {
                case "tragedy" -> {
                    thisAmount = BigDecimal.valueOf(40000);
                    if (perf.audience() > 30) {
                        thisAmount = thisAmount.add(BigDecimal.valueOf(1000L * (perf.audience() - 30)));
                    }
                }
                case "comedy" -> {
                    thisAmount = BigDecimal.valueOf(30000);
                    if (perf.audience() > 20) {
                        thisAmount = thisAmount.add(BigDecimal.valueOf(10000L + 500L * (perf.audience() - 20)));
                    }
                    thisAmount = thisAmount.add(BigDecimal.valueOf(300L * perf.audience()));
                }
                default -> throw new IllegalArgumentException("unknown type: " + play.getType());
            }
            result += "  " + play.getName() + ": " + String.format(format, thisAmount.divide(BigDecimal.valueOf(100))) + " (" + perf.audience() + " seats)\n";
            totalAmount = totalAmount.add(thisAmount);
        }

        result += "Amount owed is " + String.format(format, totalAmount.divide(BigDecimal.valueOf(100))) + "\n";
        result += "You earned " + volumeCredits + " credits\n";

        return result;

    }

}

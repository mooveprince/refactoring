package com.refactor.tutor.playground;

import java.math.BigDecimal;

public class Performance {

    Play play;
    int audience;

    public Performance(Play play, int audience) {
        this.play = play;
        this.audience = audience;
    }

    public Play getPlay() {
        return play;
    }
    public int getAudience() {
        return audience;
    }

    public BigDecimal amount() {

        BigDecimal result;

        switch (play.getType().toString().toLowerCase()) {
            case "tragedy" -> {
                result = BigDecimal.valueOf(40000);
                if (audience > 30) {
                    result = result.add(BigDecimal.valueOf(1000L * (audience - 30)));
                }
            }
            case "comedy" -> {
                result = BigDecimal.valueOf(30000);
                if (audience > 20) {
                    result = result.add(BigDecimal.valueOf(10000L + 500L * (audience - 20)));
                }
                result = result.add(BigDecimal.valueOf(300L * audience));
            }
            default -> throw new IllegalArgumentException("unknown type: " + play.getType());
        }

        return result;

    }

    public int volumeCredits () {

        int result = 0;

        // calculate volume credits
        result += Math.max(audience - 30, 0);
        if ("comedy".equalsIgnoreCase(play.getType().toString())) {
            result += Math.floor(audience / 5);
        }

        return result;

    }

}

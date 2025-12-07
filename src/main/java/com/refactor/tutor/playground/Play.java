package com.refactor.tutor.playground;

import com.refactor.tutor.model.PlayType;

import java.math.BigDecimal;

public class Play {

    String name;
    PlayType playType;

    public Play(String name, PlayType playType) {
        this.name = name;
        this.playType = playType;
    }

    public String getName() {
        return name;
    }
    public PlayType getType() {
        return playType;
    }

    public BigDecimal amount(int audience) {

        BigDecimal result;

        switch (playType.toString().toLowerCase()) {
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
            default -> throw new IllegalArgumentException("unknown type: " + playType);
        }

        return result;

    }

    public int volumeCredits(int audience) {
        int result = 0;

        // calculate volume credits
        result += Math.max(audience - 30, 0);
        if ("comedy".equalsIgnoreCase(playType.toString())) {
            result += Math.floor(audience / 5);
        }

        return result;
    }

}

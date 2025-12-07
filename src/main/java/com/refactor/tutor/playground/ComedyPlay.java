package com.refactor.tutor.playground;

import com.refactor.tutor.model.PlayType;

import java.math.BigDecimal;

public class ComedyPlay extends Play{

    public ComedyPlay(String name, PlayType playType) {
        super(name, playType);
    }

    @Override
    public BigDecimal amount(int audience) {
        BigDecimal result = BigDecimal.valueOf(30000);
        if (audience > 20) {
            result = result.add(BigDecimal.valueOf(10000L + 500L * (audience - 20)));
        }
        result = result.add(BigDecimal.valueOf(300L * audience));
        return result;
    }

    @Override
    public int volumeCredits(int audience) {
        int result = super.volumeCredits(audience);
        result += audience / 5; // integer division is fine for "floor"
        return result;
    }
}

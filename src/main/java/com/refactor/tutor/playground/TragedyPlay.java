package com.refactor.tutor.playground;

import com.refactor.tutor.model.PlayType;

public class TragedyPlay extends Play {

    public TragedyPlay(String name, PlayType playType) {
        super(name, playType);
    }

    @Override
    public java.math.BigDecimal amount(int audience) {
        java.math.BigDecimal result = java.math.BigDecimal.valueOf(40000);
        if (audience > 30) {
            result = result.add(java.math.BigDecimal.valueOf(1000L * (audience - 30)));
        }
        return result;
    }
}

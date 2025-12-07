package com.refactor.tutor.playground;

import com.refactor.tutor.model.PlayType;

import java.math.BigDecimal;

public abstract class Play {

    private final String name;
    private final PlayType playType;

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

    public abstract BigDecimal amount(int audience);

    public int volumeCredits(int audience) {
        // default volume credits (common part)
        return Math.max(audience - 30, 0);
    }


}

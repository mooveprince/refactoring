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
        return play.amount(audience);
    }

    public int volumeCredits () {
        return play.volumeCredits(audience);
    }

}

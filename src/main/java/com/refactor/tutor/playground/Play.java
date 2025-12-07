package com.refactor.tutor.playground;

import com.refactor.tutor.model.PlayType;

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
}

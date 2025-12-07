package com.refactor.tutor.model;

public enum Play {

    HAMLET("Hamlet", "TRAGEDY"),
    AS_LIKE("As You Like It", "COMEDY"),
    OTHELLO("Othello", "TRAGEDY");

    private final String type;
    private final String name;

    Play(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}

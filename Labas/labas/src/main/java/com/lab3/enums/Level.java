package com.lab3.enums;

// рівень для усіх спеціалістів
public enum Level {
    JUNIOR("Junior"),
    MIDDLE("Middle"),
    SENIOR("Senior"),
    LEAD("Lead");

    private final String displayName;

    Level(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

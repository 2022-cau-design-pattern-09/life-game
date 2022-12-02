package com.holub.constant;

public enum Modifier {
    AGONIZING(500),
    SLOW(150),
    MEDIUM(70),
    FAST(30);

    final private int interval;

    Modifier(int interval) {
        this.interval = interval;
    }

    public int getInterval() {
        return interval;
    }
}

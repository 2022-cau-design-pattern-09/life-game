package com.holub.life.enumeration;

public enum Modifier {
    HART(0),
    TICK(-1),
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

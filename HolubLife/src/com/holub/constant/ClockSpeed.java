package com.holub.constant;

public enum ClockSpeed {
    AGONIZING(500),
    SLOW(150),
    MEDIUM(70),
    FAST(30);

    final private int interval;

    ClockSpeed(int interval) {
        this.interval = interval;
    }

    public int getInterval() {
        return interval;
    }
}

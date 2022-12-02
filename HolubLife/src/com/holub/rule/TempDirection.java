package com.holub.rule;


public class TempDirection {
    private int dy;
    private int dx;
    private String description;

    public TempDirection(int dy, int dx, String description) {
        this.dy = dy;
        this.dx = dx;
        this.description = description;
    }

    public int getDy() {
        return dy;
    }

    public int getDx() {
        return dx;
    }

    public String getDescription() {
        return description;
    }
}

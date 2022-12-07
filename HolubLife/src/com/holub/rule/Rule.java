package com.holub.rule;

import com.holub.life.Cell;

import java.util.List;

public abstract class Rule {
    public abstract boolean willBeAlive(List<Cell> neighbors, boolean amAlive);
    public abstract List<RelativePosition> getRelativePositions();
}
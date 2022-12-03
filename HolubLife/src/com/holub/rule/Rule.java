package com.holub.rule;

import java.util.List;
import com.holub.life.Cell;

public abstract class Rule {
    public abstract boolean willBeAlive(List<Cell> neighbors, boolean amAlive);
    public abstract List<RelativePosition> getRelativePositions();
}
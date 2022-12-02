package com.holub.rule;

import java.util.List;

public abstract class Rule {

    public abstract boolean isNumberToBorn(int number);

    public abstract boolean isNumberToSustain(int number);

    public abstract List<RelativePosition> getRelativePositions();
}

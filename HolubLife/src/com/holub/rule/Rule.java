package com.holub.rule;

import java.util.List;

public abstract class Rule {

    abstract boolean isNumberToBorn(int number);

    abstract boolean isNumberToSustain(int number);

    abstract List<TempDirection> getNeighborhoods();
}

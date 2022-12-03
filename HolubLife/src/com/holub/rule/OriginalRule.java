package com.holub.rule;

import java.util.Arrays;
import java.util.List;

public class OriginalRule extends Rule {

    private final List<Integer> numbersToSustain = Arrays.asList(2, 3);
    private final List<Integer> numbersToBorn = Arrays.asList(3);

    private final List<RelativePosition> neighborhoods = Arrays.asList(
            new RelativePosition(-1, -1),
            new RelativePosition(-1, 0),
            new RelativePosition(-1, 1),
            new RelativePosition(0, -1),
            new RelativePosition(0, 1),
            new RelativePosition(1, -1),
            new RelativePosition(1, 0),
            new RelativePosition(1, 1)
    );

    @Override
    public boolean isNumberToBorn(int number) {
        return numbersToBorn.contains(number);
    }

    @Override
    public boolean isNumberToSustain(int number) {
        return numbersToSustain.contains(number);
    }

    @Override
    public List<RelativePosition> getRelativePositions() {
        return neighborhoods;
    }
}

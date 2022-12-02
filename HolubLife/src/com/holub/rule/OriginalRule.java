package com.holub.rule;

import java.util.Arrays;
import java.util.List;

public class OriginalRule extends Rule {

    private final List<Integer> numbersToBorn = Arrays.asList(3);
    private final List<Integer> numbersToSustain = Arrays.asList(2, 3);

    private final List<TempDirection> neighborhoods = Arrays.asList(
            new TempDirection(-1, -1, "UpLeft"),
            new TempDirection(-1, 0, "Up"),
            new TempDirection(-1, 1, "UpRight"),
            new TempDirection(0, -1, "Left"),
            new TempDirection(0, 1, "Right"),
            new TempDirection(1, -1, "DownLeft"),
            new TempDirection(1, 0, "Down"),
            new TempDirection(1, 1, "DownRight")
    );

    @Override
    boolean isNumberToBorn(int number) {
        return numbersToBorn.contains(number);
    }

    @Override
    boolean isNumberToSustain(int number) {
        return numbersToSustain.contains(number);
    }

    @Override
    public List<TempDirection> getNeighborhoods() {
        return neighborhoods;
    }
}

package com.holub.rule;

import com.holub.life.Cell;

import java.util.Arrays;
import java.util.List;

public class DiamoebaRule extends Rule {
    private final String name = "Diamoeba";
    private final List<Integer> numbersToSustain = Arrays.asList(5, 6, 7, 8);
    private final List<Integer> numbersToBorn = Arrays.asList(3, 5, 6, 7, 8);

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
    public String getName() {
        return name;
    }

    @Override
    public boolean willBeAlive(List<Cell> neighborResidents, boolean amAlive) {
        int neighbors = 0;
        for(Cell c: neighborResidents){
            if (c.isAlive())
                neighbors++;
        }

        return (amAlive && numbersToSustain.contains(neighbors)) ||
                (!amAlive && numbersToBorn.contains(neighbors));
    }

    @Override
    public List<RelativePosition> getRelativePositions() {
        return neighborhoods;
    }
}

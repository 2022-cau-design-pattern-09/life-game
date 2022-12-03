package com.holub.rule;

import com.holub.life.Cell;
import java.util.List;
import java.util.ArrayList;

public class DummyRule extends Rule {
    @Override
    public boolean willBeAlive(List<Cell> neighbors, boolean amAlive){
        return false;
    }

    @Override
    public List<RelativePosition> getRelativePositions(){
        return new ArrayList<RelativePosition>();
    }
}

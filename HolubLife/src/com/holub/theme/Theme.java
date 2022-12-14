package com.holub.theme;

import com.holub.constant.Colors;
import java.awt.*;

public abstract class Theme {
    protected Color cellBorderColor = Colors.BLACK.getColor();
    protected Color cellLiveColor = Colors.WHITE.getColor();
    protected Color cellDeadColor = Colors.BLACK.getColor();
    protected Color gridBorderColor = Colors.BLACK.getColor();
    protected Color gridActiveColor = Colors.BLACK.getColor();
    protected String name = "Default";

    public Color cellBorderColor(){
        return cellBorderColor;
    }
    public Color cellLiveColor(){
        return cellLiveColor;
    }
    public Color cellDeadColor(){
        return cellDeadColor;
    }
    public Color gridBorderColor(){
        return gridBorderColor;
    }
    public Color gridActiveColor(){
        return gridActiveColor;
    }

    public String getName(){
        return name;
    }
}

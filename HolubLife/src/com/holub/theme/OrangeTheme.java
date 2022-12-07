package com.holub.theme;

import com.holub.constant.Colors;

public class OrangeTheme extends Theme{
    public OrangeTheme(){
        cellBorderColor = Colors.DARK_YELLOW.getColor();
        cellLiveColor = Colors.RED.getColor();
        cellDeadColor = Colors.LIGHT_YELLOW.getColor();
        gridBorderColor = Colors.LIGHT_ORANGE.getColor();
        gridActiveColor = Colors.BLUE.getColor();
    }
}

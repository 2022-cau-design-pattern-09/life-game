package com.holub.theme;

import java.awt.*;

public class GrayTheme extends Theme{
    public GrayTheme(){
        cellBorderColor = new Color(0xA0, 0xA0, 0xA0);
        cellLiveColor = new Color(0x40, 0x40, 0x40);
        cellDeadColor = new Color(0xE0, 0xE0, 0xE0);
        gridBorderColor = new Color(0x00, 0x00, 0x00);
        gridActiveColor = new Color(0x20, 0x20, 0x20);

        name = "Gray";
    }
}


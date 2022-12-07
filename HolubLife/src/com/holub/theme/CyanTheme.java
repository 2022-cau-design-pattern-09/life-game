package com.holub.theme;

import java.awt.*;

public class CyanTheme extends Theme{
    public CyanTheme(){
        cellBorderColor = new Color(100, 100, 100);
        cellLiveColor = new Color(255, 214, 0);
        cellDeadColor = new Color(3, 131, 103);
        gridBorderColor = new Color(0, 0, 0);
        gridActiveColor = new Color(214, 234, 183);

        name = "Cyan";
    }
}


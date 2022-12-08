package com.holub.life;

import com.holub.ui.UIManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UIManagerTest {

    private static Universe universe;
    private static UIManager uiManager;

    @BeforeAll
    public static void init() {
        universe = new Universe();
        uiManager = new UIManager(universe);
    }

    @Test
    public void 크기_변경_테스트() throws AWTException {
        // given
        int newGridSize = 12;
        // when
        universe.reconstruct(12);
        // then
        assertEquals(universe.getGridSize(), newGridSize);
    }
}

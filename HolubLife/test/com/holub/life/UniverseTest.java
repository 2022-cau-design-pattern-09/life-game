package com.holub.life;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UniverseTest {

    private static Universe universe;

    @BeforeAll
    public static void init() {
        universe = new Universe();
    }

    @Test
    public void nextTickTest() {
        // TODO 다음 틱에 주변 셀들의 상태를 검증
    }

    @Test
    public void clickedTest(){
        //given
        Cell target = universe.getCell().at(0, 0).at(0, 0);
        //when
        target.userClicked(null, null);
        //then
        assertTrue(target.isAlive());    
    }

    @Test
    public void clearTest() {
        //given
        Cell target = universe.getCell().at(0, 0).at(0, 0);
        target.userClicked(null, null);
        //when
        universe.clear();
        //then
        assertFalse(target.isAlive());
    }

    @Test
    public void resizeTest() {
        // given
        int newGridSize = 12;
        // when
        universe.reconstruct(12);
        // then
        assertEquals(universe.getGridSize(), newGridSize);
    }
}

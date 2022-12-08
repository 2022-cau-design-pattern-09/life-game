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
    public void 다음_티커_주변_셀_검증() {
        // TODO 다음 틱에 주변 셀들의 상태를 검증
    }

    @Test
    public void 클리어_검증() {
        //given

        //when
        universe.clear();
        //then
        Cell universeCells = universe.getCell();
        assertFalse(universeCells.isAlive());
    }
}

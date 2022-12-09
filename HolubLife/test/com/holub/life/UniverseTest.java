package com.holub.life;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.holub.life.SurroundingCells.SurroundingCellsBuilder;
import com.holub.rule.BigDiamondRule;
import com.holub.rule.OriginalRule;
import com.holub.rule.Rule;

public class UniverseTest {

    private static Universe universe;

    @BeforeAll
    public static void init() {
        universe = new Universe();
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
    public void clickedTwiceTest(){
        //given
        Cell target = universe.getCell().at(0, 0).at(0, 0);
        target.userClicked(null, null); // When Target is Alive

        //when
        target.userClicked(null, null);
        //then
        assertFalse(target.isAlive());    
    }

    @Test
    public void clearTest() {
        //given
        Cell target = universe.getCell().at(0, 0).at(0, 0);
        target.userClicked(null, null); // Target is Alive
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

    @Test
    public void setRuleTest(){
        // given
        Rule rule = new OriginalRule();
        // when
        universe.setRule(rule);
        //then
        assertTrue(universe.getRule() instanceof OriginalRule);
    }

    @Test
    public void ruleChangeTest(){
        // given
        Rule rule = new BigDiamondRule();
        universe.setRule(new OriginalRule());

        // when
        universe.setRule(rule);

        // then
        assertAll(
            () -> assertTrue(universe.getRule() instanceof BigDiamondRule),
            () -> assertFalse(universe.getRule() instanceof OriginalRule)
        );
    }

    @DisplayName("3x3으로 활성화 되어있을 때, 살아있는 셀들 테스트")
    @Test
    public void nextTickTest_1() {
        Universe universe = new Universe();
        //given
        universe.setRule(new OriginalRule());
        universe.reconstruct(3);
        Cell grid = universe.getCell().at(0, 0);
        setAlive(grid.at(0, 0));
        setAlive(grid.at(0, 1));
        setAlive(grid.at(0, 2));
        setAlive(grid.at(1, 0));
        setAlive(grid.at(1, 1));
        setAlive(grid.at(1, 2));
        setAlive(grid.at(2, 0));
        setAlive(grid.at(2, 1));
        setAlive(grid.at(2, 2));

        SurroundingCells dummySurroundingCells = new SurroundingCellsBuilder()
            .setNorthWest(Cell.DUMMY)
            .setNorthEast(Cell.DUMMY)
            .setNorth(Cell.DUMMY)
            .setSouthWest(Cell.DUMMY)
            .setSouthEast(Cell.DUMMY)
            .setSouth(Cell.DUMMY)
            .setWest(Cell.DUMMY)
            .setEast(Cell.DUMMY)
            .build();

        //when

        universe.getCell().figureNextState(dummySurroundingCells, universe.getRule());
        universe.getCell().transition();
        boolean check[][] = new boolean[3][3];
        for(int row = 0; row < 3; row++){
            for(int column = 0; column < 3; column++){
                check[row][column] = grid.at(row, column).isAlive();
            }
        }

        //then
        assertAll(
            () -> assertTrue(check[0][0]),
            () -> assertFalse(check[0][1]),
            () -> assertTrue(check[0][2]),
            () -> assertFalse(check[1][0]),
            () -> assertFalse(check[1][1]),
            () -> assertFalse(check[1][2]),
            () -> assertTrue(check[2][0]),
            () -> assertFalse(check[2][1]),
            () -> assertTrue(check[2][2])
        );
    }

    @DisplayName("주변에 활성화 된 셀이 3개일 때 유지되는 셀 테스트")
    @Test
    public void nextTickTest_2() {
        //given
        universe.setRule(new OriginalRule());
        Cell grid = universe.getCell().at(0, 0);

        setAlive(grid.at(0, 0));
        setAlive(grid.at(1, 0)); 
        setAlive(grid.at(2, 0));

        setAlive(grid.at(1, 1));

        SurroundingCells dummySurroundingCells = new SurroundingCellsBuilder()
            .setNorthWest(Cell.DUMMY)
            .setNorthEast(Cell.DUMMY)
            .setNorth(Cell.DUMMY)
            .setSouthWest(Cell.DUMMY)
            .setSouthEast(Cell.DUMMY)
            .setSouth(Cell.DUMMY)
            .setWest(Cell.DUMMY)
            .setEast(Cell.DUMMY)
            .build();

        //when
        universe.getCell().figureNextState(dummySurroundingCells, universe.getRule());
        universe.getCell().transition();

        //then
        assertFalse(grid.at(0, 0).isAlive());
        assertFalse(grid.at(0, 1).isAlive());
        assertFalse(grid.at(0, 2).isAlive());
        assertFalse(grid.at(1, 0).isAlive());
        assertFalse(grid.at(1, 1).isAlive());
        assertFalse(grid.at(1, 2).isAlive());
        assertFalse(grid.at(2, 0).isAlive());
        assertFalse(grid.at(2, 1).isAlive());
        assertFalse(grid.at(2, 2).isAlive());
    }

    // 
    // Methods for Test
    // 

    private static void setAlive(Cell cell){
        if(!cell.isAlive()){
            cell.userClicked(null, null);
        }
    }
}


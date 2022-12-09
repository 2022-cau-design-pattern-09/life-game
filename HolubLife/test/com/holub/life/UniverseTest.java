package com.holub.life;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.holub.life.SurroundingCells.SurroundingCellsBuilder;
import com.holub.rule.BigDiamondRule;
import com.holub.rule.OriginalRule;
import com.holub.rule.Rule;

public class UniverseTest {
    @DisplayName("셀을 클릭 했을 때의 테스트")
    @Test
    public void clickedTest(){
        //given
        Universe universe = new Universe();
        Cell target = universe.getCell().at(0, 0).at(0, 0);
        //when
        target.userClicked(null, null);
        //then
        assertTrue(target.isAlive());    
    }

    @DisplayName("이미 클릭 된 셀 클릭 했을 때의 테스트")
    @Test
    public void clickedTwiceTest(){
        //given
        Universe universe = new Universe();
        Cell target = universe.getCell().at(0, 0).at(0, 0);
        target.userClicked(null, null); // When Target is Alive

        //when
        target.userClicked(null, null);
        //then
        assertFalse(target.isAlive());    
    }

    @DisplayName("클리어 함수의 테스트")
    @Test
    public void clearTest() {
        //given
        Universe universe = new Universe();
        Cell target = universe.getCell().at(0, 0).at(0, 0);
        target.userClicked(null, null); // Target is Alive
        //when
        universe.clear();
        //then
        assertFalse(target.isAlive());
    }

    @DisplayName("그리드 사이즈가 잘 변경되는지 테스트")
    @Test
    public void resizeTest() {
        // given
        Universe universe = new Universe();
        int newGridSize = 12;
        // when
        universe.reconstruct(12);
        // then
        assertEquals(universe.getGridSize(), newGridSize);
    }

    @DisplayName("룰이 잘 적용되는지 테스트")
    @Test
    public void setRuleTest(){
        // given
        Universe universe = new Universe();
        Rule rule = new OriginalRule();
        // when
        universe.setRule(rule);
        //then
        assertTrue(universe.getRule() instanceof OriginalRule);
    }

    @DisplayName("룰 변경이 잘 되는지 테스트")
    @Test
    public void ruleChangeTest(){
        // given
        Universe universe = new Universe();
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
        //given
        Universe universe = new Universe();
        universe.setRule(new OriginalRule());
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

    @DisplayName("주변에 활성화 된 셀이 3개일 때 새로 생성되는 셀 테스트")
    @Test
    public void nextTickTest_2() {
        //given
        Universe universe = new Universe();
        universe.setRule(new OriginalRule());
        Cell grid = universe.getCell().at(0, 0);
        
        setAlive(grid.at(0, 0));
        setAlive(grid.at(1, 2));
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
        boolean check = grid.at(1, 1).isAlive();

        //then
        assertTrue(check);
    }

    @DisplayName("작은 그리드의 경계선에서 잘 작동하는지에 대한 테스트")
    @Test
    public void nextTickTest_3() {
        //given
        Universe universe = new Universe();
        universe.setRule(new OriginalRule());
        universe.reconstruct(3);

        Cell grid1 = universe.getCell().at(0, 0);
        Cell grid2 = universe.getCell().at(0, 1);

        setAlive(grid1.at(0, 1));
        setAlive(grid2.at(0, 0));
        setAlive(grid2.at(1, 0));

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
        boolean check[] = {
            grid1.at(0, 1).isAlive(),
            grid1.at(0, 2).isAlive(),
            grid1.at(1, 2).isAlive(),
            grid2.at(0, 0).isAlive(),
            grid2.at(1, 0).isAlive()
        };

        //then
        assertAll(
            () -> assertFalse(check[0]),
            () -> assertTrue(check[1]),
            () -> assertTrue(check[2]),
            () -> assertFalse(check[3]),
            () -> assertFalse(check[4])
        );
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


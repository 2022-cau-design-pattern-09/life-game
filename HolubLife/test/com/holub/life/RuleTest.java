package com.holub.life;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.holub.rule.OriginalRule;
import com.holub.rule.RelativePosition;
import com.holub.rule.Rule;

public class RuleTest {
    @DisplayName("주변에 3개의 살아있는 셀이 있을 때, 현재 셀은 유지되는가")
    @Test
    public void OriginalRuleTest_1() {
        // given
        int gridSize = 3;
        boolean[][] alive = {
                            {true, true, false}, 
                            {true, true, false}, 
                            {false, false, false}
                        };
        Rule rule = new OriginalRule();
        Cell grid = makeGrid(gridSize, alive);
        List<Cell> neighbors = makeNeighbors(grid, 1, 1, rule);

        //when
        boolean willBeAlive = rule.willBeAlive(neighbors, true);

        //then
        assertTrue(willBeAlive);
    }

    @DisplayName("주변에 4개의 살아있는 셀이 있을 때, 현재 셀은 유지되는가")
    @Test
    public void OriginalRuleTest_2() {
        // given
        int gridSize = 3;
        boolean[][] alive = {
                            {true, true, false}, 
                            {true, true, false}, 
                            {false, false, true}
                        };
        Rule rule = new OriginalRule();
        Cell grid = makeGrid(gridSize, alive);
        List<Cell> neighbors = makeNeighbors(grid, 1, 1, rule);

        //when
        boolean willBeAlive = rule.willBeAlive(neighbors, true);

        //then
        assertFalse(willBeAlive);
    }

    @DisplayName("주변에 2개의 살아있는 셀이 있을 때, 현재 셀은 유지되는가")
    @Test
    public void OriginalRuleTest_3() {
        // given
        int gridSize = 3;
        boolean[][] alive = {
                            {true, false, true}, 
                            {false, true, false}, 
                            {false, false, false}
                        };
        Rule rule = new OriginalRule();
        Cell grid = makeGrid(gridSize, alive);
        List<Cell> neighbors = makeNeighbors(grid, 1, 1, rule);

        //when
        boolean willBeAlive = rule.willBeAlive(neighbors, true);

        //then
        assertTrue(willBeAlive);
    }

    @DisplayName("주변에 2개의 살아있는 셀이 있을 때, 새로운 살아있는 셀은 생성되는가")
    @Test
    public void OriginalRuleTest_4() {
        // given
        int gridSize = 3;
        boolean[][] alive = {
                            {true, false, false}, 
                            {false, false, true}, 
                            {false, false, false}
                        };
        Rule rule = new OriginalRule();
        Cell grid = makeGrid(gridSize, alive);
        List<Cell> neighbors = makeNeighbors(grid, 1, 1, rule);

        //when
        boolean willBeAlive = rule.willBeAlive(neighbors, false);

        //then
        assertFalse(willBeAlive);
    }

    @DisplayName("주변에 3개의 살아있는 셀이 있을 때, 새로운 살아있는 셀은 생성되는가")
    @Test
    public void OriginalRuleTest_5() {
        // given
        int gridSize = 3;
        boolean[][] alive = {
                            {true, false, false}, 
                            {false, false, true}, 
                            {false, true, false}
                        };
        Rule rule = new OriginalRule();
        Cell grid = makeGrid(gridSize, alive);
        List<Cell> neighbors = makeNeighbors(grid, 1, 1, rule);

        //when
        boolean willBeAlive = rule.willBeAlive(neighbors, false);

        //then
        assertTrue(willBeAlive);
    }

    @DisplayName("주변에 4개의 살아있는 셀이 있을 때, 새로운 살아있는 셀은 생성되는가")
    @Test
    public void OriginalRuleTest_6() {
        // given
        int gridSize = 3;
        boolean[][] alive = {
                            {true, true, false}, 
                            {false, false, true}, 
                            {false, true, false}
                        };
        Rule rule = new OriginalRule();
        Cell grid = makeGrid(gridSize, alive);
        List<Cell> neighbors = makeNeighbors(grid, 1, 1, rule);

        //when
        boolean willBeAlive = rule.willBeAlive(neighbors, false);

        //then
        assertFalse(willBeAlive);
    }



    // 
    // Methods for Test
    // 

    private static Cell makeGrid(int gridSize){
        Cell cell = new Neighborhood(gridSize, new Resident());
        return cell;
    }

    private static Cell makeGrid(int gridSize, boolean[][] alive){
        Cell cell = new Neighborhood(gridSize, new Resident());

        for(int row = 0; row < gridSize; row++){
            for(int column = 0; column < gridSize; column++){
                if(alive[row][column]){
                    setAlive(cell.at(row, column));
                }
            }
        }

        return cell;
    }

    private static void setAlive(Cell cell){
        if(!cell.isAlive()){
            cell.userClicked(null, null);
        }
    }

    private static List<Cell> makeNeighbors(Cell grid, int row, int column, Rule rule){
        List<Cell> adjacentCells = new ArrayList<>();

        for (RelativePosition relativePosition : rule.getRelativePositions()) {
            int targetRow = row + relativePosition.getDy();
            int targetColumn = column + relativePosition.getDx();

            adjacentCells.add(grid.at(targetRow, targetColumn));
        }

        return adjacentCells;
    }
}

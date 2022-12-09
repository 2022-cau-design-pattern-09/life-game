package com.holub.life;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.holub.rule.*;

public class CellTest {
    private static Universe universe;
    private static Cell grid;

    @BeforeAll
    public static void init() {
        universe = new Universe();
    }

    @Test
    public void testOfTestMethod1() {
        //given
        Cell grid = makeGrid(3);
        //when
        setAlive(grid.at(0, 0));
        //then
        assertTrue(grid.at(0, 0).isAlive());
    }

    @Test
    public void testOfTestMethod2() {
        //given
        int gridSize = 3;
        boolean[][] alive = {
                            {true, true, false}, 
                            {true, true, false}, 
                            {true, true, false}
                        };
        Cell grid = makeGrid(gridSize, alive);

        //when
        setAlive(grid.at(0, 0));

        //then
        assertTrue(grid.at(0, 0).isAlive());
        assertTrue(grid.at(0, 1).isAlive());
        assertFalse(grid.at(0, 2).isAlive());
        assertTrue(grid.at(1, 0).isAlive());
        assertTrue(grid.at(1, 1).isAlive());
        assertFalse(grid.at(1, 2).isAlive());
        assertTrue(grid.at(2, 0).isAlive());
        assertTrue(grid.at(2, 1).isAlive());
        assertFalse(grid.at(2, 2).isAlive());
    }

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

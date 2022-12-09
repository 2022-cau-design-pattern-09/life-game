package com.holub.life;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;

import com.holub.rule.RelativePosition;
import com.holub.rule.Rule;

public class CellTest {
    private static Universe universe;
    private static Cell grid;

    @BeforeAll
    public static void init() {
        universe = new Universe();
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

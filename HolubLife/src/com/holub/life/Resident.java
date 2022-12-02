package com.holub.life;

import com.holub.constant.Colors;
import com.holub.rule.OriginalRule;
import com.holub.rule.Rule;

import java.awt.*;
import java.util.List;

/*** ****************************************************************
 * The Resident class implements a single cell---a "resident" of a
 * block.
 *
 * @include /etc/license.txt
 */

public final class Resident implements Cell {
    private static final Color BORDER_COLOR = Colors.DARK_YELLOW.getColor();
    private static final Color LIVE_COLOR = Colors.RED.getColor();
    private static final Color DEAD_COLOR = Colors.LIGHT_YELLOW.getColor();

    private boolean amAlive = false;
    private boolean willBeAlive = false;

    private boolean isStable() {
        return amAlive == willBeAlive;
    }

    /**
     * figure the next state.
     *
     * @return true if the cell is not stable (will change state on the
     * next transition().
     */

    public boolean figureNextState(List<Cell> neighborResidents)
    {
        for (Cell cell : neighborResidents) {
            verify(cell, "[unknown]"); // TODO: show direction
        }

        int neighbors = 0;
        for (Cell cell : neighborResidents) {
            if (cell.isAlive()) neighbors++;
        }

        Rule rule = new OriginalRule(); // TEST

        willBeAlive = (amAlive && rule.isNumberToSustain(neighbors)) ||
                (!amAlive && rule.isNumberToBorn(neighbors));

        return !isStable();
    }

    @Override
    public boolean figureNextState(SurroundingCells surroundingCells)
    {
        return false;
    }

    private void verify(Cell c, String direction) {
        assert (c instanceof Resident) || (c == Cell.DUMMY)
                : "incorrect type for " + direction + ": " +
                c.getClass().getName();
    }

    /**
     * This cell is monetary, so it's at every edge of itself. It's
     * an internal error for any position except for (0,0) to be
     * requsted since the width is 1.
     */
    public Cell edge(int row, int column) {
        assert row == 0 && column == 0;
        return this;
    }

    public boolean transition() {
        boolean changed = isStable();
        amAlive = willBeAlive;
        return changed;
    }

    public void redraw(Graphics g, Rectangle here, boolean drawAll) {
        g = g.create();
        g.setColor(amAlive ? LIVE_COLOR : DEAD_COLOR);
        g.fillRect(here.x + 1, here.y + 1, here.width - 1, here.height - 1);

        // Doesn't draw a line on the far right and bottom of the
        // grid, but that's life, so to speak. It's not worth the
        // code for the special case.

        g.setColor(BORDER_COLOR);
        g.drawLine(here.x, here.y, here.x, here.y + here.height);
        g.drawLine(here.x, here.y, here.x + here.width, here.y);
        g.dispose();
    }

    public void userClicked(Point here, Rectangle surface) {
        amAlive = !amAlive;
    }

    public void clear() {
        amAlive = willBeAlive = false;
    }

    public boolean isAlive() {
        return amAlive;
    }

    public Cell create() {
        return new Resident();
    }

    public int widthInCells() {
        return 1;
    }

    public Direction isDisruptiveTo() {
        return isStable() ? Direction.NONE : Direction.ALL;
    }

    public boolean transfer(Storable blob, Point upperLeft, boolean doLoad) {
        Memento memento = (Memento) blob;
        if (doLoad) {
            if (amAlive = willBeAlive = memento.isAlive(upperLeft))
                return true;
        } else if (amAlive)                    // store only live cells
            memento.markAsAlive(upperLeft);

        return false;
    }

    /**
     * Mementos must be created by Neighborhood objects. Throw an
     * exception if anybody tries to do it here.
     */
    public Storable createMemento() {
        throw new UnsupportedOperationException(
                "May not create memento of a unitary cell");
    }
}

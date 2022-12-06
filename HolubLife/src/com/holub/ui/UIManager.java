package com.holub.ui;

import com.holub.constant.Modifier;
import com.holub.life.Cell;
import com.holub.life.Clock;
import com.holub.life.SurroundingCells;
import com.holub.life.SurroundingCells.SurroundingCellsBuilder;
import com.holub.life.Universe;
import com.holub.Theme.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * The Universe is a mediator that sits between the Swing
 * event model and the Life classes. It is also a singleton,
 * accessed via Universe.instance(). It handles all
 * Swing events and translates them into requests to the
 * outermost Neighborhood. It also creates the Composite
 * Neighborhood.
 *
 * @include /etc/license.txt
 */

public class UIManager extends JPanel implements Observer {

    private static final int DEFAULT_CELL_SIZE = 8;
    private Theme theme;

    Universe model;

    public UIManager(Universe model) {
        this.model = model;
        model.addObserver(this);
        theme = new OrangeTheme();
    }

    public void setTheme(Theme theme){
        this.theme = theme;
    }

    public void establish() {
        setWindow();
        createMenu();
        createEventListener();
    }

    private void setWindow() {
        Dimension PREFERRED_SIZE = new Dimension(
                model.getCell().widthInCells() * DEFAULT_CELL_SIZE,
                model.getCell().widthInCells() * DEFAULT_CELL_SIZE);

        setBackground(Color.white);
        setPreferredSize(PREFERRED_SIZE);
        setMaximumSize(PREFERRED_SIZE);
        setMinimumSize(PREFERRED_SIZE);
        setOpaque(true);
    }

    private void createMenu() {
        createGridMenu();
        createGoMenu();
        createThemeMenu();
    }

    private void createGoMenu() {
        MenuSite.addLine(this, "Go", "Tick", e -> {
            Clock.instance().tick();
        });
        MenuSite.addLine(this, "Go", "Hart", e -> {
            Clock.instance().stop();
        });
        MenuSite.addLine(this, "Go", "Agonizing", e -> {
            Clock.instance().startTicking(Modifier.AGONIZING);
        });
        MenuSite.addLine(this, "Go", "Slow", e -> {
            Clock.instance().startTicking(Modifier.SLOW);
        });
        MenuSite.addLine(this, "Go", "Medium", e -> {
            Clock.instance().startTicking(Modifier.MEDIUM);
        });
        MenuSite.addLine(this, "Go", "Fast", e -> {
            Clock.instance().startTicking(Modifier.FAST);
        });
    }

    private void createGridMenu() {
        MenuSite.addLine(this, "Grid", "Clear", e -> {
            model.clear();
        });

        MenuSite.addLine(this, "Grid", "Load", e -> {
            try {
                model.doLoad();
            } catch (IOException theException) {
                JOptionPane.showMessageDialog(null, "Read Failed!",
                        "The Game of Life", JOptionPane.ERROR_MESSAGE);
            }
        });

        MenuSite.addLine(this, "Grid", "Store", e -> {
            try {
                model.doStore();
            } catch (IOException theException) {
                JOptionPane.showMessageDialog(null, "Write Failed!",
                        "The Game of Life", JOptionPane.ERROR_MESSAGE);
            }
        });

        MenuSite.addLine(this, "Grid", "Exit", e -> {
            System.exit(0);
        });
    }

    private void createThemeMenu() {
        MenuSite.addLine(this, "Theme", "Orange", e -> {
            setTheme(new OrangeTheme());
            paint(getGraphics());
        });

        MenuSite.addLine(this, "Theme", "Gray", e -> {
            setTheme(new GrayTheme());
            paint(getGraphics());
        });
    }


    private void createEventListener(){
        addComponentListener(
                new ComponentAdapter() {
                     public void componentResized(ComponentEvent e) {
                         Rectangle bounds = getBounds();
                         int outermostCellSize = Math.min(bounds.width, bounds.height);
                         outermostCellSize /= model.getCell().widthInCells();
                         outermostCellSize *= model.getCell().widthInCells();
                         bounds.height = bounds.width = outermostCellSize;
                         setBounds(bounds);
                     }
                });

        addMouseListener(
                new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        Rectangle bounds = getBounds();
                        bounds.x = 0;
                        bounds.y = 0;
                        model.getCell().userClicked(e.getPoint(), bounds);
                        repaint();
                    }
                });

        Clock.instance().addClockListener(
                () -> {
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
                    if (model.getCell().figureNextState(dummySurroundingCells, model.getRule()) && model.getCell().transition()) {
                        refreshNow();
                    }
                }
        );
    }

    /**
     * Override paint to ask the outermost Neighborhood
     * (and any subcells) to draw themselves recursively.
     * All knowledge of screen size is also encapsulated.
     * (The size is passed into the outermost <code>Cell</code>.)
     */

    @Override
    public void paint(Graphics g) {
        Rectangle panelBounds = getBounds();

        // The panel bounds is relative to the upper-left
        // corner of the screen. Pretend that it's at (0,0)
        panelBounds.x = 0;
        panelBounds.y = 0;
        redraw(g, panelBounds, true);      //{=Universe.redraw1}
    }

    public void redraw(Graphics g, Rectangle here, boolean drawAll){
        Cell cell = model.getCell();
        draw(cell, g, here, drawAll);
    }



    public void draw(Cell cell, Graphics g, Rectangle here, boolean drawAll){
        if(!cell.shouldDraw() && !drawAll){
            return;
        }

        int compoundWidth = here.width;
        int gridSize = model.getGridSize();

        Cell[][] subcell = cell.subcell();
        if(subcell == null){
            g = g.create();
            g.setColor(cell.isAlive() ? theme.cellLiveColor() : theme.cellDeadColor());
            g.fillRect(here.x + 1, here.y + 1, here.width - 1, here.height - 1);
    
            // Doesn't draw a line on the far right and bottom of the
            // grid, but that's life, so to speak. It's not worth the
            // code for the special case.
    
            g.setColor(theme.cellBorderColor());
            g.drawLine(here.x, here.y, here.x, here.y + here.height);
            g.drawLine(here.x, here.y, here.x + here.width, here.y);
            g.dispose();
        } else {
            //oneLastRefreshRequired = false;
            Rectangle subrect = new Rectangle(here.x, here.y,
                    here.width / gridSize,
                    here.height / gridSize);

            // Check to see if we can paint. If not, just return. If
            // so, actually wait for permission (in case there's
            // a race condition, then paint.

            for (int row = 0; row < gridSize; ++row) {
                for (int column = 0; column < gridSize; ++column) {
                    draw(subcell[row][column], g, subrect, drawAll);    // {=Neighborhood.redraw3}
                    subrect.translate(subrect.width, 0);
                }
                subrect.translate(-compoundWidth, subrect.height);
            }

            g = g.create();
            g.setColor(theme.gridBorderColor());
            g.drawRect(here.x, here.y, here.width, here.height);

            if (cell.isAlive()) {
                g.setColor(theme.gridActiveColor());
                g.drawRect(here.x + 1, here.y + 1,
                        here.width - 2, here.height - 2);
            }

            g.dispose();
        }
    }

    /**
     * Force a screen refresh by queing a request on
     * the Swing event queue. This is an example of the
     * Active Object pattern (not covered by the Gang of Four).
     * This method is called on every clock tick. Note that
     * the redraw() method on a given <code>Cell</code>
     * does nothing if the <code>Cell</code> doesn't
     * have to be refreshed.
     */

    private void refreshNow() {
        SwingUtilities.invokeLater(
                () -> {
                        Graphics g = getGraphics();
                        if (g == null)        // Universe not displayable
                            return;
                        try {
                            Rectangle panelBounds = getBounds();
                            panelBounds.x = 0;
                            panelBounds.y = 0;
                            redraw(g, panelBounds, false); //{=Universe.redraw2}
                        } finally{
                            g.dispose();
                        }
                    }
                );
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}

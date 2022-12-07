package com.holub.life;

import com.holub.io.Files;
import com.holub.rule.OriginalRule;
import com.holub.rule.Rule;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Observable;

public class Universe extends Observable {
    private static final int DEFAULT_GRID_SIZE = 8;

    private Cell outermostCell;
    private Rule rule;

    public Universe() {
        outermostCell = new Neighborhood(
                DEFAULT_GRID_SIZE, new Neighborhood(
                        DEFAULT_GRID_SIZE, new Resident()));

        rule = new OriginalRule();
    }

    public void clear() {
        outermostCell.clear();
        notifyObservers();
    }

    public void doLoad() throws IOException {
        FileInputStream in = new FileInputStream(
                Files.userSelected(".", ".life", "Life File", "Load"));

        Clock.instance().stop();
        outermostCell.clear();

        Storable memento = outermostCell.createMemento();
        memento.load(in);
        outermostCell.transfer(memento, new Point(0, 0), Cell.LOAD);

        in.close();

        notifyObservers();
    }

    public void doStore() throws IOException {
        FileOutputStream out = new FileOutputStream(
                Files.userSelected(".", ".life", "Life File", "Write"));

        Clock.instance().stop();

        Storable memento = outermostCell.createMemento();
        outermostCell.transfer(memento, new Point(0, 0), Cell.STORE);
        memento.flush(out);

        out.close();
    }

    public Cell getCell() {
        return outermostCell;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public int getGridSize(){
        return DEFAULT_GRID_SIZE;
    }
}

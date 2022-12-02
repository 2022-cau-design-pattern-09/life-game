package com.holub.ui;

import com.holub.life.Universe;

public class GridMenuGenerator implements MenuGenerator{
    public void generate(Object requester){
        MenuSite.addLine(requester, "Grid", "Clear", e -> {
            Universe.instance().clear();
        });

        MenuSite.addLine(requester, "Grid", "Load", e -> {
            Universe.instance().doLoad();
        });

        MenuSite.addLine(requester, "Grid", "Store", e -> {
            Universe.instance().doStore();
        });

        MenuSite.addLine(requester, "Grid", "Exit", e -> {
            System.exit(0);
        });
    }
}

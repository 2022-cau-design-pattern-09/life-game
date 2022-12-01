package com.holub.ui;

import com.holub.life.Clock;
import com.holub.life.Universe;
import com.holub.life.enumeration.Modifier;

public class MenuGenerator {

    public static void generate() {
        gridMenuGenerate(new Object());
        goMenuGenerate(new Object());
    }

    private static void gridMenuGenerate(Object requester) {
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

    private static void goMenuGenerate(Object requester) {
        MenuSite.addLine(requester, "Go", "Tick", e -> {
            Clock.instance().tick();
        });
        MenuSite.addLine(requester, "Go", "Hart", e -> {
            Clock.instance().startTicking(Modifier.HART.getInterval());
        });
        MenuSite.addLine(requester, "Go", "Agonizing", e -> {
            Clock.instance().startTicking(Modifier.AGONIZING.getInterval());
        });
        MenuSite.addLine(requester, "Go", "Slow", e -> {
            Clock.instance().startTicking(Modifier.SLOW.getInterval());
        });
        MenuSite.addLine(requester, "Go", "Medium", e -> {
            Clock.instance().startTicking(Modifier.MEDIUM.getInterval());
        });
        MenuSite.addLine(requester, "Go", "Fast", e -> {
            Clock.instance().startTicking(Modifier.FAST.getInterval());
        });
    }
}

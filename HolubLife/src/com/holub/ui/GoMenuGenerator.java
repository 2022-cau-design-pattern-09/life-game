package com.holub.ui;

import com.holub.constant.Modifier;
import com.holub.life.Clock;

public class GoMenuGenerator implements MenuGenerator{
    public void generate(Object requester){
        MenuSite.addLine(requester, "Go", "Tick", e -> {
            Clock.instance().tick();
        });
        MenuSite.addLine(requester, "Go", "Hart", e -> {
            Clock.instance().stop();
        });
        MenuSite.addLine(requester, "Go", "Agonizing", e -> {
            Clock.instance().startTicking(Modifier.AGONIZING);
        });
        MenuSite.addLine(requester, "Go", "Slow", e -> {
            Clock.instance().startTicking(Modifier.SLOW);
        });
        MenuSite.addLine(requester, "Go", "Medium", e -> {
            Clock.instance().startTicking(Modifier.MEDIUM);
        });
        MenuSite.addLine(requester, "Go", "Fast", e -> {
            Clock.instance().startTicking(Modifier.FAST);
        });
    }
}

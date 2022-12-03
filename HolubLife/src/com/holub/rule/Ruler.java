package com.holub.rule;

import com.holub.life.Cell;
import java.util.List;


public class Ruler {
    private Rule rule;
    private static Ruler instance = new Ruler();
    
    private Ruler(){
        rule = new DummyRule();
    }

    public static Ruler instance(){
        return instance;
    }

    public static void setRule(Rule rule){
        instance.rule = rule;
    }

    public static Rule rule(){
        return instance.rule;
    }

    public static boolean willBeAlive(List<Cell> neighborResidents, boolean amAlive){
        return instance.rule.willBeAlive(neighborResidents, amAlive);
    }
}

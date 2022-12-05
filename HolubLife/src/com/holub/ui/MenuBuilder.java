package com.holub.ui;

public class MenuBuilder{
    public static void generate(Object requester){
        MenuGenerator[] menus = {new GoMenuGenerator(), new GridMenuGenerator()};
        for(MenuGenerator menu : menus){
            menu.generate(requester);
        }
    }
}
package com.holub.life;

import com.holub.ui.MenuSite;
import com.holub.ui.UIManager;

import javax.swing.*;
import java.awt.*;

/*******************************************************************
 * An implemenation of Conway's Game of Life.
 *
 * @include /etc/license.txt
 */

public final class Life extends JFrame {

    public static void main(String[] arguments) {
        new Life();
    }

    private Life() {
        super("The Game of Life. "
                + "(c)2003 Allen I. Holub <http://www.holub.com>");

        // Must establish the MenuSite very early in case
        // a subcomponent puts menus on it.
        MenuSite.establish(this);

        Universe universe = new Universe();
        UIManager ui = new UIManager(universe);

        ui.establish();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(ui, BorderLayout.CENTER); //{=life.java.install}

        pack();
        setVisible(true);
    }
}

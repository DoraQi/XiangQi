package ui.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a menu item with specific properties set
 */
public class CustomMenuItem extends JMenuItem {
    private static final String FONT = "Ariel";
    private static final int DEFAULT_SIZE = 17;

    // EFFECTS: instantiates a CustomMenuItem
    public CustomMenuItem(String str) {
        super(str);
        setFont(new Font(FONT, Font.PLAIN, DEFAULT_SIZE));
    }
}

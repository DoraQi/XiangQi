package ui;

import javax.swing.*;
import java.awt.*;

public class CustomMenuItem extends JMenuItem {
    private static final String FONT = "Ariel";
    private static final int DEFAULT_SIZE = 17;

    public CustomMenuItem(String str, int size) {
        super(str);
        setFont(new Font(FONT, Font.PLAIN, size));
    }

    public CustomMenuItem(String str) {
        super(str);
        setFont(new Font(FONT, Font.PLAIN, DEFAULT_SIZE));
    }
}

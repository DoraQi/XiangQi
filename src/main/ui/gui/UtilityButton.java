package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Represents a utility button on the side panels of XiangQi
 */
public class UtilityButton extends JButton {
    // EFFECTS: instantiates an UtilityButton
    public UtilityButton(String buttonName) {
        super(buttonName);
        this.setVisible(true);
        this.setBorder(BorderFactory.createBevelBorder(0));
        this.setBackground(Color.lightGray);
        setFocusable(false);
    }
}

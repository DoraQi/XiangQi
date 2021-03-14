package ui;

import javax.swing.*;
import java.util.Objects;

public class GameButton extends JButton {

    private final String buttonName;

    public GameButton(String buttonName) {
        super(buttonName);
        this.buttonName = buttonName;
        this.setVisible(true);
        this.setBorder(BorderFactory.createBevelBorder(0));
    }

    @Override
    public String toString() {
        return buttonName;
    }

}

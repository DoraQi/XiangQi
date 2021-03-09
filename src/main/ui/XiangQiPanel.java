package ui;

import javax.swing.*;
import java.awt.*;

public class XiangQiPanel extends JPanel {
    private JLabel background = new JLabel(new ImageIcon(".\\data\\gameboard-resized.png"));

    public static final Color COLOUR = Color.black;

    public XiangQiPanel() {
        background.setVisible(true);
        setBackground(COLOUR);
        this.setOpaque(true);
        add(background, JLayeredPane.DEFAULT_LAYER);
        setVisible(true);
    }
}
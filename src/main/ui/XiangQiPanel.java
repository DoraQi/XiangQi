package ui;

import javax.swing.*;
import java.awt.*;

public class XiangQiPanel extends JPanel {
    private JLabel background = new JLabel(new ImageIcon(".\\data\\gameboard-resized.png"));

    public static final Color COLOUR = Color.black;

    public XiangQiPanel() {
        background.setVisible(true);
        this.add(background);
        this.setBounds(background.getBounds());
        this.setOpaque(true);
        add(background, JLayeredPane.DEFAULT_LAYER);
        setVisible(true);
    }
}
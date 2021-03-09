package ui;

import javax.swing.*;
import java.awt.*;

public class XiangQiPanel extends JPanel {
    private JLabel background = new JLabel(new ImageIcon(".\\data\\gameboard-resized.png"));

    public static final Color COLOUR = new Color(0x807263);

    public XiangQiPanel() {
        background.setVisible(true);
        setBackground(COLOUR);
        add(background);
        setVisible(true);
    }
}
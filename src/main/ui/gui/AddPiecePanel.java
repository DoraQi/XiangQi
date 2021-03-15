package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddPiecePanel extends JPanel {
    public static final int WIDTH = 300;
    private JTextPane textPane;
    private GameButton submitButton;

    public AddPiecePanel(ActionListener listener) {
        setLayout(null);
        setPreferredSize(new Dimension(WIDTH, 100));
        setOpaque(true);
        setBackground(Color.WHITE);
        setUpTextPane();
        setUpSubmitButton(listener);
    }

    private void setUpTextPane() {
        textPane = new JTextPane();
        textPane.setBackground(Color.lightGray);
        textPane.setBounds(WIDTH / 8, WIDTH / 8, WIDTH * 3 / 4, WIDTH);
        textPane.setFont(new Font(GameFrame.DEFAULT_FONT, Font.PLAIN, 16));
        this.add(textPane);
    }

    private void setUpSubmitButton(ActionListener listener) {
        submitButton = new GameButton("submit");
        submitButton.setBounds(WIDTH / 3, WIDTH / 8 + WIDTH / 4 + WIDTH, WIDTH / 3, 30);
        submitButton.addActionListener(listener);
        submitButton.setBackground(Color.lightGray);
        this.add(submitButton);
    }

    public String getInpt() {
        String text = textPane.getText().trim().toLowerCase();
        textPane.setText("");
        return text;
    }
}

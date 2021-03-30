package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Represents a panel for adding new pieces to the board
 */
public class AddPiecePanel extends JPanel {
    public static final int WIDTH = 300;
    private JTextPane textPane;

    // EFFECTS: instantiate an AddPiecePanel and set up its fields and settings
    public AddPiecePanel(ActionListener listener) {
        setupPanel();
        setUpTextPane();
        setUpSubmitButton(listener);
    }

    // EFFECTS: setup the panel
    private void setupPanel() {
        setLayout(null);
        setPreferredSize(new Dimension(WIDTH, 100));
        setOpaque(true);
        setBackground(Color.WHITE);
    }

    // EFFECTS: setup the text pane going onto the panel
    private void setUpTextPane() {
        textPane = new JTextPane();
        textPane.setBackground(Color.lightGray);
        textPane.setBounds(WIDTH / 8, WIDTH / 8, WIDTH * 3 / 4, WIDTH);
        textPane.setFont(new Font(GameFrame.DEFAULT_FONT, Font.PLAIN, 16));
        this.add(textPane);
    }

    // EFFECTS: setup the submit button
    private void setUpSubmitButton(ActionListener listener) {
        UtilityButton submitButton = new UtilityButton("submit");
        submitButton.setBounds(WIDTH / 3, WIDTH / 8 + WIDTH / 4 + WIDTH, WIDTH / 3, 30);
        submitButton.addActionListener(listener);
        this.add(submitButton);
    }

    // EFFECTS: return the text in the text box and clear the text box
    public String getInput() {
        String text = textPane.getText().trim().toLowerCase();
        textPane.setText("");
        return text;
    }
}

package ui.gui;

import model.components.GameBoard;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a panel that displays the game board
 */
public class XiangQiPanel extends JPanel {
    private GameFrame frame;
    private GameBoard board;
    private Map<String, GameButton> locButton;

    // EFFECTS: instantiate a XiangQiPanel
    public XiangQiPanel(GameFrame gameFrame, GameBoard gb) {
        board = gb;
        frame = gameFrame;
        locButton = new HashMap<>();
        setupProperties();
        setUpButtons();
    }

    // MODIFIES: this
    // EFFECTS: setup basic properties of the panel
    private void setupProperties() {
        this.setBackground(new Color(184, 175, 162));
        this.setOpaque(false);
        this.setBounds(50, 50, 96 * 9, 96 * 10);
        this.setPreferredSize(new Dimension(96 * 9, 96 * 10));
        this.setLayout(new GridLayout(10, 9));
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: setup all the game buttons on the panel
    private void setUpButtons() {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 9; x++) {
                GameButton button = new GameButton(x, 9 - y);
                locButton.put(String.valueOf(x) + (9 - y), button);
                this.add(button, x + y * 9);
                button.addActionListener(frame.getActionListener());
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: update the panel to display the current status of the board
    public void updateDisplay() {
        for (String loc : locButton.keySet()) {
            int x = Integer.parseInt(loc) / 10;
            int y = Integer.parseInt(loc) % 10;
            GameButton button = locButton.get(loc);
            if (board.isEmptyAt(x, y)) {
                button.toDefaultDisplay();
            } else {
                button.displayPiece(board.getPAt(x, y));
            }
        }
    }
}
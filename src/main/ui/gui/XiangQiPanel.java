package ui.gui;

import exception.IllegalInputException;
import model.components.GameBoard;
import model.pieces.Horse;
import model.pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class XiangQiPanel extends JPanel {
    private GameFrame frame;
    private GameBoard board;
    private Map<String, OnOffGameButton> locButton;

    public XiangQiPanel(GameFrame gameFrame, GameBoard gb) {
        board = gb;
        this.setBackground(new Color(184, 175, 162));
        this.setOpaque(false);
        this.setBounds(50, 50, 96 * 9, 96 * 10);
        this.setPreferredSize(new Dimension(96 * 9, 96 * 10));
        this.setVisible(true);
        frame = gameFrame;
        locButton = new HashMap<>();
        this.setLayout(new GridLayout(10, 9));
        setUpButtons();
    }

    private void setUpButtons() {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 9; x++) {
                OnOffGameButton button = new OnOffGameButton(x, 9 - y);
                locButton.put(String.valueOf(x) + (9 - y), button);
                this.add(button, x + y * 9);
                button.addActionListener(frame.getActionListener());
            }
        }
    }

    public synchronized void updateDisplay() {
        for (String loc : locButton.keySet()) {
            int x = Integer.parseInt(loc) / 10;
            int y = Integer.parseInt(loc) % 10;
            OnOffGameButton button = locButton.get(loc);
            if (board.isEmptyAt(x, y)) {
                button.toDefaultDisplay();
            } else {
                button.displayPiece(board.getPAt(x, y));
            }
        }
    }
}
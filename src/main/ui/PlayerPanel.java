package ui;

import model.components.Player;
import model.pieces.Piece;
import ui.gui.GameButton;
import ui.gui.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayerPanel extends JPanel {
    private GameFrame frame;
    private Player player;
    private final Color colour;
    private JLabel playerLabel;
    private JButton unselectButton;
    JPanel capturedPieces;

    private static final int HEIGHT = 100;
    private static final int PLAYER_ICON_SIZE = 70;
    private static final int CAPTURED_DISPLAY_HEIGHT = 80;
    private static final int CAPTURED_DISPLAY_WIDTH = 600;

    public PlayerPanel(Player p, GameFrame f) {
        frame = f;
        player = p;
        setPreferredSize(new Dimension(100, HEIGHT));
        setBackground(Color.WHITE);
        setLayout(null);
        if (player.isRed()) {
            colour = new Color(161, 19, 3);
        } else {
            colour = new Color(6, 13, 38);
        }
        displayPlayerIcon();
        displayCapturedPieces();
        addUnselectButton();
        setVisible(true);
    }

    public void update() {
        displayCapturedPieces();
    }

    private void addUnselectButton() {
        unselectButton = new GameButton("unselect");
        unselectButton.setBackground(colour);
        unselectButton.setForeground(Color.WHITE);
        unselectButton.setFocusable(false);
        unselectButton.setBorder(BorderFactory.createSoftBevelBorder(1, colour, Color.lightGray));
        unselectButton.setBounds(250 + CAPTURED_DISPLAY_WIDTH, (HEIGHT - 50) / 2, 100, 50);
        this.add(unselectButton);
        unselectButton.addActionListener(frame.getActionListener());
    }

    private void displayPlayerIcon() {
        playerLabel = new JLabel();
        playerLabel.setOpaque(true);
        playerLabel.setPreferredSize(new Dimension(100, 100));
        playerLabel.setBackground(colour);
        playerLabel.setForeground(colour);
        playerLabel.setBounds(50, HEIGHT / 2 - PLAYER_ICON_SIZE / 2, PLAYER_ICON_SIZE, PLAYER_ICON_SIZE);
        this.add(playerLabel);
    }

    private void displayCapturedPieces() {
        capturedPieces = new JPanel();
        capturedPieces.setLayout(new FlowLayout());
        capturedPieces.setBounds(200, 100 / 2 - CAPTURED_DISPLAY_HEIGHT / 2,
                CAPTURED_DISPLAY_WIDTH, CAPTURED_DISPLAY_HEIGHT);
        capturedPieces.setBackground(Color.lightGray);
        ArrayList<Piece> pieces = player.getCapturedPieces();
        for (Piece p : pieces) {
            JLabel pieceLabel = new JLabel(p.getPieceClass().toString());
            pieceLabel.setPreferredSize(new Dimension(150, 100));
            pieceLabel.setFont(new Font(GameFrame.DEFAULT_FONT, Font.PLAIN, 12));
            pieceLabel.setForeground(colour);
            capturedPieces.add(pieceLabel);
        }
        this.add(capturedPieces);
    }
}

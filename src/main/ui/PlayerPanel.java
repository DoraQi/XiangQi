package ui;

import model.components.Player;
import model.pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayerPanel extends JPanel {
    private Player player;
    private final Color colour;
    JPanel capturedPieces;

    public PlayerPanel(Player p) {
        player = p;
        setPreferredSize(new Dimension(100, 100));
        setBackground(Color.WHITE);
        setLayout(new FlowLayout());
        if (player.isRed()) {
            colour = new Color(161, 19, 3);
        } else {
            colour = new Color(6, 13, 38);
        }
        displayPlayerIcon();
        displayCapturedPieces();
        setVisible(true);
    }

    private void displayPlayerIcon() {
        JPanel playerLabel = new JPanel();
        playerLabel.setPreferredSize(new Dimension(50, 50));
        playerLabel.setBackground(colour);
        this.add(playerLabel);
    }

    private void displayCapturedPieces() {
        capturedPieces = new JPanel();
        ArrayList<Piece> pieces = player.getCapturedPieces();
        for (Piece p : pieces) {
            JLabel pieceLabel = new JLabel(p.getPieceClass().toString());
            pieceLabel.setPreferredSize(new Dimension(100, 100));
            pieceLabel.setFont(new Font(GameFrame.DEFAULT_FONT, Font.PLAIN, 12));
            pieceLabel.setForeground(colour);
            capturedPieces.add(pieceLabel);
        }
        this.add(capturedPieces);
    }
}

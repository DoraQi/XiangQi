package ui;

import model.components.Player;
import model.pieces.Piece;
import sun.util.resources.pt.CalendarData_pt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayerPanel extends JPanel {
    private GameFrame frame;
    private Player player;
    private final Color colour;
    private JLabel playerLabel;
    private JButton surrenderButton;
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
        addSurrenderButton();
        setVisible(true);
    }

    private void addSurrenderButton() {
        surrenderButton = new GameButton("surrender");
        surrenderButton.setBackground(colour);
        surrenderButton.setForeground(Color.WHITE);
        surrenderButton.setFocusable(false);
        surrenderButton.setBorder(BorderFactory.createSoftBevelBorder(1, colour, Color.lightGray));
        surrenderButton.setBounds(250 + CAPTURED_DISPLAY_WIDTH, (HEIGHT - 50) / 2, 100, 50);
        this.add(surrenderButton);
        surrenderButton.addActionListener(frame);
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
            pieceLabel.setPreferredSize(new Dimension(100, 100));
            pieceLabel.setFont(new Font(GameFrame.DEFAULT_FONT, Font.PLAIN, 12));
            pieceLabel.setForeground(colour);
            capturedPieces.add(pieceLabel);
        }
        this.add(capturedPieces);
    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == surrenderButton) {
//            JPanel surrenderPanel = new JPanel();
//            JLabel message = new JLabel("CONGRATULATIONS\nYOU WIN!");
//            message.setFont(new Font("Comic Sans", Font.BOLD, 20));
//            message.setForeground(colour);
//            surrenderPanel.add(message);
//            this.add(surrenderPanel, CENTER_ALIGNMENT);
//            surrenderButton.setEnabled(false);
//            frame.handleSurrender(surrenderPanel);
//        }
//    }
}

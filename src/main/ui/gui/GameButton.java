package ui.gui;

import model.pieces.Piece;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a button on board that displays a piece or an empty square
 */
public class GameButton extends JButton {
    private int posX;
    private int posY;

    private static final Color DEFAULT_ICON_COLOUR = new Color(0, 0, 0, 0);
    private static final ImageIcon DEFAULT_ICON = new ImageIcon(".\\data\\transparentImage.png");

    // EFFECTS: instantiate a BoardButton that displays board information at (x, y) position
    public GameButton(int x, int y) {
        super(DEFAULT_ICON);
        posX = x;
        posY = y;
        setupButtonProperties();
    }

    // MODIFIES: this
    // EFFECTS: setup basic properties of this button
    private void setupButtonProperties() {
        setFocusable(false);
        setOpaque(false);
        setBorder(null);
        toDefaultDisplay();
    }

    // MODIFIES: this
    // EFFECTS: display the image of the given piece
    public void displayPiece(Piece p) {
        setIcon(new ImageIcon(PieceImageLocFinder.findImageForPiece(p)));
    }

    // MODIFIES: this
    // EFFECTS: set the button to display an empty, transparent image
    public void toDefaultDisplay() {
        setIcon(DEFAULT_ICON);
        setBackground(DEFAULT_ICON_COLOUR);
        setOpaque(false);
    }

    // getters
    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }


}

package ui.gui;

import model.pieces.Piece;
import model.pieces.PieceClass;

import javax.swing.*;
import java.awt.*;

public class OnOffGameButton extends JButton {
    private int posX;
    private int posY;

    private static final int ICON_WIDTH = 96;
    private static final int ICON_HEIGHT = 96;
    private static final Color DEFAULT_ICON_COLOUR = new Color(0, 0, 0, 0);
    private static final ImageIcon DEFAULT_ICON = new ImageIcon(".\\data\\transparentImage.png");
    private static final Color RED = new Color(161, 19, 3);
    private static final Color BLACK = new Color(6, 13, 38);


    public OnOffGameButton(int x, int y) {
        super(DEFAULT_ICON);
        posX = x;
        posY = y;
        setFocusable(false);
        setBackground(DEFAULT_ICON_COLOUR);
        setOpaque(false);
    }

    public void selectButton() {
        Color currentC = this.getBackground();
        setBackground(new Color((float) (currentC.getRed() / 255.0), (float) (currentC.getGreen() / 255.0),
                (float) (currentC.getBlue() / 255.0), .5f));
        setOpaque(false);
    }

    public void unselectButton() {
        Color currentC = this.getBackground();
        setBackground(new Color((float) (currentC.getRed() / 255.0), (float) (currentC.getGreen() / 255.0),
                (float) (currentC.getBlue() / 255.0), 0));
        setOpaque(true);
    }

    public void displayPiece(Piece p) {
        switch (p.getPieceClass()) {
            case CANNON:
                setIcon(new ImageIcon(".\\data\\cannon.png"));
                break;
            case HORSE:
                setIcon(new ImageIcon(".\\data\\horse.png"));
                break;
            case ADVISOR:
                setIcon(new ImageIcon(".\\data\\advisor.png"));
                break;
            case CHARIOT:
                setIcon(new ImageIcon(".\\data\\chariot.png"));
                break;
            default:
                displayPiece2(p);
        }
        setColour(p);
    }

    private void displayPiece2(Piece p) {
        switch (p.getPieceClass()) {
            case GENERAL:
                setIcon(new ImageIcon(".\\data\\generalred.png"));
                break;
            case SOLDIER:
                setIcon(new ImageIcon(".\\data\\soldierblk.png"));
                break;
            case ELEPHANT:
                setIcon(new ImageIcon(".\\data\\elephantred.png"));
                break;
        }
    }

    private void setColour(Piece p) {
        if (p.isRed()) {
            setBackground(RED);
            setOpaque(true);
        } else {
            setBackground(BLACK);
            setOpaque(true);
        }
    }

    public void toDefaultDisplay() {
        setIcon(DEFAULT_ICON);
        setOpaque(false);
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }


}

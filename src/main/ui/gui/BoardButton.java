package ui.gui;

import model.pieces.Piece;
import model.pieces.PieceClass;

import javax.swing.*;
import java.awt.*;

public class BoardButton extends JButton {
    private int posX;
    private int posY;

    private static final int ICON_WIDTH = 96;
    private static final int ICON_HEIGHT = 96;
    private static final Color DEFAULT_ICON_COLOUR = new Color(0, 0, 0, 0);
    private static final ImageIcon DEFAULT_ICON = new ImageIcon(".\\data\\transparentImage.png");
    private static final Color RED = new Color(161, 19, 3);
    private static final Color BLACK = new Color(6, 13, 38);


    public BoardButton(int x, int y) {
        super(DEFAULT_ICON);
        posX = x;
        posY = y;
        setFocusable(false);
        setBackground(DEFAULT_ICON_COLOUR);
        setOpaque(false);
        setBorder(null);
    }

//    public void selectButton() {
//        Color currentC = this.getBackground();
//        setBackground(new Color((float) (currentC.getRed() / 255.0), (float) (currentC.getGreen() / 255.0),
//                (float) (currentC.getBlue() / 255.0), .5f));
//        setOpaque(false);
//    }
//
//    public void unselectButton() {
//        Color currentC = this.getBackground();
//        setBackground(new Color((float) (currentC.getRed() / 255.0), (float) (currentC.getGreen() / 255.0),
//                (float) (currentC.getBlue() / 255.0), 0));
//        setOpaque(true);
//    }

    public void displayPiece(Piece p) {
        StringBuilder fileName = new StringBuilder(".\\data\\");
        switch (p.getPieceClass()) {
            case CANNON:
                fileName.append("cannon");
                break;
            case HORSE:
                fileName.append("horse");
                break;
            case ADVISOR:
                fileName.append("advisor");
                break;
            case CHARIOT:
                fileName.append("chariot");
                break;
            default:
                displayPiece2(fileName, p);
        }
        if (p.isRed()) {
            fileName.append("red.png");
        } else {
            fileName.append("blk.png");
        }
        setIcon(new ImageIcon(fileName.toString()));
    }

    private void displayPiece2(StringBuilder fileName, Piece p) {
        switch (p.getPieceClass()) {
            case GENERAL:
                fileName.append("general");
                break;
            case SOLDIER:
                fileName.append("soldier");
                break;
            case ELEPHANT:
                fileName.append("elephant");
                break;
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

package ui.gui;

import model.pieces.Piece;

/**
 * Represents a simple finder for the location of the image of the given piece
 */
public class PieceImageLocFinder {
    // EFFECTS: returns the filename of the image that represents the given piece
    public static String findImageForPiece(Piece p) {
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
                fineImageForPieceP2(fileName, p);
        }
        if (p.isRed()) {
            fileName.append("red.png");
        } else {
            fileName.append("blk.png");
        }
        return fileName.toString();
    }

    // EFFECTS: returns the filename of the image that represents the given piece (continued)
    private static void fineImageForPieceP2(StringBuilder fileName, Piece p) {
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
            default:
                throw new RuntimeException("Illegal piece given??");
        }
    }
}

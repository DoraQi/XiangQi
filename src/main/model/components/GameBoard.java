package model.components;

import model.pieces.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a game of XiangQi with the board and players on Black and Red sides
 * Coordinate (x, y) start with (0,0) on the bottom right, increasing to the right and up
 * The River is between row 4 and 5, certain pieces may not cross the river
 */
public class GameBoard {
    Player red;
    Player black;
    String gameLog;

    HashMap<String, Piece> board; // Change to Map

    private static final int MAX_X_COORD = 8;
    private static final int MIN_X_COORD = 0;
    private static final int MAX_Y_COORD = 9;
    private static final int MIN_Y_COORD = 0;

    private static final String rules =
            "Rules: https://ancientchess.com/page/play-xiangqi.htm \n\n"
                    + "input formats:\n"
                    + "  - moves: <from x><from y><space><to x><to y>\n"
                    + "           - ex. 01 11\n"
                    + "  -  add a piece in custom game: <class of the piece><space>[<x>,<y>]<R/B>\n"
                    + "           - ex. Soldier [1,0]R, Cannon [7,4]B";

    // EFFECTS: instantiate a GameBoard
    public GameBoard() {
        red = new Player(true);
        black = new Player(false);
        board = new HashMap<>();
        gameLog = "";
    }

    // REQUIRES: a game has been set up
    // EFFECTS: return true if only 1 general's left on the board
    public boolean checkWin() {
        int generalCount = 0;
        for (Piece p : board.values()) {
            if (p.getPieceClass().equals("General")) {
                if (++generalCount == 2) {
                    return false;
                }
            }
        }
        return true;
    }


    // REQUIRES: pos is a valid position within range ([MIN_X_COORD, MAX_X_COORD], [MIN_Y_COORD, MAX_Y_COORD])
    // EFFECTS: checks if the given position (x, y) is occupied by any piece
    public boolean isEmptyAt(int x, int y) {
        return !board.containsKey(toStrLoc(x, y));
    }

    // REQUIRES: input string is in all lower case
    // MODIFIES: this
    // EFFECTS: add a piece according to the given instruction; if unsuccessful prints the reason for failure
    public void putPiece(String inpt) {
        String[] inptSplit = inpt.split(" \\[|,|\\]");
        String pieceClass = inptSplit[0];
        int x = Integer.parseInt(inptSplit[1]);
        int y = Integer.parseInt(inptSplit[2]);
        boolean isRed = inptSplit[3].equals("r");
        if (x > MAX_X_COORD || x < MIN_X_COORD || y > MAX_Y_COORD || y < MIN_Y_COORD) {
            throw new IllegalArgumentException();
        }
        Piece p = makeNew(pieceClass, x, y, isRed);
        if (isRed) {
            red.addPiece(p);
        } else {
            black.addPiece(p);
        }
        System.out.println("Added " + p);
    }


    // EFFECTS: returns a string representation of the status of this board that includes
    //          positions of all pieces on board
    public String toString() {
        String str = "";
        for (Piece p : red.getPieces()) {
            str += p.toString() + " ";
        }
        str += "\n";
        for (Piece p : black.getPieces()) {
            str += p.toString() + " ";
        }
        return str.trim();
    }

    // REQUIRES: position of p is currently empty on this board
    // MODIFIES: this
    // EFFECT: place given piece onto the correct position on board
    public void placePiece(Piece p) {
        board.put(toStrLoc(p.getPosX(), p.getPosY()), p);
    }

    // REQUIRES: given coordinate (x, y) is a valid position on board,
    //           given position is occupied by a Piece
    // EFFECTS: return the piece on the given position
    public Piece getPAt(int x, int y) {
        return board.get(toStrLoc(x, y));
    }

    // EFFECTS: returns the rules
    public static String getRules() {
        return rules;
    }

    // MODIFIES: this
    // EFFECTS: sets up the board and players to play the game
    public void setUpClassicGame() {
        makeSoldiers();
        makeChariots();
        makeCannons();
        makeElephants();
        makeHorses();
        makeGenerals();
        makeAdvisors();
    }

    // MODIFIES: this
    // EFFECTS: move the piece at given location to the specified location and return true if successful
    public void redMove(String inpt) {
        playerMove(inpt, red, black);
    }

    // MODIFIES: this
    // EFFECTS: move the piece at given location to the specified location and return true if successful
    public void blackMove(String inpt) {
        playerMove(inpt, black, red);
    }

    // REQUIRES: moving is the player moving, other is the other player
    // MODIFIES: this
    // EFFECTS: move the piece at given location to the specified location and return true if successful
    private void playerMove(String move, Player moving, Player other) {
        int fromX = Integer.parseInt(move.substring(0, 1));
        int fromY = Integer.parseInt(move.substring(1, 2));
        int toX = Integer.parseInt(move.substring(3,4));
        int toY = Integer.parseInt(move.substring(4,5));
        if (isEmptyAt(fromX, fromY)) {
            throw new NullPointerException();
        }
        Piece p = getPAt(fromX, fromY);
        if (p.isRed() == moving.isRed()) {
            if (isEmptyAt(toX, toY)) {
                if (p.canMoveTo(toX, toY)) {
                    movePiece(p, toX, toY);
                    return;
                }
            } else {
                Piece prey = getPAt(toX, toY);
                if (prey.isRed() == other.isRed() && p.canCapture(toX, toY)) {
                    capture(p, prey, moving, other);
                    return;
                }
            }
        }
        throw new IllegalArgumentException();
    }

    // REQUIRES: hunter is a piece owned by playing and prey is a piece owned by other
    // MODIFIES: playing, other
    // EFFECTS: playing captures p from other, removing p from the board and moving hunter to prey's position
    private void capture(Piece hunter, Piece prey, Player playing, Player other) {
        removePiece(prey.getPosX(), prey.getPosY());
        playing.capture(prey);
        other.removePiece(prey);
        hunter.move(prey.getPosX(), prey.getPosY());
    }

    // REQUIRES: given piece can be moved to given coordinate (x, y)
    // MODIFIES: this, p
    // EFFECTS: move p to location (x, y)
    private void movePiece(Piece p, int x, int y) {
        // remove p from board
        removePiece(p.getPosX(), p.getPosY());
        // update position in p
        p.move(x, y);
        // update position on board
        placePiece(p);
    }

    // REQUIRES: given x, y position is empty on this board
    // MODIFIES: this
    // EFFECTS: place a piece of class pc and red if isRed, black if not, onto (x, y) of this board
    private Piece makeNew(String pc, int x, int y, boolean isRed) {
        switch (pc) {
            case "soldier":
                return new Soldier(x, y, this, isRed);
            case "general":
                return new General(x, y, this, isRed);
            case "cannon":
                return new Cannon(x, y, this, isRed);
            case "chariot":
                return new Chariot(x, y, this, isRed);
            case "advisor":
                return new Advisor(x, y, this, isRed);
            case "horse":
                return new Horse(x, y, this, isRed);
            case "elephant":
                return new Elephant(x, y, this, isRed);
        }
        throw new IllegalArgumentException();
    }

    // MODIFIES: this
    // EFFECTS: makes all generals for both red and black sides and place on board
    private void makeGenerals() {
        General redG = new General(4, 0, this, true);
        General blackG = new General(4, 9, this, false);
        placePiece(redG);
        placePiece(blackG);
        red.addPiece(redG);
        black.addPiece(blackG);
    }

    // MODIFIES: this
    // EFFECTS: makes all advisors for both red and black sides and place on board
    private void makeAdvisors() {
        ArrayList<Piece> pieces = new ArrayList<>();
        pieces.add(new Advisor(3, 9, this, false));
        pieces.add(new Advisor(5, 9, this, false));
        pieces.add(new Advisor(3, 0, this, true));
        pieces.add(new Advisor(5, 0, this, true));
        for (Piece p : pieces) {
            this.placePiece(p);
            if (p.isRed()) {
                red.addPiece(p);
            } else {
                black.addPiece(p);
            }
        }
    }


    // MODIFIES: this
    // EFFECTS: makes all horses for both red and black sides and place on board
    private void makeHorses() {
        ArrayList<Horse> horses = new ArrayList<>();
        horses.add(new Horse(1, 9, this, false));
        horses.add(new Horse(7, 9, this, false));
        horses.add(new Horse(1, 0, this, true));
        horses.add(new Horse(7, 0, this, true));
        for (Piece h : horses) {
            this.placePiece(h);
            if (h.isRed()) {
                red.addPiece(h);
            } else {
                black.addPiece(h);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: makes all elephants for both red and black sides and place on board
    private void makeElephants() {
        ArrayList<Elephant> elephants = new ArrayList<>();
        elephants.add(new Elephant(2, 9, this, false));
        elephants.add(new Elephant(6, 9, this, false));
        elephants.add(new Elephant(2, 0, this, true));
        elephants.add(new Elephant(6, 0, this, true));
        for (Piece p : elephants) {
            this.placePiece(p);
            if (p.isRed()) {
                red.addPiece(p);
            } else {
                black.addPiece(p);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: makes all cannons for both red and black sides and place on board
    private void makeCannons() {
        ArrayList<Piece> pieces = new ArrayList<>();
        pieces.add(new Cannon(1, 7, this, false));
        pieces.add(new Cannon(7, 7, this, false));
        pieces.add(new Cannon(1, 2, this, true));
        pieces.add(new Cannon(7, 2, this, true));
        for (Piece p : pieces) {
            this.placePiece(p);
            if (p.isRed()) {
                red.addPiece(p);
            } else {
                black.addPiece(p);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: makes all soldiers for both red and black sides and place on board
    private void makeChariots() {
        ArrayList<Piece> pieces = new ArrayList<>();
        pieces.add(new Chariot(0, 9, this, false));
        pieces.add(new Chariot(8, 9, this, false));
        pieces.add(new Chariot(0, 0, this, true));
        pieces.add(new Chariot(0, 8, this, true));
        for (Piece p : pieces) {
            this.placePiece(p);
            if (p.isRed()) {
                red.addPiece(p);
            } else {
                black.addPiece(p);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: makes all soldiers for both red and black sides and place on board
    private void makeSoldiers() {
        ArrayList<Piece> pieces = new ArrayList<>();
        for (int i = 0; i <= 8; i += 2) {
            pieces.add(new Soldier(i, 3, this, true));
            pieces.add(new Soldier(i, 6, this, false));
        }
        for (Piece p : pieces) {
            this.placePiece(p);
            if (p.isRed()) {
                red.addPiece(p);
            } else {
                black.addPiece(p);
            }
        }
    }

    // REQUIRES: position (x, y) is a valid position on the board
    // MODIFIES: this
    // EFFECTS: vacant the given spot
    private void removePiece(int x, int y) {
        board.remove(toStrLoc(x, y));
    }

    // EFFECTS: produce a string version of x*10 + y
    private String toStrLoc(int x, int y) {
        return x + String.valueOf(y);
    }
}

package ui;

import model.components.GameBoard;

import java.util.Scanner;

/**
 * A game of XiangQi
 */
public class XiangQi {
    private final String separator = "======================================";
    private final Scanner console = new Scanner(System.in);
    private GameBoard game;

    // EFFECTS: Starts a game of XiangQi
    public XiangQi() {
        while (true) {
            System.out.println("--------- WELCOME TO XIANGQI ---------\n"
                    + "Check out:       1 - rules\n"
                    + "                 2 - Play classic game\n"
                    + "                 3 - Play custom game\n"
                    + "                 4 - Quit");
            int opt = get1to4();
            if (opt == 1) {
                System.out.println(GameBoard.RULES);
            } else if (opt == 2) {
                playClassicGame();
                return;
            } else if (opt == 3) {
                playCustomGame();
                return;
            } else {
                System.exit(0);
            }
            System.out.println(separator);
        }
    }

    // MODIFIES: this
    // EFFECTS: sets up and prompts to play a classic game
    private void playClassicGame() {
        game = new GameBoard();
        System.out.println("Welcome to a classic game of XiangQi!");
        game.setUpClassicGame();
        System.out.println(game);
        playGame();
    }

    // MODIFIES: this
    // EFFECTS: prompts to set up and play a custom game
    private void playCustomGame() {
        game = new GameBoard();
        setupCustomGame();
        playGame();
    }

    // MODIFIES: this
    // EFFECTS: repeatedly prompts and gets user inputs to set up a custom game
    private void setupCustomGame() {
        System.out.println("Add pieces by entering \"<piece class> [x,y]<R/B>\"\n"
                + "                       ex. Soldier [2,3]R\n"
                + "Enter \"check\" to check the current status of the board\n"
                + "Enter \"done\" when you're ready to play!");
        System.out.println(separator);
        while (true) {
            System.out.print("Add piece: ");
            String inpt = console.nextLine().trim().toLowerCase();
            if (inpt.equalsIgnoreCase("done")) {
                System.out.println(game);
                return;
            } else if (inpt.equalsIgnoreCase("check")) {
                System.out.println(game);
                continue;
            }
            try {
                System.out.println("Added " + game.createPiece(inpt));
            } catch (Exception e) {
                System.out.println("<invalid input>");
            }
        }
    }

    // REQUIRES: a game has been set up
    // MODIFIES: this
    // EFFECTS: repeatedly get user to make moves until <= 1 general is left on board, if any invalid input is
    //          given, ask the user to re-enter
    private void playGame() {
        boolean redMoving = true;
        while (true) {
            System.out.println(separator);
            try {
                if (game.checkWin()) {
                    endMessage();
                    return;
                }
                playerMove(redMoving);
                System.out.println(game);
                redMoving = !redMoving;
            } catch (Exception e) {
                System.out.println("<invalid move>");
            }
        }
    }

    // REQUIRES: a game has been set up
    // MODIFIES: this
    // EFFECTS: prompts to get user input for a move and make the move, throws Exception if the given input is invalid
    private void playerMove(boolean redMoving) throws IllegalArgumentException {
        String inpt = "";
        try {
            if (redMoving) {
                System.out.print("RED move: ");
                inpt = console.nextLine().trim().toLowerCase();
                game.redMove(inpt);
            } else {
                System.out.print("BLACK move: ");
                inpt = console.nextLine().trim().toLowerCase();
                game.blackMove(inpt);
            }
        } catch (NumberFormatException e) {
            if (inpt.equalsIgnoreCase("quit")) {
                System.out.println("Game over!");
                System.exit(0);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    // REQUIRES: game has ended ( <= 1 general left on board )
    // EFFECTS: prints out a message to end the game
    private void endMessage() {
        System.out.println("Game Over!");
    }

    // EFFECTS: repeatedly prompts for user input until they enter 1, 2, 3, or 4
    private int get1to4() {
        while (true) {
            String inpt = console.nextLine().trim();
            switch (inpt) {
                case "1":
                    return 1;
                case "2":
                    return 2;
                case "3":
                    return 3;
                case "4":
                    return 4;
            }
            System.out.println("<please enter a number 1 - 4>");
        }
    }
}

package ui;

import exception.QuitGameException;
import model.components.GameBoard;
import persistence.JsonWriter;
import persistence.JsonReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * A game of XiangQi
 */
public class XiangQi {
    private final String separator = "======================================";
    private final Scanner console = new Scanner(System.in);
    private GameBoard game;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private static final String RULES =
            "Rules: https://ancientchess.com/page/play-xiangqi.htm \n\n"
                    + "input formats:\n"
                    + "  - moves: <from x><from y><space><to x><to y>\n"
                    + "           - ex. 01 11\n"
                    + "  -  add a piece in custom game: <class of the piece><space>[<x>,<y>]<R/B>\n"
                    + "           - ex. Soldier [1,0]R, Cannon [7,4]B\n"
                    + "You can exit the program anytime by entering \"quit\", "
                    + "follow the instructions to save the game.";

    // EFFECTS: Starts a game of XiangQi
    public XiangQi() throws FileNotFoundException {
        jsonWriter = new JsonWriter();
        jsonReader = new JsonReader();
        try {
            System.out.println("--------- WELCOME TO XIANGQI ---------\n"
                    + "Check out:       1 - rules\n"
                    + "                 2 - Play classic game\n"
                    + "                 3 - Play custom game\n"
                    + "                 4 - Load saved game\n"
                    + "                 5 - quit");
            play();
        } catch (QuitGameException quitGameException) {
            handleQuit(quitGameException.redGoesNext());
        } catch (IOException ioException) {
            System.out.println("Failed");
        }
    }

    // EFFECTS: play classic game, custom game, or load game depending on user input
    private void play() throws QuitGameException, IOException {
        int opt = get1to4();
        if (opt == 1) {
            System.out.println(RULES);
        } else if (opt == 2) {
            playClassicGame();
        } else if (opt == 3) {
            playCustomGame();
        } else if (opt == 4) {
            playSavedGame();
        }
    }

    // EFFECTS: reads saved file for a gameboard
    private void playSavedGame() throws IOException, QuitGameException {
        game = jsonReader.loadGame();
        boolean redStart = jsonReader.getFirstStart();
        System.out.println(game);
        playGame(redStart);
    }

    // EFFECTS: save the game progress to file
    private void handleQuit(boolean redGoesNext) throws FileNotFoundException {
        System.out.println("Save? [Y/N]");
        boolean saveGame = console.nextLine().trim().toUpperCase().equals("Y");
        if (saveGame) {
            System.out.println("Game saved!");
            jsonWriter.saveGame(game, redGoesNext);
        } else {
            System.out.println("Are you sure you don't want to save? [Y/N]");
            saveGame = console.nextLine().trim().toUpperCase().equals("Y");
            if (saveGame) {
                System.out.println("Game saved!");
                jsonWriter.saveGame(game, redGoesNext);
            } else {
                System.out.println("Thanks for playing!");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: sets up and prompts to play a classic game
    private void playClassicGame() throws QuitGameException {
        game = new GameBoard();
        System.out.println("Welcome to a classic game of XiangQi!");
        game.setUpClassicGame();
        System.out.println(game);
        playGame(true);
    }

    // MODIFIES: this
    // EFFECTS: prompts to set up and play a custom game
    private void playCustomGame() throws QuitGameException {
        game = new GameBoard();
        setupCustomGame();
        playGame(true);
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
    private void playGame(boolean redMoving) throws QuitGameException {
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
            } catch (IllegalArgumentException e) {
                System.out.println("<invalid move>");
            }
        }
    }

    // REQUIRES: a game has been set up
    // MODIFIES: this
    // EFFECTS: prompts to get user input for a move and make the move, throws Exception if the given input is invalid
    private void playerMove(boolean redMoving) throws IllegalArgumentException, QuitGameException {
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
                throw new QuitGameException(redMoving);
            } else {
                throw e;
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

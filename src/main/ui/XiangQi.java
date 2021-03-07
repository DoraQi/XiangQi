package ui;

import exception.IllegalInputException;
import exception.IllegalNumGeneralException;
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
    //          if user chooses to load saved game yet no games were ever saved, throw FileNotFoundException
    public XiangQi() throws FileNotFoundException {
        jsonWriter = new JsonWriter();
        jsonReader = new JsonReader();
        try {
            System.out.println("--------- WELCOME TO XIANGQI ---------\n"
                    + "Check out:     1 - rules\n"
                    + "               2 - Play classic game\n"
                    + "               3 - Play custom game\n"
                    + "               4 - Load saved game\n"
                    + "               5 - quit");
            play();
        } catch (QuitGameException quitGameException) {
            handleQuit(quitGameException.redGoesNext());
        } catch (IOException ioException) {
            System.out.println("Failed");
        } catch (IllegalNumGeneralException ie) {
            System.out.println("You have more than one general on each side!?");
        }
    }

    // EFFECTS: prompts for user input, if input =
    //          1 - display rules
    //          2 - play classic game,
    //          3 - custom game,
    //          4 - load game
    //          throws QuitGameException if user chooses to quit
    //          throws IOException if thrown by playSavedGame()
    private void play() throws QuitGameException, IOException, IllegalNumGeneralException {
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
    //          throws QuitGameException if user chooses to quit
    //          throws IOException if thrown by playSavedGame()
    private void playSavedGame() throws IOException, QuitGameException {
        try {
            game = jsonReader.loadGame();
            boolean redStart = jsonReader.getFirstStart();
            System.out.println(game);
            playGame(redStart);
        } catch (IllegalInputException | IllegalNumGeneralException e) {
            System.out.println("Broken save file :(");
        }
    }

    // EFFECTS: save the game progress to file
    //          throws FileNotFoundException thrown by saveGame()
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
    //          throws QuitGameException if user chooses to quit
    private void playClassicGame() throws QuitGameException, IllegalNumGeneralException {
        game = new GameBoard();
        System.out.println("Welcome to a classic game of XiangQi!");
        try {
            game.setUpClassicGame();
        } catch (IllegalInputException e) {
            throw new RuntimeException("can never happen");
        }
        System.out.println(game);
        playGame(true);
    }

    // MODIFIES: this
    // EFFECTS: prompts to set up and play a custom game
    //          throws QuitGameException if user chooses to quit
    private void playCustomGame() throws QuitGameException, IllegalNumGeneralException {
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
            } catch (IllegalInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // REQUIRES: a game has been set up
    // MODIFIES: this
    // EFFECTS: repeatedly get user to make moves until <= 1 general is left on board, if any invalid input is
    //          given, ask the user to re-enter
    //          throws QuitGameException if user chooses to quit
    private void playGame(boolean redMoving) throws QuitGameException, IllegalNumGeneralException {
        while (true) {
            System.out.println(separator);
            try {
                if (game.checkWin()) {
                    System.out.println("Game Over!");
                    return;
                }
                playerMove(redMoving);
                System.out.println(game);
                redMoving = !redMoving;
            } catch (IllegalInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // REQUIRES: a game has been set up
    // MODIFIES: this
    // EFFECTS: prompts to get user input for a move and make the move, throws Exception if the given input is invalid
    //          throws QuitGameException if user chooses to quit
    //          throws IllegalInputException if given move is invalid in format or cannot be performed
    private void playerMove(boolean redMoving) throws IllegalInputException, QuitGameException {
        try {
            if (redMoving) {
                System.out.print("RED move: ");
            } else {
                System.out.print("BLACK move: ");
            }
            String inpt = console.nextLine().trim().toLowerCase();
            game.playerMove(inpt, redMoving);
        } catch (RuntimeException e) {
            throw new IllegalInputException();
        }
    }

    // EFFECTS: repeatedly prompts for user input until they enter 1, 2, 3, or 4
    private int get1to4() throws QuitGameException {
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
                case "5":
                    throw new QuitGameException(true);
            }
            System.out.println("<please enter a number 1 - 4>");
        }
    }
}

package ui;

import javafx.util.Pair;
import model.components.GameBoard;
import model.components.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    private static String SEPARATOR = "==============================";
    private static Scanner console = new Scanner(System.in);
    private static Player red;
    private static Player black;
    private static GameBoard game;

    private static ArrayList<String> VALID_X_INPUTS = new ArrayList<>(); // = {'0', '1', '2', '3', '4', '5', '6', '7', '8'};
    private static ArrayList<String> VALID_Y_INPUTS = new ArrayList<>(); // = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static void main(String[] args) {
        setup();
        int opt = greet();
        if (opt == 1) {
            return;
        } else if (opt == 2) {
            playClassicGame();
        } else if (opt == 3) {
            playCustomGame();
        } else {
            return;
        }
    }

    private static void setup() {
        Collections.addAll(VALID_X_INPUTS, "0", "1", "2", "3", "4", "5", "6", "7", "8");
        Collections.addAll(VALID_Y_INPUTS, "0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
    }

    private static void playCustomGame() {
        game = new GameBoard();
        setupCustomGame(game);
        playGame();
    }


    private static void playClassicGame() {
        game = new GameBoard();
        game.setUpClassicGame();
        classicGameStartMessage();
        playGame();
    }

    private static void classicGameStartMessage() {
        System.out.println(SEPARATOR);
    }

    private static void setupCustomGame(GameBoard gb) {
    }

    private static void playGame() {
        boolean redMoving = true;
        while (true) {
            boolean moveSuccessful;
            if (redMoving) {
                System.out.print("RED move: ");
                moveSuccessful = game.redMove(getMove());
            } else {
                game.printBoard();
                System.out.print("BLACK move: ");
                moveSuccessful = game.blackMove(getMove());
            }
            if (!moveSuccessful) {
                System.out.println("<invalid move>");
                continue;
            }
            if (game.checkWin()) {
                winMessage();
                return;
            }
            game.printBoard();
            redMoving = !redMoving;
        }
    }

    private static int[] getMove() {
        while (true) {
            String inpt = console.nextLine().trim();
            if (inpt.equalsIgnoreCase("surrender")) {
                endGameMessage();
                System.exit(0);
            }
            if (inpt.length() != 5) {
                System.out.println("<invalid input>");
                continue;
            }
            String fromX = String.valueOf(inpt.charAt(0));
            String fromY = String.valueOf(inpt.charAt(1));
            String toX = String.valueOf(inpt.charAt(3));
            String toY = String.valueOf(inpt.charAt(4));
            if (VALID_X_INPUTS.contains(fromX) && VALID_Y_INPUTS.contains(fromY)
                    && VALID_X_INPUTS.contains(toX) && VALID_Y_INPUTS.contains(toY)) {
                return new int[]{Integer.parseInt(fromX), Integer.parseInt(fromY),
                        Integer.parseInt(toX), Integer.parseInt(toY)};
            }
            System.out.println("<invalid input>");
        }
    }

    private static void winMessage() {
    }

    private static void endGameMessage() {
    }

    private static int greet() {
        System.out.println("--------- WELCOME TO XIANGQI ---------\n"
                + "Check out:       1 - rules\n"
                + "                 2 - Play classic game\n"
                + "                 3 - Play custom game\n"
                + "                 4 - Quit");
        return get1to4();
    }

    private static int get1to4() {
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

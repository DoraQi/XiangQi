package ui;

import model.components.GameBoard;
import model.components.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    private static String SEPARATOR = "==============================";
    private static Scanner console = new Scanner(System.in);
    private static GameBoard game;

    public static void main(String[] args) {
        int opt = greet();
        if (opt == 1) {
            System.out.println(GameBoard.RULES);
        } else if (opt == 2) {
            playClassicGame();
        } else if (opt == 3) {
            playCustomGame();
        }
    }

    protected static void playCustomGame() {
        game = new GameBoard();
        setupCustomGame(game);
        playGame();
    }

    protected static void playClassicGame() {
        game = new GameBoard();
        System.out.println("Welcome to a classic game of XiangQi!");
        game.setUpClassicGame();
        System.out.println(game);
        playGame();
    }

    private static void setupCustomGame(GameBoard gb) {
        System.out.println(SEPARATOR);
        while (true) {
            System.out.println("Add piece: ");
            String inpt = console.nextLine().trim().toLowerCase();
            if (inpt.equalsIgnoreCase("done")) {
                System.out.println(game);
                return;
            }
            try {
                game.putPiece(inpt);
            } catch (Exception e) {
                System.out.println("<invalid input>");
            }
        }
    }

    private static void playGame() {
        boolean redMoving = true;
        while (true) {
            System.out.println(SEPARATOR);
            try {
                if (redMoving) {
                    System.out.print("RED move: ");
                    game.redMove(console.nextLine().trim().toLowerCase());
                } else {
                    System.out.print("BLACK move: ");
                    game.blackMove(console.nextLine().trim().toLowerCase());
                }
                System.out.println(game);
                if (game.checkWin()) {
                    winMessage();
                    return;
                }
                redMoving = !redMoving;
            } catch (Exception e) {
                System.out.println("<invalid move>");
            }
        }
    }

    private static void winMessage() {
        System.out.println("Congratulations! You win!");
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

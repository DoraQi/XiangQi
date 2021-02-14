package ui;

import model.components.GameBoard;

import java.util.Scanner;

/**
 * A game of XiangQi
 */
public class XiangQi {
    private String SEPARATOR = "======================================";
    private Scanner console = new Scanner(System.in);
    private GameBoard game;

    public XiangQi() {
        while (true) {
            int opt = greet();
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
            System.out.println(SEPARATOR);
        }
    }

    protected void playCustomGame() {
        game = new GameBoard();
        setupCustomGame();
        playGame();
    }

    protected void playClassicGame() {
        game = new GameBoard();
        System.out.println("Welcome to a classic game of XiangQi!");
        game.setUpClassicGame();
        System.out.println(game);
        playGame();
    }

    private void setupCustomGame() {
        System.out.println("Add pieces by entering \"<piece class> [x,y]<R/B>\"\n"
                + "                       ex. Soldier [2,3]R\n"
                + "Enter \"check\" to check the current status of the board\n"
                + "Enter \"done\" when you're ready to play!");
        System.out.println(SEPARATOR);
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

    private void playGame() {
        boolean redMoving = true;
        while (true) {
            System.out.println(SEPARATOR);
            try {
                playerMove(redMoving);
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

    private void winMessage() {
        System.out.println("Congratulations! You win!");
    }

    private int greet() {
        System.out.println("--------- WELCOME TO XIANGQI ---------\n"
                + "Check out:       1 - rules\n"
                + "                 2 - Play classic game\n"
                + "                 3 - Play custom game\n"
                + "                 4 - Quit");
        return get1to4();
    }

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

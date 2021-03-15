package ui.gui;

import exception.IllegalInputException;
import exception.QuitGameException;
import model.components.GameBoard;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GraphicalXiangQi extends JFrame implements ActionListener {
    private boolean redMoving;
    private GameBoard gameBoard;
    private GameFrame gameFrame;
    private BoardButton previousSelectedButton;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public GraphicalXiangQi() {
        previousSelectedButton = null;
        gameBoard = new GameBoard();
        jsonWriter = new JsonWriter();
        jsonReader = new JsonReader();
        gameFrame = new GameFrame(gameBoard, this);
        try {
            gameBoard.setUpClassicGame();
            redMoving = true;
        } catch (IllegalInputException e) {
            throw new RuntimeException("can never happen");
        }
        gameFrame.updateAll();
        gameFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof BoardButton) {
            try {
                handleGameButtonResponse((BoardButton) e.getSource());
                gameFrame.updateAll();
            } catch (IllegalInputException | QuitGameException ignored) {
                System.out.println("Don't rlly care");
            }
        } else if (source instanceof CustomMenuItem) {
            CustomMenuItem item = (CustomMenuItem) source;
            try {
                handleMenuItemResponse(item);
            } catch (IOException | IllegalInputException fileNotFoundException) {
                throw new RuntimeException("Error loading / saving game");
            }
        } else if (source instanceof GameButton) {
            try {
                handleFuntionalButtonResponse((GameButton) source);
            } catch (IllegalInputException illegalInputException) {
                System.out.println("Don't rlly care");
            }
        }
    }

    private void handleFuntionalButtonResponse(GameButton button) throws IllegalInputException {
        if (button.getText().equalsIgnoreCase("unselect")) {
            unselect();
        } else {
            String entry = gameFrame.getTextEntry();
            if (entry.equals("clear")) {
                gameBoard.clearBoard();
                gameFrame.updateAll();
            }
            gameBoard.createPiece(entry);
            gameFrame.updateAll();
        }
    }

    private void handleGameButtonResponse(BoardButton button) throws IllegalInputException, QuitGameException {
        int x = button.getPosX();
        int y = button.getPosY();
        if (previousSelectedButton == null) {
            if (gameBoard.isEmptyAt(x, y) || !redMoving == gameBoard.getPAt(x, y).isRed()) {
                throw new IllegalInputException();
            } else {
                previousSelectedButton = button;
            }
        } else {
            gameBoard.playerMove(String.valueOf(previousSelectedButton.getPosX())
                    + previousSelectedButton.getPosY() + " " + button.getPosX() + button.getPosY(), redMoving);
            redMoving = !redMoving;
            unselect();
        }
    }

    private void unselect() {
        previousSelectedButton = null;
    }

    private void handleMenuItemResponse(CustomMenuItem item) throws IOException, IllegalInputException {
        if (item.getText().equalsIgnoreCase("save")) {
            jsonWriter.saveGame(gameBoard, redMoving);
        } else if (item.getText().equalsIgnoreCase("load")) {
            System.out.println("loading game");
            gameBoard = jsonReader.loadGame();
            redMoving = jsonReader.getFirstStart();
            gameFrame.dispose();
            gameFrame = new GameFrame(gameBoard, this);
            gameFrame.updateAll();
        }
    }
}

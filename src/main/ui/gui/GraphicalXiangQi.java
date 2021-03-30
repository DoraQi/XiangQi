package ui.gui;

import exception.IllegalInputException;
import exception.IllegalNumGeneralException;
import exception.QuitGameException;
import model.components.GameBoard;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static model.components.PieceFactory.createPiece;
import static model.components.PieceFactory.setUpClassicGame;

/**
 * Represents the GUI for a XiangQi game
 */
public class GraphicalXiangQi extends JFrame implements ActionListener {
    private boolean redMoving;
    private boolean gameOngoing;
    private GameBoard gameBoard;
    private GameFrame gameFrame;
    private GameButton previousSelectedButton;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: instantiates a new GraphicalXiangQi
    public GraphicalXiangQi() {
        previousSelectedButton = null;
        setupComponents();
        setUpClassicGame(gameBoard);
        redMoving = true;
        gameOngoing = true;
        gameFrame.updateAll();
        gameFrame.setVisible(true);
        pack();
    }

    // MODIFIES: this
    // EFFECTS: setup all components of the GUI
    private void setupComponents() {
        gameBoard = new GameBoard();
        jsonWriter = new JsonWriter();
        jsonReader = new JsonReader();
        gameFrame = new GameFrame(gameBoard, this);
    }

    // MODIFIES: this
    // EFFECTS: handles any action performed by the user if game is still ongoing, ignores it if not
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOngoing) {
            return;
        }
        Object source = e.getSource();
        findHandlerForAction(source);
    }

    // MODIFIES: this
    // EFFECTS: handles any action performed by the user
    private void findHandlerForAction(Object source) {
        if (source instanceof GameButton) {
            try {
                handleGameButtonResponse((GameButton) source);
                gameFrame.updateAll();
            } catch (IllegalInputException | IllegalNumGeneralException ignored) {
                System.out.println("ignored");
            }
        } else if (source instanceof CustomMenuItem) {
            CustomMenuItem item = (CustomMenuItem) source;
            try {
                handleMenuItemResponse(item);
            } catch (IOException | IllegalInputException fileNotFoundException) {
                throw new RuntimeException("Error loading / saving game");
            }
        } else if (source instanceof UtilityButton) {
            try {
                handleUtilityButtonResponse((UtilityButton) source);
            } catch (IllegalInputException illegalInputException) {
                System.out.println("Don't rlly care");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: handles any actions from a UtilityButton
    private void handleUtilityButtonResponse(UtilityButton button) throws IllegalInputException {
        if (button.getText().equalsIgnoreCase("unselect")) {
            unselect();
        } else {
            String entry = gameFrame.getTextEntry();
            if (entry.equals("clear")) {
                gameBoard.clearBoard();
                gameFrame.updateAll();
            }
            createPiece(entry, gameBoard);
            gameFrame.updateAll();
        }
    }

    // MODIFIES: this
    // EFFECTS: handles any actions from a GameButton
    private void handleGameButtonResponse(GameButton button) throws IllegalInputException,
            IllegalNumGeneralException {
        int x = button.getPosX();
        int y = button.getPosY();
        if (previousSelectedButton == null) {
            if (gameBoard.isEmptyAt(x, y) || !redMoving == gameBoard.getPAt(x, y).isRed()) {
                throw new IllegalInputException();
            } else {
                previousSelectedButton = button;
            }
        } else {
            gameBoard.makeMove(previousSelectedButton.getPosX(), button.getPosX(), previousSelectedButton.getPosY(),
                    button.getPosY(), redMoving);
            redMoving = !redMoving;
            unselect();
            gameOngoing = !gameBoard.checkWin();
        }
    }

    // MODIFIES: this
    // EFFECTS: handles any actions from a MenuItem
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

    // MODIFIES: this
    // EFFECTS: clear any selected buttons
    private void unselect() {
        previousSelectedButton = null;
    }

}

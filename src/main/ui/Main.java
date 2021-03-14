package ui;

import exception.IllegalInputException;
import exception.OutOfBoundPositionException;
import model.components.GameBoard;
import model.pieces.*;
import ui.gui.GameFrame;
import ui.gui.GraphicalXiangQi;
import ui.gui.OnOffGameButton;
import ui.gui.XiangQiPanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        new GraphicalXiangQi();
    }
}

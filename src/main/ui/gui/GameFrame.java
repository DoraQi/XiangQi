package ui.gui;

import exception.IllegalInputException;
import model.components.GameBoard;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.PlayerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameFrame extends JFrame {
    private ActionListener actionListener;
    private boolean redPlaying;
    private GameBoard gb;
    private XiangQiPanel panel;
    private JMenuBar menu;
    private JMenuItem loadButton;
    private JMenuItem saveButton;

    public static final String DEFAULT_FONT = "Ariel";

    public GameFrame(GameBoard b, GraphicalXiangQi gameRunner) {
        actionListener = gameRunner;
        gb = b;
        setUpFrame();
        setUpMenu();
        setUpPlayerPanels();
        pack();
    }

    public void updateAll() {
        panel.updateDisplay();
    }

    public ActionListener getActionListener() {
        return actionListener;
    }

    private void setUpPlayerPanels() {
        this.add(new PlayerPanel(gb.getRed(), this), BorderLayout.SOUTH);
        this.add(new PlayerPanel(gb.getBlack(), this), BorderLayout.NORTH);
    }

    private void setUpMenu() {
        menu = new JMenuBar();
        JMenu fileItem = new JMenu("File");
        saveButton = new CustomMenuItem("Save");
        loadButton = new CustomMenuItem("Load");
        fileItem.add(saveButton);
        fileItem.add(loadButton);
        saveButton.addActionListener(actionListener);
        loadButton.addActionListener(actionListener);
        fileItem.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 20));

        JMenu helpItem = new JMenu("Help");
        helpItem.add(new CustomMenuItem("Rules"));
        helpItem.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 20));

        menu.add(fileItem);
        menu.add(helpItem);
        this.setJMenuBar(menu);
    }

    private void setUpFrame() {
        this.setPreferredSize(new Dimension(1000, 1200));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setIconImage(new ImageIcon(".\\data\\katana-sword-icon.png").getImage());
        // image source: <a target="_blank" href="https://icons8.com/icons/set/katana-sword">Katana Sword icon</a> icon
        // by <a target="_blank" href="https://icons8.com">Icons8</a>
        this.panel = new XiangQiPanel(this, gb);
        this.add(panel, BorderLayout.CENTER);
        setVisible(true);
    }
}

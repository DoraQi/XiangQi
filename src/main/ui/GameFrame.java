package ui;

import exception.IllegalInputException;
import model.components.GameBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener {
    private GameBoard gb;
    private XiangQiPanel panel;
    private JMenuBar menu;
//
//    private static final int WIDTH = 1000;
//    private static final int HEIGHT = 1200;
    public static final String DEFAULT_FONT = "Ariel";

    public GameFrame() {
        gb = new GameBoard();
        try {
            gb.setUpClassicGame();
        } catch (IllegalInputException e) {
            throw new RuntimeException();
        }

        setUpFrame();
        setUpMenu();
        setUpPlayerPanels();
        pack();
    }

    private void setUpPlayerPanels() {
        this.add(new PlayerPanel(gb.getRed()), BorderLayout.NORTH);
        this.add(new PlayerPanel(gb.getBlack()), BorderLayout.SOUTH);
    }

    private void setUpMenu() {
        menu = new JMenuBar();
        JMenu fileItem = new JMenu("File");
        fileItem.add(new CustomMenuItem("Save"));
        fileItem.add(new CustomMenuItem("Load"));
        fileItem.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 20));

        JMenu helpItem = new JMenu("Help");
        helpItem.add(new CustomMenuItem("Rules"));
        helpItem.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 20));

        menu.add(fileItem);
        menu.add(helpItem);
        this.setJMenuBar(menu);
    }

    private void setUpFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setIconImage(new ImageIcon(".\\data\\katana-sword-icon.png").getImage());
        // image source: <a target="_blank" href="https://icons8.com/icons/set/katana-sword">Katana Sword icon</a> icon
        // by <a target="_blank" href="https://icons8.com">Icons8</a>
        panel = new XiangQiPanel();
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

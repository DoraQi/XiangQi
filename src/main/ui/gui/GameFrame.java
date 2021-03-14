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
    private GameBoard gb;
    private XiangQiPanel panel;
    private JMenuBar menu;
    private JMenuItem loadButton;
    private JMenuItem saveButton;
    private PlayerPanel redP;
    private PlayerPanel blackP;

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
        redP.update();
        blackP.update();
    }

    public ActionListener getActionListener() {
        return actionListener;
    }

    private void setUpPlayerPanels() {
        redP = new PlayerPanel(gb.getRed(), this);
        blackP = new PlayerPanel(gb.getBlack(), this);
        this.add(redP, BorderLayout.SOUTH);
        this.add(blackP, BorderLayout.NORTH);
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
        JLayeredPane layeredPane = new JLayeredPane();
        JPanel bgPanel = new JPanel();
        ImageIcon bgIcon = new ImageIcon(".\\data\\boardbg.png");
        JLabel bgLabel = new JLabel(bgIcon);
        bgPanel.add(bgLabel);
        bgPanel.setBounds(0, 0, bgIcon.getIconWidth(), bgIcon.getIconHeight());
        layeredPane.setBackground(Color.BLACK);
        layeredPane.add(bgPanel);
        layeredPane.setVisible(true);
        layeredPane.setOpaque(true);
        this.setPreferredSize(new Dimension(980, 1320));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setIconImage(new ImageIcon(".\\data\\katana-sword-icon.png").getImage());
        // image source: <a target="_blank" href="https://icons8.com/icons/set/katana-sword">Katana Sword icon</a> icon
        // by <a target="_blank" href="https://icons8.com">Icons8</a>
        this.panel = new XiangQiPanel(this, gb);
        layeredPane.add(panel, JLayeredPane.POPUP_LAYER);
        this.add(layeredPane, BorderLayout.CENTER);
        setVisible(true);
    }
}

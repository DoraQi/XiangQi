package ui.gui;

import model.components.GameBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Represents the frame of the game
 */
public class GameFrame extends JFrame {
    private ActionListener actionListener;
    private GameBoard gb;
    private XiangQiPanel panel;
    private JMenuBar menu;
    private JMenuItem loadButton;
    private JMenuItem saveButton;
    private PlayerPanel redP;
    private PlayerPanel blackP;
    private AddPiecePanel apPanel;

    public static final String DEFAULT_FONT = "Ariel";

    // EFFECTS: instantiates a GameFrame and setup all components
    public GameFrame(GameBoard b, GraphicalXiangQi gameRunner) {
        actionListener = gameRunner;
        gb = b;
        setupFrame();
        setupGameboardDisplay();
        setUpMenu();
        setUpPlayerPanels();
        setUpApPanel();
        pack();
    }

    // MODIFIES: this
    // EFFECTS: update all panels to display the current status of the game
    public void updateAll() {
        panel.updateDisplay();
        redP.update();
        blackP.update();
    }

    // getter
    public ActionListener getActionListener() {
        return actionListener;
    }

    // EFFECTS: return the user input in the text pane
    public String getTextEntry() {
        return apPanel.getInput();
    }
    
    // MODIFIES: this
    // EFFECTS: setup basic properties of the frame
    private void setupFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: sets up an AddPiecePanel
    private void setUpApPanel() {
        apPanel = new AddPiecePanel(actionListener);
        this.add(apPanel, BorderLayout.EAST);
    }

    // MODIFIES: this
    // EFFECTS: sets up two PlayerPanels for each player
    private void setUpPlayerPanels() {
        redP = new PlayerPanel(gb.getRed(), this);
        blackP = new PlayerPanel(gb.getBlack(), this);
        this.add(redP, BorderLayout.SOUTH);
        this.add(blackP, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: sets up the menu
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

    // MODIFIES: this
    // EFFECTS: sets up the game board display
    private void setupGameboardDisplay() {
        JLayeredPane layeredPane = new JLayeredPane();
        JPanel bgPanel = new JPanel();
        ImageIcon bgIcon = new ImageIcon(".\\data\\boardbg.png");
        JLabel bgLabel = new JLabel(bgIcon);
        bgPanel.add(bgLabel);
        bgPanel.setBounds(0, 0, bgIcon.getIconWidth(), bgIcon.getIconHeight());
        layeredPane.setLayout(null);
        layeredPane.setBackground(Color.WHITE);
        layeredPane.add(bgPanel);
        layeredPane.setVisible(true);
        layeredPane.setOpaque(true);
        layeredPane.setPreferredSize(new Dimension(bgIcon.getIconWidth(), bgIcon.getIconHeight()));
        this.panel = new XiangQiPanel(this, gb);
        layeredPane.add(panel, JLayeredPane.POPUP_LAYER);
        this.add(layeredPane, BorderLayout.CENTER);

    }
}

package ui.gui;

import model.components.GameBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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

    public GameFrame(GameBoard b, GraphicalXiangQi gameRunner) {
        actionListener = gameRunner;
        gb = b;
        setUpFrame();
        setUpMenu();
        setUpPlayerPanels();
        setUpApPanel();
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

    public String getTextEntry() {
        return apPanel.getInput();
    }

    private void setUpApPanel() {
        apPanel = new AddPiecePanel(actionListener);
        this.add(apPanel, BorderLayout.EAST);
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
        layeredPane.setLayout(null);
        layeredPane.setBackground(Color.WHITE);
        layeredPane.add(bgPanel);
        layeredPane.setVisible(true);
        layeredPane.setOpaque(true);
        layeredPane.setPreferredSize(new Dimension(bgIcon.getIconWidth(), bgIcon.getIconHeight()));
        this.setPreferredSize(new Dimension(bgIcon.getIconWidth() + 300, 1043 + 288));
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

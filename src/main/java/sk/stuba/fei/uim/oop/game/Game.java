package sk.stuba.fei.uim.oop.game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
//private JLabel winLabel;
//private int boardSizeInt;
//private final List<Player> players;
//private final JFrame mainWindow;
//private JPanel playBoard, mainPanel;
//private JTextArea scoreText;
//private final List<Tile> tiles;
//private BoardSizeSlider sizeSlider;
//private JComboBox colorChoice;
//private BoardImagesBuffer images;

public class Game {
    JFrame mainWindow;
    GamePanel gamePanel;

    public Game() {

        mainWindow = new JFrame();
        mainWindow.setSize(1000,1000);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setResizable(false);
        mainWindow.setLayout(new GridLayout());
        mainWindow.getContentPane().setBackground(Color.white);
        mainWindow.setResizable(false);

        gamePanel = new GamePanel();
        mainWindow.setLayout(new GridLayout(1,1));

        mainWindow.add(gamePanel);

        mainWindow.setVisible(true);

        gamePanel.moveLoop();

    }





//    boardSizeInt  = 6;
//    players = new ArrayList<>();
//    JFrame mainWindow = new JFrame();
//        players.add(new Player(Chips.BLACK,this));
//        players.add(new Bot(Chips.WHITE,this));
//    tiles = new ArrayList<>();
//
//    initialiseWhole();
//        mainWindow.setVisible(true);
//        players.get(0).startTurn();
}

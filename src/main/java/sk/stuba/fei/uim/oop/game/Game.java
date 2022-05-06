package sk.stuba.fei.uim.oop.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.lang.Object;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Game {
    JFrame mainWindow;
    GamePanel gamePanel;
    List timeList;

    public Game() {
       // timeList = new List();
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


}

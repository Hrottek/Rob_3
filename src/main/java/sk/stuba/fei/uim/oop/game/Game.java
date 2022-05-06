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
    List<Double> timeList;
    List<Double> VlList;
    List<Double> VrList;

    public Game() {
        timeList = new ArrayList();
        VlList = new ArrayList();
        VrList = new ArrayList();
        mainWindow = new JFrame();
        mainWindow.setSize(1000,1000);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setResizable(false);
        mainWindow.setLayout(new GridLayout());
        mainWindow.getContentPane().setBackground(Color.white);
        mainWindow.setResizable(false);
        mainWindow.setLayout(new BorderLayout());

        gamePanel = new GamePanel();
        mainWindow.add(gamePanel, BorderLayout.CENTER);

        JButton generateButton = new JButton("Generate CSV");
        generateButton.addActionListener(e -> {
            generateCSV();
            gamePanel.requestFocus();
        });
        mainWindow.add(generateButton, BorderLayout.PAGE_END);

        mainWindow.setVisible(true);

        gamePanel.moveLoop();
    }

    private void generateCSV(){
        try (PrintWriter writer = new PrintWriter("test.csv")) {

            StringBuilder sb = new StringBuilder();
            sb.append("id");
            sb.append(',');
            sb.append("Name");
            sb.append('\n');

            sb.append("1");
            sb.append(',');
            sb.append("Prashant Ghimire");
            sb.append('\n');

            writer.write(sb.toString());

            System.out.println("done!");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


}

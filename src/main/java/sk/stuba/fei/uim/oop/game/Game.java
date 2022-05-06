package sk.stuba.fei.uim.oop.game;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Game {
    JFrame mainWindow;
    GamePanel gamePanel;
    List<Double> timeList;
    List<Double> YtList;
    List<Double> XtList;
    List<Double> fiList;

    public Game() {
        timeList = new ArrayList();
        YtList = new ArrayList();
        XtList = new ArrayList();
        fiList = new ArrayList();
        mainWindow = new JFrame();
        mainWindow.setSize(1000,1000);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setResizable(false);
        mainWindow.setLayout(new GridLayout());
        mainWindow.getContentPane().setBackground(Color.white);
        mainWindow.setResizable(false);
        mainWindow.setLayout(new BorderLayout());

        gamePanel = new GamePanel(this);
        mainWindow.add(gamePanel, BorderLayout.CENTER);

        JButton generateButton = new JButton("Generate CSV");
        generateButton.addActionListener(e -> {

            try {
                generateCSV();
                System.out.println("skusam sa");
            } catch (Exception ex) {
                System.out.println("nepodarilo sa");
                generateCSV();
            }
            gamePanel.requestFocus();

        });
        mainWindow.add(generateButton, BorderLayout.PAGE_END);

        mainWindow.setVisible(true);

        gamePanel.moveLoop();
    }

    private void generateCSV(){
        try (PrintWriter writer = new PrintWriter("test.csv")) {

            StringBuilder sb = new StringBuilder();

            for (Double item : timeList) {
                sb.append(item);
                sb.append(',');
            }
            sb.append('\n');
            for (Double value : XtList) {
                sb.append(value * 0.01);
                //sb.append(value);
                sb.append(',');
            }
            sb.append('\n');
            for (Double aDouble : YtList) {
               sb.append((aDouble * - 1)*0.01);   //@TODO
                //sb.append(aDouble * - 1);
                sb.append(',');
            }
            sb.append('\n');
            for (Double aDouble : fiList) {
                sb.append(aDouble * - 1);
                sb.append(',');
            }

            writer.write(sb.toString());

            System.out.println("done!");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


}

package sk.stuba.fei.uim.oop.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class GamePanel extends JPanel implements KeyListener {
    BufferedImage img;
    int x, y;
    double rotation;
    double acceleration;
    double speed;
    private final Set<Integer> pressedKeys = new HashSet<>();


    AffineTransform identityTrans;

    AffineTransform af;

    public GamePanel() {
        x = 0;
        y = 0;
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocus();
        identityTrans = new AffineTransform();
        af = new AffineTransform();
        //ImageObserver observer;
        try {
            img = ImageIO.read((getClass().getResourceAsStream("/podvozok1.png")));
            img.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void moveLoop(){
        while(true){
            repaint();
        }
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
       rotate(g);
    }

    private void rotate(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        // The required drawing location
        int drawLocationX = 300;
        int drawLocationY = 300;

// Rotation information

        double rotationRequired = Math.toRadians (0);
        double locationX = img.getWidth();
        double locationY = img.getHeight();
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
      //  tx.scale(0.1,0.1);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

// Drawing the rotated image at the required drawing locations
        g2d.setBackground(Color.GREEN);
        g2d.drawImage(op.filter(img, null), x, y, null);
    }




    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W){
            y--;
        }
        if (e.getKeyCode() == KeyEvent.VK_S){
            y++;
        }
        if (e.getKeyCode() == KeyEvent.VK_A){
            x--;
        }
        if (e.getKeyCode() == KeyEvent.VK_D){
            x++;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

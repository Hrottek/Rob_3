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
import java.util.Iterator;
import java.util.Set;

public class GamePanel extends JPanel implements KeyListener {
    BufferedImage img;
    int drawx, drawy,Xl,Yl,Xt,Yt;
    double Vl,Vr;
    double fi;
    double rotation;
    double acceleration;
    double speed;
    private final Set<Integer> pressedKeys = new HashSet<>();


    AffineTransform identityTrans;

    AffineTransform af;

    public GamePanel() {
        drawx = 0;
        drawy = 0;

        fi = 0;
        Vl = 0;
        Vr = 0;
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

    private void calculateLocationAndRotation(){

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

        double rotationRequired = Math.toRadians (fi);
        double locationX = img.getWidth();
        double locationY = img.getHeight();
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
      //  tx.scale(0.1,0.1);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

// Drawing the rotated image at the required drawing locations
        g2d.setBackground(Color.GREEN);
        g2d.drawImage(op.filter(img, null), drawx, drawy, null);
    }




    @Override
    public synchronized void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
        Point offset = new Point();
        if (!pressedKeys.isEmpty()) {
            for (Iterator<Integer> it = pressedKeys.iterator(); it.hasNext();) {
                switch (it.next()) {
                    case KeyEvent.VK_W:
                    case KeyEvent.VK_UP:
                        drawy--;
                        break;
                    case KeyEvent.VK_A:
                    case KeyEvent.VK_LEFT:
                        drawx--;
                        break;
                    case KeyEvent.VK_S:
                    case KeyEvent.VK_DOWN:
                        drawy++;
                        break;
                    case KeyEvent.VK_D:
                    case KeyEvent.VK_RIGHT:
                        drawx++;
                        break;
                }
            }
        }
        System.out.println(offset); // Do something with the offset.
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) { /* Not used */ }
}


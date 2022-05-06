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
import java.time.Instant;

import static java.lang.Thread.sleep;

public class GamePanel extends JPanel implements KeyListener {
    BufferedImage img;
    double drawx, drawy,Xl,Yl,Xt,Yt;
    double Vl,Vr;
    double fi;
    double rotation;
    double acceleration;
    double speed;
    double L;
    private final Set<Integer> pressedKeys = new HashSet<>();


    AffineTransform identityTrans;

    AffineTransform af;

    public GamePanel() {

        L = 0.2;
        drawx = 500;
        drawy = 500;

        fi = 0;
        fi = Math.toRadians(fi);
        Vl = 0;
        Vr = 0;

        acceleration = 0.01;
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocus();
        identityTrans = new AffineTransform();
        af = new AffineTransform();
        //ImageObserver observer;
        try {
            img = ImageIO.read((getClass().getResourceAsStream("/podvozok1.png")));
            img.getScaledInstance(5,5,Image.SCALE_SMOOTH);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void calculateLocationAndRotation(){
        //
        if(Vl != 0 || Vr != 0){
            double wt = (Vr - Vl)/L;
            double vt = (Vr + Vl)/2;
            double dfi = wt * 0.0001;
            fi += dfi;
            double dx = vt * Math.cos(fi)*0.01;
            double dy = vt * Math.sin(fi)*0.01;

            if (drawx + dx >= 0 && drawx + dx <= 900) {
                drawx += dx;
            }
            if (drawy + dy >= 0 && drawy + dy <= 900) {
               drawy += dy;
            }

            System.out.println(dx);
            System.out.println(dy);
        }

    }

    public void moveLoop(){
        while(true){
            calculateLocationAndRotation();
//            if(Vl != 0 && Vr != 0){
//
//            }
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

// Rotation information

        double locationX = img.getWidth()/2;
        double locationY = img.getHeight()/2;
        AffineTransform tx = AffineTransform.getRotateInstance(fi + Math.PI/2, locationX, locationY);
      //  tx.scale(0.1,0.1);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);


// Drawing the rotated image at the required drawing locations
        g2d.setBackground(Color.GREEN);
        g2d.drawImage(op.filter(img, null), (int)drawx, (int)drawy, null);
    }

    private void deccelarate(int V) {
        while (V != 0) {
            if (V > 0) {
                V--;
            } else{
                V++;
            }
        }
    }




    @Override
    public synchronized void keyPressed(KeyEvent e) {
       // pressedKeys.remove(e.getKeyCode());
        pressedKeys.add(e.getKeyCode());
        if (!pressedKeys.isEmpty()) {
            for (Iterator<Integer> it = pressedKeys.iterator(); it.hasNext();) {
                switch (it.next()) {
                    case KeyEvent.VK_W:
                    case KeyEvent.VK_UP:
                        Vl+=acceleration;
                        Vr+=acceleration;
                        break;
                    case KeyEvent.VK_A:
                    case KeyEvent.VK_LEFT:
                        Vl+=acceleration;
                        //Vr-=acceleration;
                        break;
                    case KeyEvent.VK_S:
                    case KeyEvent.VK_DOWN:
                        Vl-=acceleration;
                        Vr-=acceleration;
                        break;
                    case KeyEvent.VK_D:
                    case KeyEvent.VK_RIGHT:
                        //Vl-=acceleration;
                        Vr+=acceleration;
                        break;

                    case KeyEvent.VK_SPACE:
                        Vl = 0;
                        Vr = 0;
                }
            }
        }
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
//        pressedKeys.add(e.getKeyCode());
//        if (!pressedKeys.isEmpty()) {
//            for (Iterator<Integer> it = pressedKeys.iterator(); it.hasNext();) {
//                switch (it.next()) {
//                    case KeyEvent.VK_W:
//                    case KeyEvent.VK_UP:
//                        Vl-=2*acceleration;
//                        Vr-=2*acceleration;
//                        break;
//                    case KeyEvent.VK_A:
//                    case KeyEvent.VK_LEFT:
//                        Vl-=2*acceleration;
//                        //Vr-=acceleration;
//                        break;
//                    case KeyEvent.VK_S:
//                    case KeyEvent.VK_DOWN:
//                        Vl+=2*acceleration;
//                        Vr+=2*acceleration;
//                        break;
//                    case KeyEvent.VK_D:
//                    case KeyEvent.VK_RIGHT:
//                        //Vl-=acceleration;
//                        Vr+=2*acceleration;
//                        break;
//
//                    case KeyEvent.VK_SPACE:
//                        Vl = 0;
//                        Vr = 0;
//                }
//            }
//        }
    }

    @Override
    public void keyTyped(KeyEvent e) { /* Not used */ }
}


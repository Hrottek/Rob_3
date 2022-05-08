package sk.stuba.fei.uim.oop.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class GamePanel extends JPanel implements KeyListener {

    BufferedImage img;
    double drawx, drawy,Xt,Yt;
    double Vl,Vr;
    double phi;
    double acceleration;
    double L;
    private final Set<Integer> pressedKeys = new HashSet<>();
    Game game;
    long start1;
    AffineTransform identityTrans;
    AffineTransform af;

    public GamePanel(Game game) {
        this.game = game;
        L = 0.2;
        drawx = 500;
        drawy = 500;
        phi = 0;
        phi = Math.toRadians(phi);
        Vl = 0;
        Vr = 0;

        acceleration = 0.001;
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

        start1 = System.currentTimeMillis();
        game.timeList.add(0.0);
        game.XtList.add(Xt);
        game.YtList.add(Yt);
        game.fiList.add(phi);

    }

    private void calculateLocationAndRotation(){
        if(Vl != 0 || Vr != 0){
            double wt = (Vr - Vl)/L;
            double vt = (Vr + Vl)/2;
            double dfi = wt * 0.0001;
            phi += dfi;
            double dx = vt * Math.cos(phi)*0.01;
            double dy = vt * Math.sin(phi)*0.01;

            if (drawx + dx >= 0 && drawx + dx <= 900) {
                drawx += dx;
            }
            if (drawy + dy >= 0 && drawy + dy <= 900) {
               drawy += dy;
            }

            Xt +=dx;
            Yt +=dy;
        }
    }

    public void moveLoop(){
        while(true){
            calculateLocationAndRotation();
            if(Vl != 0 || Vr != 0){
                repaint();
           }

            if((double)(System.currentTimeMillis() - start1)/1000 > 0.01 && !game.isWriting()){
                double end = (double)(System.currentTimeMillis() - start1)/1000;
                game.timeList.add(end + game.timeList.get(game.timeList.size() -1));
                game.XtList.add(Xt);
                game.YtList.add(Yt);
                game.fiList.add(phi);
                start1 = System.currentTimeMillis();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
       rotate(g);
    }

    private void rotate(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

// Rotation information

        double locationX = img.getWidth()/2;
        double locationY = img.getHeight()/2;
        AffineTransform tx = AffineTransform.getRotateInstance(phi + Math.PI/2, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

// Drawing the rotated image at the required drawing locations
        g2d.drawImage(op.filter(img, null), (int)drawx, (int)drawy, null);
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
        for (Iterator<Integer> it = pressedKeys.iterator(); it.hasNext();) {
            switch (it.next()) {
                case KeyEvent.VK_W:
                case KeyEvent.VK_UP:
                    Vl+=acceleration;
                    Vr+=acceleration; //TODO
                    break;
                case KeyEvent.VK_A:
                case KeyEvent.VK_LEFT:
                    Vl+=acceleration;
                    Vr-=acceleration;
                    break;
                case KeyEvent.VK_S:
                case KeyEvent.VK_DOWN:
                    Vl-=acceleration;
                    Vr-=acceleration;
                    break;
                case KeyEvent.VK_D:
                case KeyEvent.VK_RIGHT:
                    Vl-=acceleration;
                    Vr+=acceleration;
                    break;

                case KeyEvent.VK_SPACE:
                    Vl = 0;
                    Vr = 0;
                    break;
            }
        }
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());

    }

    @Override
    public void keyTyped(KeyEvent e) { /* Not used */ }
}


package pong_game;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class pong_game extends Applet implements Runnable, KeyListener {
    final int WIDTH = 700, HEIGHT = 500;
    Thread thread;
    HumanPaddle p1;
    Ball b1;
    AI_Paddle p2;
    boolean gameStart;

    Graphics gfx;
    Image img;


    public void init(){
        this.resize(WIDTH, HEIGHT);
        this.addKeyListener(this);

        setFocusable(true);
        requestFocusInWindow();
        gameStart = false;

        p1 = new HumanPaddle(1);
        b1 = new Ball();
        p2 = new AI_Paddle(2, b1);

        img = createImage(WIDTH, HEIGHT);
        gfx = img.getGraphics(); // off-screen buffer

        thread = new Thread(this);
        thread.start();
    }

    public void paint(Graphics graphics){
        gfx.setColor(Color.BLACK);
        gfx.fillRect(0, 0, WIDTH, HEIGHT);

        if(b1.getX() < -10 || b1.getX() > 710){
            gfx.setColor(Color.RED);
            gfx.drawString("GAME OVER !", 300, 230);
        }
        else {
            p1.draw(gfx);
            b1.draw(gfx);
            p2.draw(gfx);
        }

        if(!gameStart){
            gfx.setColor(Color.WHITE);
            gfx.drawString("PING_PONG", 310, 30);
            gfx.drawString("Press [ENTER] to start", 290, 80);
        }

        graphics.drawImage(img,0, 0, this);
    }

    public void update(Graphics graphics){
        paint(graphics);
    }


    public void run() {
        for(;;){
            if(gameStart) {
                p1.move();
                p2.move();
                b1.move();

                b1.checkCollision(p1, p2);
            }
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            p1.setUpAccel(true);
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            p1.setDownAccel(true);
        }
        else if(e.getKeyCode() == KeyEvent.VK_ENTER){
            gameStart = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            p1.setUpAccel(false);
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            p1.setDownAccel(false);
        }
    }
}


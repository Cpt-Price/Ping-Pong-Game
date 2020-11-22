package pong_game;

import java.awt.*;

public class AI_Paddle implements Paddle{
    double yPos, yVel;
    boolean upAccel, downAccel;
    int  x;
    Ball b1; // for tracking the ball

    public AI_Paddle(int player, Ball b){
        upAccel = false; downAccel = false;
        yPos = 210; yVel = 0;
        b1 = b;

        if(player == 1) // player 1 is on the left
            x = 20;
        else
            x = 660; // player 2 would be on the right
    }


    public void draw(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(x, (int)yPos, 20, 80); // draws the paddle 20*80
    }

    public void move() {
        yPos = b1.getY() - 40 ;

        // prevent the paddle from going outside the screen-space
        if(yPos < 0)
            yPos = 0;
        if(yPos > 420)  // height of screen minus height of paddle i.e. 500-80= 420
            yPos = 420;
    }

    public int getY() {
        return (int)yPos; // typed-casted to int
    }
}


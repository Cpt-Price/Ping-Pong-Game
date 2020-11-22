package pong_game;

import java.awt.*;

public class HumanPaddle implements Paddle{
    double y, yVel;
    boolean upAccel, downAccel;
    int x;
    final double FRIC = 0.95;  // slows down the paddle

    public HumanPaddle(int player){
        upAccel = false; downAccel = false;
        y = 210; yVel = 0;

        if(player == 1) // player 1 is on the left
            x = 20;
        else
            x = 660; // player 2 would be on the right
    }


    public void draw(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(x, (int)y, 20, 80); // draws the paddle 20*80
    }

    public void move() {
        if(upAccel){
            yVel -= 2;
        }
        else if(downAccel){
            yVel += 2;
        }
        else if(!upAccel && !downAccel){
            yVel *= FRIC; // slows down the paddle
        }

        // handle excessive speed
        if(yVel >= 6)
            yVel = 6;
        else if(yVel <= -6)
            yVel = -6;

        y += yVel; // change the speed

        // prevent the paddle from going outside the screen-space
        if(y < 0)
            y = 0;
        if(y > 420)  // height of screen minus height of paddle i.e. 500-80= 420
            y = 420;
    }

    public void setUpAccel(boolean input){ // mutators
        upAccel = input;
    }

    public void setDownAccel(boolean input){ // mutators
        downAccel = input;
    }

    public int getY() {
        return (int)y; // typed-casted to int
    }
}

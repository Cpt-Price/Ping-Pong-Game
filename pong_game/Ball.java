package pong_game;

import java.awt.*;

public class Ball {
    double xVel, yVel, x, y;

    public Ball(){
        x = 350;
        y = 220;
        xVel = getRandSpeed()*getRandDirection();
        yVel = getRandSpeed()*getRandDirection();
    }

    public double getRandSpeed(){
        return(Math.random()*3 + 2);
    }

    public int getRandDirection(){
        int rand = (int)(Math.random()*2);

        if(rand == 1)
            return 1;
        else
             return -1;

    }

    public void draw(Graphics graphics){
        graphics.setColor(Color.WHITE);
        graphics.fillOval((int)x-10, (int)y-10, 20, 20);
    }

    public void checkCollision(Paddle pOne, Paddle pTwo){
        if(x <= 50){
            if( y >= pOne.getY() && y <= pOne.getY() + 80)
                xVel = -xVel;
        }
        else if(x >= 650){
            if( y >= pTwo.getY() && y <= pTwo.getY() + 80)
                xVel = -xVel;
        }
    }

    public void move(){
        x += xVel;
        y += yVel;

        if(y < 10)  // centre of the ball is (10,10), radius = 10
            yVel = -yVel;
        if(y > 490) // width - radius = 500-10 = 490
            yVel = -yVel;
    }

    public int getX(){
        return (int)x;
    }

    public int getY(){
        return (int)y;
    }
}

package com.game.catching;

public class Object {
    float x,y;
    double vx, vy;
    float height, width;
    float angle, speedRotation;

    void move(){
    }

    float getX(){
        return x-width/2;
    }

    float getY(){
        return y-height/2;
    }
}

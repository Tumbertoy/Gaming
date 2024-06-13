package com.game.catching;

public class BrokenEgg extends Object {

    boolean flipX, flipY;

    public BrokenEgg(float x, float y){
        this.x = x;
        this.y = y;
        this.flipX = false;
        this.flipY = false;


        if (x > 592 && x<700){ //упало с левых лотков
            this.flipX = true;


        }
        else if (x > 700 && x<1560) {//упало с правых лотков
            this.flipX = false;
        }

    }
}

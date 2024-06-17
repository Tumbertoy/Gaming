package com.game.catching;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.bullet.linearmath.SWIGTYPE_p_btAlignedObjectArrayT_btPlane_t;

public class Egg extends Object{
    Boolean top;

    public Egg(float x, float y){
        this.x = x;
        this.y = y;
        float a = 2;
        float v = 1;
        this.top = false;
        if(y>500){
            top = true;
        }



        vx = v*MathUtils.sin(a);
        vy = v*MathUtils.cos(a);


    }

    double gravity = -0.03;

    @Override
    void move() {
        super.move();
        if (x<592){ //на левых лотках
            x += vx;
            y += vy;
            speedRotation = -10;
        } else if (x > 592 && x<700){ //упало с левых лотков

            speedRotation = -3;
            vy = vy + gravity;
            y += 5*vy ;
        } else if (x > 700 && x<1560) { //упало с правых лотков
            speedRotation = 3;
            vy = vy + gravity;
            y += 5*vy ;
        } else if (x>1560) { //на правых лотках
            x -= vx;
            y += vy;
            speedRotation = 10;
        }

        angle+=speedRotation;
    }
    public boolean eggOnGround(){
        return y < 100;
    }

}








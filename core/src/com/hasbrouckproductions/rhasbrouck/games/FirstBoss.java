package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Random;

/**
 * Created by hasbrouckr on 6/23/2016.
 */
public class FirstBoss extends Boss {
    public float lastUpperFireTime;
    public float lastLowerFireTime;

    public float stateTime;

    public FirstBoss(float x, float y){
        super(x, y);
        xPos = x;
        yPos = y;
        hp = 20;
        lastUpperFireTime = TimeUtils.nanoTime();
        lastLowerFireTime = TimeUtils.nanoTime();
        state = BOSS_IS_ALIVE;
    }

    public void update(float deltaTime){
        this.bounds = new Rectangle(xPos, yPos, BOSS_WIDTH, BOSS_HEIGHT);

        if(xPos > 700) {
            xPos -= 100 * Gdx.graphics.getDeltaTime();
        }
        stateTime += deltaTime;
    }

    public void updateUpperFireTime(){
        lastUpperFireTime = TimeUtils.nanoTime();
    }

    public void updateLowerFireTime(){
        lastLowerFireTime = TimeUtils.nanoTime();
    }
}

package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Random;

/**
 * Created by hasbrouckr on 6/23/2016.
 */
public class FirstBoss extends DynamicGameObject {

    public static final int BOSS_IS_ALIVE = 1;
    public static final int BOSS_IS_DEAD = 2;

    public static final float BOSS_WIDTH = 70;
    public static final float BOSS_HEIGHT = 70;

    public float xPos;
    public float yPos;

    public float lastUpperFireTime;
    public float lastLowerFireTime;


    public int state;
    public int hp;
    float stateTime = 0;

    public FirstBoss(float x, float y){
        super(x, y, BOSS_WIDTH, BOSS_HEIGHT);
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

    public void hit(){
        hp--;
        if(hp == 0){
            state = BOSS_IS_DEAD;
        }
    }
}

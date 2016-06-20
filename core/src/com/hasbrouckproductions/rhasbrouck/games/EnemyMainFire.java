package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by hasbrouckr on 6/20/2016.
 */
public class EnemyMainFire extends DynamicGameObject {
    public static final int LASER_NOT_HIT = 1;
    public static final int LASER_HIT = 2;
    public static final int LASER_WIDTH = 50;
    public static final int LASER_HEIGHT = 10;

    public static final int LASER_VELOCITY = 400;

    public float xPos;
    public float yPos;
    public static float lastFireTime;
    public int state;


    public EnemyMainFire(float x, float y){
        super(x, y, LASER_WIDTH, LASER_HEIGHT);
        xPos = x;
        yPos = y;
        state = LASER_NOT_HIT;
        lastFireTime = TimeUtils.nanoTime();
    }

    //update laser position
    public void update(){
        xPos -= LASER_VELOCITY * Gdx.graphics.getDeltaTime();
        this.bounds = new Rectangle(xPos, yPos, LASER_WIDTH, LASER_HEIGHT);
    }

    public void hitEnemy(){
        state = LASER_HIT;
    }
}

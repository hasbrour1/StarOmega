package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by hasbrouckr on 6/16/2016.
 *
 * This class holds the properties of the
 * Main Ships Laser
 *
 */
public class MainLaser extends DynamicGameObject{

    public static final int LASER_NOT_HIT = 1;
    public static final int LASER_HIT = 2;
    public static final int LASER_WIDTH = 50;
    public static final int LASER_HEIGHT = 10;

    public static final int LASER_VELOCITY = 400;

    public float xPos;
    public float yPos;
    public static float lastFireTime;
    public int state;


    public MainLaser(float x, float y){
        super(x, y, LASER_WIDTH, LASER_HEIGHT);
        xPos = x;
        yPos = y;
        state = LASER_NOT_HIT;
        lastFireTime = TimeUtils.nanoTime();
    }

    //update laser position
    public void update(){
        xPos += LASER_VELOCITY * Gdx.graphics.getDeltaTime();
        this.bounds = new Rectangle(xPos, yPos, LASER_WIDTH, LASER_HEIGHT);
    }

    public void hitEnemy(){
        state = LASER_HIT;
    }
}

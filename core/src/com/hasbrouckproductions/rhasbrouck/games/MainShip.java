package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by hasbrouckr on 6/10/2016.
 */
public class MainShip extends DynamicGameObject {
    public static final int SHIP_STATE_ALIVE = 1;
    public static final int SHIP_STATE_HIT = 2;
    public static final int SHIP_DEAD = 3;

    public static final float SHIP_WIDTH = 100;
    public static final float SHIP_HEIGHT = 100;

    public static float xPos;
    public static float yPos;
    int state;
    float stateTime;

    int powerUp;

    public MainShip (float x, float y) {
        super(x, y, SHIP_WIDTH, SHIP_HEIGHT);
        stateTime = 0;
        state = SHIP_STATE_ALIVE;
        powerUp = 0;
    }

    public void update (float deltaTime, float x, float y) {
        xPos = x;
        yPos = y;
        this.bounds = new Rectangle(xPos, yPos, SHIP_WIDTH, SHIP_HEIGHT);
        stateTime += deltaTime;
    }

    public void gotHit () {
        velocity.set(0, 0);
        if(powerUp == 0) {
            state = SHIP_DEAD;
        }
        else{
            powerUp--;
        }
        stateTime = 0;
    }

    public void gotPower(){
        powerUp = 1;
    }
}

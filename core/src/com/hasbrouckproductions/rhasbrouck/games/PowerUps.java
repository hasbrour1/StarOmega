package com.hasbrouckproductions.rhasbrouck.games;

/**
 * Created by hasbrouckr on 6/10/2016.
 */
public class PowerUps extends DynamicGameObject {
    public static final float POWERUP_HEIGHT = 0.6f;
    public static final float POWERUP_WIDHT = 1;
    public static final float POWERUP_VELOCITY = 3f;

    float stateTime = 0;

    public PowerUps(float x, float y){
        super(x, y, POWERUP_WIDHT, POWERUP_HEIGHT);
        stateTime = 0;
    }

    public void update(float deltaTime){
        stateTime += deltaTime;
    }
}

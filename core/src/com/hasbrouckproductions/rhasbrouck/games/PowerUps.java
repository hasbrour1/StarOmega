package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Gdx;

/**
 * Created by hasbrouckr on 6/10/2016.
 */
public class PowerUps extends DynamicGameObject {
    public static final float POWERUP_HEIGHT = 0.6f;
    public static final float POWERUP_WIDHT = 1;
    public static final float POWERUP_VELOCITY = 3f;

    public static int xPos;
    public static int yPos;

    float stateTime = 0;

    public PowerUps(float x, float y){
        super(x, y, POWERUP_WIDHT, POWERUP_HEIGHT);
        xPos = (int)x;
        yPos = (int)y;
        stateTime = 0;
    }

    public void update(float deltaTime){

        xPos -= 200 * Gdx.graphics.getDeltaTime();

        stateTime += deltaTime;
    }
}

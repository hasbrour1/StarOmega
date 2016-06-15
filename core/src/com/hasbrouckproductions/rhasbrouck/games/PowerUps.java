package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by hasbrouckr on 6/10/2016.
 */
public class PowerUps extends DynamicGameObject {
    public static final float POWERUP_HEIGHT = 50;
    public static final float POWERUP_WIDHT = 50;

    public int xPos;
    public int yPos;

    float stateTime = 0;

    public PowerUps(float x, float y){
        super(x, y, POWERUP_WIDHT, POWERUP_HEIGHT);
        xPos = (int)x;
        yPos = (int)y;
        stateTime = 0;
    }

    public void update(float deltaTime){

        xPos -= 200 * Gdx.graphics.getDeltaTime();
        this.bounds = new Rectangle(xPos, yPos, POWERUP_WIDHT, POWERUP_HEIGHT);

        stateTime += deltaTime;
    }
}

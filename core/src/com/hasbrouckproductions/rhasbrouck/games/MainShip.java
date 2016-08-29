package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by hasbrouckr on 6/10/2016.
 *
 * This class holds all the attributes for
 * the main player ship
 *
 *
 */
public class MainShip extends DynamicGameObject {
    public static final int SHIP_STATE_ALIVE = 1;
    public static final int SHIP_STATE_POWERUP = 2;
    public static final int SHIP_DEAD = 3;

    public static final float SHIP_WIDTH = 100;
    public static final float SHIP_HEIGHT = 100;

    public static final int SHIP_SPEED = 400;

    public static float xPos;
    public static float yPos;
    int state;
    float stateTime;

    public MainShip (float x, float y) {
        super(x, y, SHIP_WIDTH, SHIP_HEIGHT);
        stateTime = 0;
        state = SHIP_STATE_ALIVE;
    }

    //update ships position
    public void update (float deltaTime, float x, float y) {

        //Move ship closer to touch point
        if(xPos > x && xPos + 50 >= x){
            xPos -= SHIP_SPEED * Gdx.graphics.getDeltaTime();
        }else if(xPos < x && xPos + 50 <= x){
            xPos += SHIP_SPEED * Gdx.graphics.getDeltaTime();
        }

        if(yPos > y && yPos + 50 >= y){
            yPos -= SHIP_SPEED * Gdx.graphics.getDeltaTime();
        }else if(yPos < y && yPos + 50 <= y){
            yPos += SHIP_SPEED * Gdx.graphics.getDeltaTime();
        }

        this.bounds = new Rectangle(xPos, yPos, SHIP_WIDTH, SHIP_HEIGHT);
        stateTime += deltaTime;
    }

    //updates ship status when geting hit
    public void gotHit () {
        velocity.set(0, 0);
        if(this.state == SHIP_STATE_ALIVE) {
            state = SHIP_DEAD;
        }
        else{
            state = SHIP_STATE_ALIVE;
        }
        stateTime = 0;
    }

    //Gets power up
    public void gotPower(){
        state = SHIP_STATE_POWERUP;
    }
}

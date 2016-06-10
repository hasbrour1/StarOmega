package com.hasbrouckproductions.rhasbrouck.games;

/**
 * Created by hasbrouckr on 6/10/2016.
 */
public class MainShip extends DynamicGameObject {
    public static final int SHIP_STATE_ALIVE = 1;
    public static final int SHIP_STATE_HIT = 2;
    public static final int SHIP_DEAD = 3;
    public static final float SHIP_MOVE_VELOCITY = 20;
    public static final float SHIP_WIDTH = 0.8f;
    public static final float SHIP_HEIGHT = 0.8f;

    int state;
    float stateTime;

    int powerUp;

    public MainShip (float x, float y) {
        super(x, y, SHIP_WIDTH, SHIP_HEIGHT);
        stateTime = 0;
        state = SHIP_STATE_ALIVE;
        powerUp = 0;
    }

    public void update (float deltaTime) {
        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
        bounds.x = position.x - bounds.width / 2;
        bounds.y = position.y - bounds.height / 2;


        if (position.x < 0) position.x = World.WORLD_WIDTH;
        if (position.x > World.WORLD_WIDTH) position.x = 0;

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

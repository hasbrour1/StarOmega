package com.hasbrouckproductions.rhasbrouck.games;

/**
 * Created by hasbrouckr on 6/10/2016.
 */
public class Enemy extends DynamicGameObject{

    public static final int ENEMY_IS_ALIVE = 1;
    public static final int ENEMY_IS_DEAD = 2;

    public static final float ENEMY_WIDTH = 1;
    public static final float ENEMY_HEIGHT = 0.6f;
    public static final float ENEMY_VELOCITY = 3f;

    public int state;
    public int hp;
    float stateTime = 0;

    public Enemy(float x, float y){
        super(x, y, ENEMY_WIDTH, ENEMY_HEIGHT);
        velocity.set(ENEMY_VELOCITY, 0);
        state = 1;
    }

    public void update(float deltaTime){
        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
        bounds.x = position.x - ENEMY_WIDTH / 2;
        bounds.y = position.y - ENEMY_HEIGHT / 2;

        if (position.x < ENEMY_WIDTH / 2) {
            position.x = ENEMY_WIDTH / 2;
            velocity.x = ENEMY_VELOCITY;
        }
        if (position.x > World.WORLD_WIDTH - ENEMY_WIDTH / 2) {
            position.x = World.WORLD_WIDTH - ENEMY_WIDTH / 2;
            velocity.x = -ENEMY_VELOCITY;
        }
        stateTime += deltaTime;
    }

    public void hit(){
        hp--;
        if(hp == 0){
            state = ENEMY_IS_DEAD;
        }
    }
}

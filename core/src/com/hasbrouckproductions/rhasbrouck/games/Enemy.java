package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by hasbrouckr on 6/10/2016.
 */
public class Enemy extends DynamicGameObject{

    public final int ENEMY_IS_ALIVE = 1;
    public final int ENEMY_IS_DEAD = 2;

    public static final float ENEMY_WIDTH = 70;
    public static final float ENEMY_HEIGHT = 70;

    public float xPos;
    public float yPos;


    public int state;
    public int hp;
    float stateTime = 0;

    public Enemy(float x, float y){
        super(x, y, ENEMY_WIDTH, ENEMY_HEIGHT);
        xPos = x;
        yPos = y;
        state = 1;
    }

    public void update(float deltaTime){

        xPos -= 100 * Gdx.graphics.getDeltaTime();
        stateTime += deltaTime;
    }

    public void hit(){
        hp--;
        if(hp == 0){
            state = ENEMY_IS_DEAD;
        }
    }
}

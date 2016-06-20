package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Random;

/**
 * Created by hasbrouckr on 6/10/2016.
 */
public class Enemy extends DynamicGameObject{

    public static final int ENEMY_IS_ALIVE = 1;
    public static final int ENEMY_IS_DEAD = 2;

    public static final float ENEMY_WIDTH = 70;
    public static final float ENEMY_HEIGHT = 70;

    public float xPos;
    public float yPos;

    public float lastFireTime;


    public int state;
    public int hp;
    float stateTime = 0;

    public Enemy(float x, float y){
        super(x, y, ENEMY_WIDTH, ENEMY_HEIGHT);
        xPos = x;
        yPos = y;
        generateHp();
        lastFireTime = TimeUtils.nanoTime();
        state = ENEMY_IS_ALIVE;
    }

    private void generateHp(){
        Random rand =  new Random();
        hp = rand.nextInt(5 - 1) + 1;
    }

    public void update(float deltaTime){
        this.bounds = new Rectangle(xPos, yPos, ENEMY_WIDTH, ENEMY_HEIGHT);
        xPos -= 100 * Gdx.graphics.getDeltaTime();
        stateTime += deltaTime;
    }

    public void updateFireTime(){
        lastFireTime = TimeUtils.nanoTime();
    }

    public void hit(){
        hp--;
        if(hp == 0){
            state = ENEMY_IS_DEAD;
        }
    }
}

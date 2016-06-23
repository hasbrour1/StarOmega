package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.oracle.jrockit.jfr.DynamicEventToken;

/**
 * Created by hasbrouckr on 6/23/2016.
 */
public abstract class Boss extends DynamicGameObject {

    public static final int BOSS_IS_ALIVE = 1;
    public static final int BOSS_IS_DEAD = 2;

    public static final float BOSS_WIDTH = 70;
    public static final float BOSS_HEIGHT = 70;

    public float xPos;
    public float yPos;

    public int state;
    public int hp;

    public Boss(float x, float y){
        super(x, y, BOSS_WIDTH, BOSS_HEIGHT);
        xPos = x;
        yPos = y;
        state = BOSS_IS_ALIVE;
    }

    public abstract void update(float deltaTime);

    public void hit(){
        hp--;
        if(hp == 0){
            state = BOSS_IS_DEAD;
        }
    }
}

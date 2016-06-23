package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.oracle.jrockit.jfr.DynamicEventToken;

import java.util.ArrayList;

/**
 * Created by hasbrouckr on 6/23/2016.
 * Abstract boss class for each boss
 *
 */
public abstract class Boss extends DynamicGameObject {

    public static final int BOSS_IS_ALIVE = 1;
    public static final int BOSS_IS_DEAD = 2;

    public ArrayList<EnemyMainFire> bossLasers;

    public float xPos;
    public float yPos;

    public int state;
    public int hp;

    public Boss(float x, float y, float BOSS_WIDTH, float BOSS_HEIGHT){
        super(x, y, BOSS_WIDTH, BOSS_HEIGHT);
        xPos = x;
        yPos = y;
        bossLasers = new ArrayList<EnemyMainFire>();
        state = BOSS_IS_ALIVE;
    }

    public abstract void update(float deltaTime, World world);

    public abstract void updateWeapons(World world);

    public void hit(){
        hp--;
        if(hp == 0){
            state = BOSS_IS_DEAD;
        }
    }
}

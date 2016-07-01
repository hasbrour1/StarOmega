package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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

    public static float bossHeight;
    public static float bossWidth;

    public ArrayList<EnemyMainFire> bossLasers;
    public ArrayList<EnemyBeam> bossBeams;

    public float xPos;
    public float yPos;

    public int state;
    public int hp;

    public Texture bossTexture;

    public Boss(float x, float y, float BOSS_WIDTH, float BOSS_HEIGHT){
        super(x, y, BOSS_WIDTH, BOSS_HEIGHT);
        xPos = x;
        yPos = y;
        bossLasers = new ArrayList<EnemyMainFire>();
        bossBeams = new ArrayList<EnemyBeam>();
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

    public void checkRemoveWeapons(){
        int len = bossLasers.size();
        for (int i = 0; i < len; i++) {
            EnemyMainFire laser = bossLasers.get(i);
            if(laser.state == EnemyMainFire.LASER_HIT || laser.xPos <= -50){
                bossLasers.remove(i);
                len --;
            }
        }

        len = bossBeams.size();
        for (int i = 0; i < len; i++) {
            EnemyBeam beam = bossBeams.get(i);
            if(beam.xPos <= - 3000){
                bossBeams.remove(i);
                len--;
            }
        }
    }
}

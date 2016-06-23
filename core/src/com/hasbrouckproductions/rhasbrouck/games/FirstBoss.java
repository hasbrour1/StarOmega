package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Random;

/**
 * Created by hasbrouckr on 6/23/2016.
 */
public class FirstBoss extends Boss {

    public static final float BOSS_WIDTH = 70;
    public static final float BOSS_HEIGHT = 70;

    public float lastUpperFireTime;
    public float lastLowerFireTime;

    public float stateTime;

    public FirstBoss(float x, float y){
        super(x, y, BOSS_WIDTH, BOSS_HEIGHT);
        xPos = x;
        yPos = y;
        hp = 20;
        lastUpperFireTime = TimeUtils.nanoTime();
        lastLowerFireTime = TimeUtils.nanoTime();
        state = BOSS_IS_ALIVE;
    }

    public void update(float deltaTime, World world){
        this.bounds = new Rectangle(xPos, yPos, BOSS_WIDTH, BOSS_HEIGHT);

        if(xPos > 700) {
            xPos -= 100 * Gdx.graphics.getDeltaTime();
        }

        updateWeapons(world);

        stateTime += deltaTime;
    }

    @Override
    public void updateWeapons(World world) {
        //Check to add Boss Laser
        if(xPos < 800) {
            if (TimeUtils.nanoTime() - lastLowerFireTime > (1000000000 * 2) + 500000000) {
                world.listener.shoot();
                updateLowerFireTime();
                bossLasers.add(new EnemyMainFire(xPos - 100, yPos - 150));
            }

            if (TimeUtils.nanoTime() - lastUpperFireTime > (1000000000 * 2)) {
                world.listener.shoot();
                updateUpperFireTime();
                bossLasers.add(new EnemyMainFire(xPos - 100, yPos + 150));
            }
        }

        //Update Laser Positions
        for(EnemyMainFire laser : bossLasers){
            laser.update();
        }
    }

    public void updateUpperFireTime(){
        lastUpperFireTime = TimeUtils.nanoTime();
    }

    public void updateLowerFireTime(){
        lastLowerFireTime = TimeUtils.nanoTime();
    }
}

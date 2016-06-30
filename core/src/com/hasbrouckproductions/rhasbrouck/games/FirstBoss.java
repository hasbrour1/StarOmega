package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Random;

/**
 * Created by hasbrouckr on 6/23/2016.
 * The games first boss
 *
 */
public class FirstBoss extends Boss {

    public static final float BOSS_WIDTH = 300;
    public static final float BOSS_HEIGHT = 300;

    public float lastUpperFireTime;
    public float lastLowerFireTime;

    public float stateTime;

    private boolean direction;

    public FirstBoss(float x, float y){
        super(x, y, BOSS_WIDTH, BOSS_HEIGHT);
        xPos = x;
        yPos = y;
        hp = 20;

        bossWidth = BOSS_WIDTH;
        bossHeight = BOSS_HEIGHT;
        lastUpperFireTime = TimeUtils.nanoTime();
        lastLowerFireTime = TimeUtils.nanoTime();
        bossTexture = Assets.firstBossTexture;
        state = BOSS_IS_ALIVE;
        direction = true;
    }

    public void update(float deltaTime, World world){


        if(xPos > 500) {
            xPos -= 100 * Gdx.graphics.getDeltaTime();
        }else{
            if(direction){
                if(yPos < 230){
                    yPos += 50 * Gdx.graphics.getDeltaTime();
                }else{
                    direction = false;
                }
            }else{
                if(yPos > 25){
                    yPos -= 50 * Gdx.graphics.getDeltaTime();
                }else{
                    direction = true;
                }
            }
        }

        this.bounds = new Rectangle(xPos, yPos + 100, BOSS_WIDTH, BOSS_HEIGHT - 200);

        updateWeapons(world);

        stateTime += deltaTime;
    }

    @Override
    public void updateWeapons(World world) {
        //Check to add Boss Laser
        if(xPos < 800) {
            if (TimeUtils.nanoTime() - lastLowerFireTime > (1000000000 * 2)) {
                world.listener.shoot();
                updateLowerFireTime();
                bossLasers.add(new EnemyMainFire(xPos - 100, yPos + 100));
            }

            if (TimeUtils.nanoTime() - lastUpperFireTime > (1000000000 * 2) &&
                    TimeUtils.nanoTime() - lastLowerFireTime > (1000000000)) {
                world.listener.shoot();
                updateUpperFireTime();
                bossLasers.add(new EnemyMainFire(xPos - 100, yPos + 200));
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

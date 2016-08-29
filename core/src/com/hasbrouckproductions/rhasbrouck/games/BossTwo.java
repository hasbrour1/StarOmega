package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by hasbrouckr on 6/30/2016.
 * Boss for Level 2
 *
 */
public class BossTwo extends Boss {

    public static final float BOSS_WIDTH = 330;
    public static final float BOSS_HEIGHT = 310;

    public float lastUpperFireTime;
    public float lastLowerFireTime;
    public float beamWeaponFireTime;

    public float stateTime;

    public BossTwo(float x, float y){
        super(x, y, BOSS_WIDTH, BOSS_HEIGHT);
        xPos = x;
        yPos = y;
        hp = 20;

        bossWidth = BOSS_WIDTH;
        bossHeight = BOSS_HEIGHT;
        lastUpperFireTime = TimeUtils.nanoTime();
        lastLowerFireTime = TimeUtils.nanoTime();
        beamWeaponFireTime = TimeUtils.nanoTime();
        bossTexture = Assets.secondBossTexture;
        state = BOSS_IS_ALIVE;
    }

    public void update(float deltaTime, World world){

        if(xPos > 550) {
            xPos -= 100 * Gdx.graphics.getDeltaTime();
        }

        this.bounds = new Rectangle(xPos, yPos + 100, BOSS_WIDTH, BOSS_HEIGHT - 200);

        updateWeapons(world);

        stateTime += deltaTime;
    }

    @Override
    public void updateWeapons(World world) {
        //Check to add Boss Laser
        if(xPos < 550) {
            if (TimeUtils.nanoTime() - lastLowerFireTime > (1000000000 * 2)) {
                world.listener.shoot();
                updateLowerFireTime();
                bossLasers.add(new EnemyMainFire(xPos + 160, yPos + 30));
            }

            if (TimeUtils.nanoTime() - lastUpperFireTime > (1000000000 * 2)) {
                world.listener.shoot();
                updateUpperFireTime();
                bossLasers.add(new EnemyMainFire(xPos + 160, yPos + 300));
            }

            if (bossBeams.isEmpty() && TimeUtils.nanoTime() - beamWeaponFireTime > (1000000000 * 5)) {
                world.listener.shoot();
                beamWeaponFireTime = TimeUtils.nanoTime();
                bossBeams.add(new EnemyBeam(xPos + 50, yPos + 130));
            }
        }

        //Update Laser Positions
        for(EnemyMainFire laser : bossLasers){
            laser.update();
        }

        for(EnemyBeam beam : bossBeams){
            beam.update();
        }

        checkRemoveWeapons();
    }

    public void updateUpperFireTime(){
        lastUpperFireTime = TimeUtils.nanoTime();
    }

    public void updateLowerFireTime(){
        lastLowerFireTime = TimeUtils.nanoTime();
    }
}

package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hasbrouckr on 9/15/2016.
 */
public class BossFour extends Boss {

    public static final int BOSS_WIDTH = 279;
    public static final int BOSS_HEIGHT = 348;

    public static final int NOT_CHARGING = 0;
    public static final int PREPARE_CHARGE = 1;
    public static final int CHARGE_FORWARD = 2;
    public static final int RESET_POSITION = 3;
    public static final int START_TIMER = 4;
    public static final int INCOMMING = 5;

    private float lastInnerWeaponFire;
    private float lastOuterWeaponFire;

    boolean direction;
    int chargingState;

    Timer chargeTimer;

    public BossFour(float x, float y){
        super(x, y, BOSS_WIDTH, BOSS_HEIGHT);
        xPos = x;
        yPos = y;
        hp = 40;
        direction = true;
        chargingState = INCOMMING;
        chargeTimer = new Timer();

        bossWidth = BOSS_WIDTH;
        bossHeight = BOSS_HEIGHT;
        bossTexture = Assets.fourthBossTexture;
        state = BOSS_IS_ALIVE;
    }


    @Override
    public void updateWeapons(World world) {
        if(chargingState == NOT_CHARGING){
            if (TimeUtils.nanoTime() - lastOuterWeaponFire > (100000000 * 2)) {
                world.listener.shoot();
                lastOuterWeaponFire = TimeUtils.nanoTime();
                bossLasers.add(new EnemyMainFire(xPos + 155, yPos + 200));
                bossLasers.add(new EnemyMainFire(xPos + 155, yPos + 90));
            }

            if (TimeUtils.nanoTime() - lastInnerWeaponFire > (100000000 * 2)) {
                world.listener.shoot();
                lastInnerWeaponFire = TimeUtils.nanoTime();
                bossLasers.add(new EnemyMainFire(xPos + 95, yPos + 175));
                bossLasers.add(new EnemyMainFire(xPos + 95, yPos + 115));
            }
        }

        //Update Laser Positions
        for(EnemyMainFire laser : bossLasers){
            laser.update();
        }

        checkRemoveWeapons();
    }

    @Override
    public void update(float deltaTime, World world) {
            switch(chargingState){
                case INCOMMING:
                    xPos -= 100 * Gdx.graphics.getDeltaTime();
                    if(xPos < 500)
                        chargingState = START_TIMER;
                    break;
                case START_TIMER:
                    lastInnerWeaponFire = TimeUtils.nanoTime();
                    lastOuterWeaponFire = TimeUtils.nanoTime();
                    chargeTimer.schedule( new TimerTask() {
                        public void run() {
                            chargingState = PREPARE_CHARGE;
                        }
                    }, 0, 15*1000);
                    break;
                case NOT_CHARGING:
                    if (direction) {
                        if (yPos < 230) {
                                yPos += 50 * Gdx.graphics.getDeltaTime();
                        } else {
                            direction = false;
                        }
                    } else {
                        if (yPos > 25) {
                            yPos -= 50 * Gdx.graphics.getDeltaTime();
                        } else {
                            direction = true;
                        }
                    }
                    break;
                case PREPARE_CHARGE:
                    xPos += 100 * Gdx.graphics.getDeltaTime();
                    if(xPos > 700) {
                        chargingState = CHARGE_FORWARD;
                    }
                    break;
                case CHARGE_FORWARD:
                    xPos -= 420 * Gdx.graphics.getDeltaTime();
                        if(xPos < 50) {
                            chargingState = RESET_POSITION;
                        }
                    break;
                case RESET_POSITION:
                    xPos += 200 * Gdx.graphics.getDeltaTime();
                    if(xPos >= 500) {
                        chargingState = NOT_CHARGING;
                    }
                    break;
                default:
                    break;
            }

        this.bounds = new Rectangle(xPos, yPos + 100, BOSS_WIDTH, BOSS_HEIGHT - 200);

        updateWeapons(world);
    }
}

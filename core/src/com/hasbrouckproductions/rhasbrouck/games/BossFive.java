package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hasbrouckr on 9/16/2016.
 */
public class BossFive extends Boss {
    public static final int BOSS_WIDTH = 279;
    public static final int BOSS_HEIGHT = 348;

    public static final int SHIELD_UP = 0;
    public static final int SHIELD_DOWN = 1;
    public static final int INCOMMING = 2;
    public static final int START_TIMER = 3;

    private float lastInnerWeaponFire;
    private float lastOuterWeaponFire;

    boolean direction;
    int bossState;

    Timer shieldTimer;

    public BossFive(float x, float y){
        super(x, y, BOSS_WIDTH, BOSS_HEIGHT);
        xPos = x;
        yPos = y;
        hp = 40;
        direction = true;
        bossState = INCOMMING;
        shieldTimer = new Timer();

        bossWidth = BOSS_WIDTH;
        bossHeight = BOSS_HEIGHT;
        bossTexture = Assets.bossFiveUnshieldedTexture;
        state = BOSS_IS_ALIVE;
    }


    @Override
    public void updateWeapons(World world) {

        checkRemoveWeapons();
    }

    @Override
    public void update(float deltaTime, World world) {

        switch(bossState){
            case INCOMMING:
                xPos -= 100 * Gdx.graphics.getDeltaTime();
                if(xPos < 500)
                    bossState = START_TIMER;
                break;
            case START_TIMER:
                shieldTimer.schedule( new TimerTask() {
                    public void run() {
                        bossState = SHIELD_UP;
                    }
                }, 0, 20*1000);
                break;
            case SHIELD_DOWN:

                break;
            case SHIELD_UP:

                break;
            default:
                break;
        }

        this.bounds = new Rectangle(xPos, yPos + 100, BOSS_WIDTH, BOSS_HEIGHT - 200);

        updateWeapons(world);
    }
}

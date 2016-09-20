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
    public static final int BOSS_WIDTH = 450;
    public static final int BOSS_HEIGHT = 450;

    public static final int SHIELD_UP = 0;
    public static final int SHIELD_DOWN = 1;
    public static final int INCOMMING = 2;
    public static final int START_TIMER = 3;

    public static final int MOVE_LEFT = 0;
    public static final int MOVE_RIGHT = 1;
    public static final int MOVE_UP = 2;
    public static final int MOVE_DOWN = 3;

    private float lastWeaponFire;

    boolean direction;
    int bossState;
    int moveState;

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
    //TODO: Create weapon fire and shield graphic
    public void updateWeapons(World world) {

        checkRemoveWeapons();
    }

    @Override
    public void update(float deltaTime, World world) {

        switch(bossState){
            case INCOMMING:
                xPos -= 100 * Gdx.graphics.getDeltaTime();
                if(xPos < 500) {
                    moveState = MOVE_UP;
                    bossState = START_TIMER;
                }
                break;
            case START_TIMER:
                shieldTimer.schedule( new TimerTask() {
                    public void run() {
                        if(bossState == SHIELD_UP){
                            bossTexture = Assets.bossFiveUnshieldedTexture;
                            bossState = SHIELD_DOWN;
                        }else {
                            bossTexture = Assets.bossFiveShieldedTexture;
                            bossState = SHIELD_UP;
                        }
                    }
                }, 0, 20*1000);
                break;
            case SHIELD_DOWN:
                moveBoss();
                break;
            case SHIELD_UP:
                moveBoss();
                break;
            default:
                break;
        }

        this.bounds = new Rectangle(xPos + 100, yPos + 100, BOSS_WIDTH - 100, BOSS_HEIGHT - 200);

        updateWeapons(world);
    }

    //moves boss in a box formation
    public void moveBoss(){
        switch(moveState){
            case MOVE_DOWN:
                if (yPos > -120) {
                    yPos -= 150 * Gdx.graphics.getDeltaTime();
                } else {
                    moveState = MOVE_RIGHT;
                }
                break;
            case MOVE_UP:
                if (yPos < 170) {
                    yPos += 150 * Gdx.graphics.getDeltaTime();
                } else {
                    moveState = MOVE_LEFT;
                }
                break;
            case MOVE_LEFT:
                if(xPos > 100) {
                    xPos -= 150 * Gdx.graphics.getDeltaTime();
                }else{
                    moveState = MOVE_DOWN;
                }
                break;
            case MOVE_RIGHT:
                if(xPos < 500) {
                    xPos += 150 * Gdx.graphics.getDeltaTime();
                }else{
                    moveState = MOVE_UP;
                }
                break;
            default:
                break;
        }
    }

    public void hit(){
        switch(bossState){
            case SHIELD_UP:
                //do nothing
                break;
            case SHIELD_DOWN:
                hp--;
                if(hp == 0){
                    state = BOSS_IS_DEAD;
                }
                break;
            default:
                break;
        }
    }
}

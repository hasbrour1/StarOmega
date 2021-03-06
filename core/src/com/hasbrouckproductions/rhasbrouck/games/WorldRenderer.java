package com.hasbrouckproductions.rhasbrouck.games;

/**
 * Created by hasbrouckr on 6/10/2016.
 *
 * Renders the objects present from World Class
 *
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Logger;

import java.util.Iterator;

public class WorldRenderer {
    World world;
    OrthographicCamera cam;
    SpriteBatch batch;
    float stateTime;

    public WorldRenderer (SpriteBatch batch, World world) {
        this.world = world;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 800, 480);
        this.batch = batch;
        stateTime = 0f;
    }

    public void render () {
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        renderBackground();
        renderObjects();
    }

    public void renderBackground () {
        batch.disableBlending();
        batch.begin();
        batch.draw(Assets.backgroundRegion, 0, 0, 800, 480);
        batch.end();
    }

    public void renderObjects () {
        batch.enableBlending();
        batch.begin();
        renderShip();
        renderMainShipFire();
        renderMainShipHp();
        renderEnemyFire();
        renderEnemies();
        renderPowerUps();
        renderBoss();
        renderBossFire();
        batch.end();
    }

    //render the ship depending on ship state
    private void renderShip () {
        TextureRegion keyFrame;
        switch (world.ship.state) {
            case MainShip.SHIP_STATE_ALIVE:
                keyFrame = Assets.mainShip;
                break;
            case MainShip.SHIP_DEAD:
                stateTime += Gdx.graphics.getDeltaTime();
                keyFrame = Assets.explosionAnimation.getKeyFrame(stateTime, false);
                break;
            default:
                keyFrame = Assets.mainShip;
        }
        batch.draw(keyFrame, world.ship.xPos, world.ship.yPos, 100, 100);
    }

    private void renderMainShipFire(){
        for(MainLaser laser : world.mainShipLasers){
            batch.draw(Assets.mainLaser, laser.xPos, laser.yPos, 50, 10);
        }
    }

    //Renderes the mainship hp
    private void renderMainShipHp(){
        if(world.ship.hitPoints > 0){
            int hp = world.ship.hitPoints;

            //all hp should be green
            if(hp % 2  == 0){
                for(int i = 0; i < hp / 2; i++){
                    batch.draw(Assets.greenHp, 420 + (i * 25), 440, 20 , 20);
                }
            }else{//last hp should be red
                for(int i = 0; i < (hp - 1) / 2; i++){
                    batch.draw(Assets.greenHp, 420 + (i * 25), 440, 20 , 20);
                }

                batch.draw(Assets.redHp, 420 + (((hp - 1) / 2) * 25), 440, 20 , 20);
            }
        }
    }

    private void renderEnemyFire(){
        for(EnemyMainFire laser : world.enemyShipLasers){
            batch.draw(Assets.mainLaser, laser.xPos, laser.yPos, 50, 10);
        }
    }

    private void renderEnemies () {

        int len = world.enemies.size();
        for (int i = 0; i < len; i++) {
            Enemy enemy = world.enemies.get(i);
            batch.draw(Assets.enemyShip, enemy.xPos, enemy.yPos, 70, 70);
        }
    }

    private void renderPowerUps () {
        int len = world.powerUps.size();
        for (int i = 0; i < len; i++) {
            PowerUps power = world.powerUps.get(i);
            batch.draw(Assets.powerUp, power.xPos, power.yPos, 70, 70);
        }
    }

    private void renderBoss(){
        if(world.boss.state != Boss.BOSS_IS_DEAD) {
            batch.draw(world.boss.bossTexture, world.boss.xPos, world.boss.yPos, world.boss.bossHeight, world.boss.bossWidth);
        }
    }

    private void renderBossFire(){
        for(EnemyMainFire laser : world.boss.bossLasers){
            batch.draw(Assets.mainLaser, laser.xPos, laser.yPos, 50, 10);
        }

        for(EnemyBeam beam : world.boss.bossBeams){
            batch.draw(Assets.mainLaser, beam.xPos, beam.yPos, beam.beamWidth, EnemyBeam.BEAM_HEIGHT);
        }
    }
}
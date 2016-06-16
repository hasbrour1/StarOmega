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

    public WorldRenderer (SpriteBatch batch, World world) {
        this.world = world;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 800, 480);
        this.batch = batch;
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
        renderEnemies();
        renderPowerUps();
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
                keyFrame = Assets.explosion;
                break;
            case MainShip.SHIP_STATE_HIT:
                keyFrame = Assets.shipHit;
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

    private void renderEnemies () {

        int len = world.enemies.size();
        for (int i = 0; i < len; i++) {
            Enemy enemy = world.enemies.get(i);
            Gdx.app.log("ENEMY SHIP", + enemy.xPos + " " + enemy.yPos + " " + i);
            batch.draw(Assets.enemyShip, enemy.xPos, enemy.yPos, 70, 70);
        }
    }

    private void renderPowerUps () {
        int len = world.powerUps.size();
        Gdx.app.log("POWER UP ",  "Rendering Power Ups");
        for (int i = 0; i < len; i++) {
            PowerUps power = world.powerUps.get(i);
            Gdx.app.log("POWER UP ",  power.xPos + " " + power.yPos);
            batch.draw(Assets.powerUp, power.xPos, power.yPos, 70, 70);
        }
    }
}
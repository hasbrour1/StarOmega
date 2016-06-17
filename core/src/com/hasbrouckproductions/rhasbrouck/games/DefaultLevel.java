package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Gdx;

import java.util.Random;

/**
 * Created by hasbrouckr on 6/17/2016.
 * Default level will generate random enemies and
 * power up location.  Use default level after all other levels
 * are used to continue game forever.
 *
 */
public class DefaultLevel extends GenericLevel {

    public DefaultLevel(){
        super();
        generateEnemies();
        generatePowerUps();
    }

    @Override
    void generateEnemies() {

        Random rand = new Random();
        int iteration = rand.nextInt(20 - 5) + 5;

        for(int i = 0; i < iteration; i++){
            int randX = rand.nextInt(10 - 1) + 1;
            int randY = rand.nextInt(350 - 50) + 50;
            enemies.add(new Enemy(800 * randX, randY));
        }

        enemies.add(new Enemy(800 * 3, 300));
    }

    @Override
    void generatePowerUps() {
        //generate 1 power up randomly in world
        Random rand = new Random();
        int powerX = rand.nextInt((int)World.WORLD_WIDTH - 850) + 850;
        int powerY = rand.nextInt((int)World.WORLD_HEIGHT - 80) + 80;
        Gdx.app.log("POWER UP Creation",  powerX+ " " + powerY);
        powerUps.add(new PowerUps(powerX, powerY));
    }
}

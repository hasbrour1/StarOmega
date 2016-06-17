package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Gdx;

import java.util.Random;

/**
 * Created by hasbrouckr on 6/17/2016.
 */
public class LevelTwo extends GenericLevel {

    public LevelTwo(){
        super();
        generateEnemies();
        generatePowerUps();
    }

    @Override
    void generateEnemies() {
        enemies.add(new Enemy(800, 200));
        enemies.add(new Enemy(800 * 2 + 56, 50));
        enemies.add(new Enemy(830 * 2, 320));
        enemies.add(new Enemy(800 * 3 + 100, 100));
        enemies.add(new Enemy(800 * 3, 300));
        enemies.add(new Enemy(800 * 4 + 200, 100));
        enemies.add(new Enemy(800 * 4, 300));
        enemies.add(new Enemy(800 * 4, 100));
        enemies.add(new Enemy(800 * 4, 50));
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

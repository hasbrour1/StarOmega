package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Gdx;

import java.util.Random;

/**
 * Created by hasbrouckr on 6/22/2016.
 */
public class LevelThree extends GenericLevel {

    @Override
    void generateEnemies() {
        enemies.add(new Enemy(800, 100));
        enemies.add(new Enemy(850, 75));
        enemies.add(new Enemy(860, 130));
        enemies.add(new Enemy(800 * 2, 50));
        enemies.add(new Enemy(830 * 2, 200));
        enemies.add(new Enemy(800 * 2 + 80, 150));
        enemies.add(new Enemy(830 * 2 + 15, 300));
        enemies.add(new Enemy(830 * 2 + 90, 100));
        enemies.add(new Enemy(800 * 3 + 100, 100));
        enemies.add(new Enemy(800 * 3, 300));
        enemies.add(new Enemy(800 * 3 + 100, 100));
        enemies.add(new Enemy(800 * 3 + 50, 175));
        enemies.add(new Enemy(800 * 4, 300));
        enemies.add(new Enemy(800 * 4 , 100));
        enemies.add(new Enemy(800 * 4 , 300));
        enemies.add(new Enemy(800 * 4 + 100, 100));
        enemies.add(new Enemy(800 * 4 + 50, 175));
        enemies.add(new Enemy(800 * 4 + 100, 100));
        enemies.add(new Enemy(800 * 4 + 50, 175));
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

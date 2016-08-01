package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Gdx;

import java.util.Random;

/**
 * Created by hasbrouckr on 6/17/2016.
 */
public class LevelOne extends GenericLevel {

    @Override
    void generateEnemies() {
        enemies.add(new Enemy(810, 130));
        enemies.add(new Enemy(860, 220));
        enemies.add(new Enemy(800 * 2, 50));
        enemies.add(new Enemy(800 * 2 - 80, 200));
        enemies.add(new Enemy(800 * 3 + 75,300));
        enemies.add(new Enemy(800 * 3 + 50, 175));
    }

    @Override
    void generateBoss() {
        boss = new FirstBoss(800 * 3 + 300, 50);
    }
}

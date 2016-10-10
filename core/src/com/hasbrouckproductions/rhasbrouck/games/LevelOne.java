package com.hasbrouckproductions.rhasbrouck.games;

/**
 * Created by hasbrouckr on 6/17/2016.
 */
public class LevelOne extends GenericLevel {

    @Override
    void generateEnemies() {
        enemies.add(new Enemy(810, 130));
        enemies.add(new Enemy(860, 220));
        enemies.add(new Enemy(930, 30));
        enemies.add(new Enemy(1020, 110));
        enemies.add(new Enemy(800 * 2, 50));
        enemies.add(new Enemy(800 * 2 - 200, 200));
        enemies.add(new Enemy(800 * 2 + 75,300));
        enemies.add(new Enemy(800 * 2 + 50, 175));
    }

    @Override
    void generateBoss() {
        boss = new BossOne(800 * 2 + 300, 50);
    }
}

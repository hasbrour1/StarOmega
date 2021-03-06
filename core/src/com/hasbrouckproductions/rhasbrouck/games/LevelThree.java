package com.hasbrouckproductions.rhasbrouck.games;

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
        enemies.add(new Enemy(830 * 2 + 90, 100));
        enemies.add(new Enemy(800 * 3 + 100, 100));
        enemies.add(new Enemy(800 * 3, 300));
        enemies.add(new Enemy(800 * 3 + 50, 175));
        enemies.add(new Enemy(800 * 4, 300));
        enemies.add(new Enemy(800 * 4 , 100));
        enemies.add(new Enemy(800 * 4 + 100, 150));
        enemies.add(new Enemy(800 * 4 + 350, 300));
        enemies.add(new Enemy(800 * 4 + 100, 75));
        enemies.add(new Enemy(800 * 4 + 50, 175));
    }

    @Override
    void generateBoss() {
        boss = new BossThree(800 * 4 + 600, 150);
    }
}

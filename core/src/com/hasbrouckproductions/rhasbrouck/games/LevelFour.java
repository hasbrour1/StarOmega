package com.hasbrouckproductions.rhasbrouck.games;

/**
 * Created by hasbrouckr on 6/28/2016.
 */
public class LevelFour extends GenericLevel {

    @Override
    void generateEnemies() {
        enemies.add(new Enemy(870, 100));
        enemies.add(new Enemy(850, 10));
        enemies.add(new Enemy(860, 210));
        enemies.add(new Enemy(800 * 2, 300));
        enemies.add(new Enemy(830 * 2 + 220, 310));
        enemies.add(new Enemy(800 * 2 + 80, 210));
        enemies.add(new Enemy(830 * 2 + 15, 150));
        enemies.add(new Enemy(830 * 2 + 10, 10));
        enemies.add(new Enemy(800 * 3 + 110, 100));
        enemies.add(new Enemy(800 * 3, 300));
        enemies.add(new Enemy(800 * 3 + 100, 100));
        enemies.add(new Enemy(800 * 3 + 100, 175));
        enemies.add(new Enemy(800 * 4 + 55, 300));
        enemies.add(new Enemy(800 * 4 + 10, 100));
        enemies.add(new Enemy(800 * 4 , 35));
        enemies.add(new Enemy(800 * 4 + 100, 150));
        enemies.add(new Enemy(800 * 4 + 350, 300));
        enemies.add(new Enemy(800 * 4 + 560, 300));
        enemies.add(new Enemy(800 * 4 + 100, 100));
        enemies.add(new Enemy(800 * 4 + 50, 175));
        enemies.add(new Enemy(800 * 4 + 600, 100));
        enemies.add(new Enemy(800 * 5 + 100, 100));
        enemies.add(new Enemy(800 * 5 + 50, 175));
        enemies.add(new Enemy(800 * 5 + 100, 290));
        enemies.add(new Enemy(800 * 5 , 10));
    }

    @Override
    void generateBoss() {
        boss = new BossFour(800 * 6, 100);
    }
}

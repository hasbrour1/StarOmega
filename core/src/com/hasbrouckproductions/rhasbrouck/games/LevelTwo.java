package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Gdx;

import java.util.Random;

/**
 * Created by hasbrouckr on 6/17/2016.
 */
public class LevelTwo extends GenericLevel {

    @Override
    void generateEnemies() {
        enemies.add(new Enemy(850, 75));
        enemies.add(new Enemy(860, 130));
        enemies.add(new Enemy(800 * 2, 50));
        enemies.add(new Enemy(830 * 2, 200));
        enemies.add(new Enemy(800 * 2 + 80, 150));
        enemies.add(new Enemy(830 * 2 + 90, 100));
        enemies.add(new Enemy(800 * 3 - 100, 100));
        enemies.add(new Enemy(800 * 3, 300));
        enemies.add(new Enemy(800 * 3 + 50, 175));
    }

    @Override
    void generateBoss() {
        boss = new SecondBoss(800 * 3 + 400, 50);
    }
}

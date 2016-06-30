package com.hasbrouckproductions.rhasbrouck.games;

/**
 * Created by hasbrouckr on 6/30/2016.
 *
 * Level for testing purposes.  Will not be in finale game.
 *
 */
public class TestLevel extends GenericLevel {

    @Override
    void generateEnemies() {
    }

    @Override
    void generateBoss() {
        boss = new SecondBoss(800, 50);
    }
}

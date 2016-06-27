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

    int max = 0;

    @Override
    void generateEnemies() {

        Random rand = new Random();
        int iteration = rand.nextInt(30 - 10) + 10;

        for(int i = 0; i < iteration; i++){
            int randX = rand.nextInt(10 - 1) + 1;
            int randY = rand.nextInt(350 - 50) + 50;
            enemies.add(new Enemy(800 * randX, randY));
            if(randX > max) max = randX;
        }
    }

    @Override
    void generateBoss() {
        boss = new FirstBoss(800 * max + 350, 150);
    }
}

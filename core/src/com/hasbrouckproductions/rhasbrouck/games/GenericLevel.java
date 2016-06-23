package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by hasbrouckr on 6/17/2016.
 * Abstract Class for Levels
 *
 */
public abstract class GenericLevel{

    public ArrayList<Enemy> enemies;
    public ArrayList<PowerUps> powerUps;
    public Boss boss;

    public GenericLevel(){
        enemies = new ArrayList<Enemy>();
        powerUps = new ArrayList<PowerUps>();
        generateEnemies();
        generatePowerUps();
        generateBoss();
    }

    abstract void generateEnemies();

    abstract void generateBoss();

    private void generatePowerUps(){
        //generate 1 power up randomly in world
        Random rand = new Random();
        int powerX = rand.nextInt((int)World.WORLD_WIDTH - 850) + 850;
        int powerY = rand.nextInt((int)World.WORLD_HEIGHT - 80) + 80;
        Gdx.app.log("POWER UP Creation",  powerX+ " " + powerY);
        powerUps.add(new PowerUps(powerX, powerY));
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<PowerUps> getPowerUps() {
        return powerUps;
    }

    public Boss getBoss(){
        return boss;
    }
}

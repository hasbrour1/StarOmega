package com.hasbrouckproductions.rhasbrouck.games;

import java.util.ArrayList;

/**
 * Created by hasbrouckr on 6/17/2016.
 * Abstract Class for Levels
 *
 */
public abstract class GenericLevel{

    public ArrayList<Enemy> enemies;
    public ArrayList<PowerUps> powerUps;

    public GenericLevel(){
        enemies = new ArrayList<Enemy>();
        powerUps = new ArrayList<PowerUps>();
    }

    abstract void generateEnemies();

    abstract void generatePowerUps();

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<PowerUps> getPowerUps() {
        return powerUps;
    }
}

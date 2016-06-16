package com.hasbrouckproductions.rhasbrouck.games;

/**
 * Created by hasbrouckr on 6/10/2016.
 * Generates level and objects
 * passes values to WorldRender to render
 * every object on screen.  Generates level and
 * updates objects positions.
 *
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

public class World {
    public interface WorldListener {
        public void shoot();

        public void hit();

        public void power();
    }

    public static final float WORLD_WIDTH = 800*4;
    public static final float WORLD_HEIGHT = 350;
    public static final int WORLD_STATE_RUNNING = 0;
    public static final int WORLD_STATE_NEXT_LEVEL = 1;
    public static final int WORLD_STATE_GAME_OVER = 2;

    public final MainShip ship;
    public final ArrayList<Enemy> enemies;
    public final ArrayList<PowerUps> powerUps;
    public final ArrayList<MainLaser> mainShipLasers;
    public final WorldListener listener;
    public final Random rand;

    public int score;
    public int state;

    public World(WorldListener listener) {
        this.ship = new MainShip(5, 1);
        this.enemies = new ArrayList<Enemy>();
        this.powerUps = new ArrayList<PowerUps>();
        this.mainShipLasers = new ArrayList<MainLaser>();
        this.listener = listener;
        rand = new Random();
        generateLevel();

        this.score = 0;
        this.state = WORLD_STATE_RUNNING;
    }

    private void generateLevel() {

        //For now create 5 enemies and 1 power up
        enemies.add(new Enemy(800, 100));
        enemies.add(new Enemy(800 * 2, 50));
        enemies.add(new Enemy(830 * 2, 200));
        enemies.add(new Enemy(800 * 3 + 100, 100));
        enemies.add(new Enemy(800 * 3, 300));

        //generate 1 power up randomly in world
        Random rand = new Random();
        int powerX = rand.nextInt((int)WORLD_WIDTH - 850) + 850;
        int powerY = rand.nextInt((int)WORLD_HEIGHT - 80) + 80;
        Gdx.app.log("POWER UP Creation",  powerX+ " " + powerY);
        powerUps.add(new PowerUps(powerX, powerY));
    }

    public void update(float deltaTime) {
        updateShip(deltaTime);
        updateEnemies(deltaTime);
        updatePowerUps(deltaTime);
        if (ship.state != ship.SHIP_STATE_HIT) checkCollisions();
        checkGameOver();
    }

    private void updateShip(float deltaTime){
        if (ship.state != ship.SHIP_STATE_HIT) {
            ship.update(deltaTime, ship.xPos, ship.yPos);
        }
    }

    public void updateShip(float deltaTime, float x, float y) {
        if (ship.state != ship.SHIP_STATE_HIT) {
            ship.update(deltaTime, x, y);
        }
    }

    public void updateMainShipFire(float deltaTime){
        //fire laser after cool down time if ship is moving
        if(TimeUtils.nanoTime() - MainLaser.lastFireTime > 1000000000 / 2){
            mainShipLasers.add(new MainLaser(ship.xPos, ship.yPos));
        }

        int len = mainShipLasers.size();
        for (int i = 0; i < len; i++) {
            MainLaser laser = mainShipLasers.get(i);
            laser.update();
            if(laser.state == MainLaser.LASER_HIT){
                mainShipLasers.remove(i);
                len --;
            }
        }
    }

    private void updateEnemies(float deltaTime) {
        int len = enemies.size();
        for (int i = 0; i < len; i++) {
            Enemy enemy = enemies.get(i);
            enemy.update(deltaTime);
            if(enemy.state == Enemy.ENEMY_IS_DEAD){
                enemies.remove(i);
                len --;
            }
        }
    }

    private void updatePowerUps(float deltaTime) {
        int len = powerUps.size();
        for (int i = 0; i < len; i++) {
            Gdx.app.log("POWER UP ",  "Updating Power");
            PowerUps power = powerUps.get(i);
            power.update(deltaTime);
        }
    }

    //Check main ship collisions with each object
    private void checkCollisions() {
        checkPowerUpCollisions();
        checkEnemyCollisions();
        checkMainLaserCollisions();
        checkEnemyLaserCollisions();
    }

    private void checkMainLaserCollisions(){
        //Check if laser hits enemy
        int lasLen = mainShipLasers.size();
        int enLen = enemies.size();

        for(int i = 0; i < lasLen; i++){
            MainLaser laser = mainShipLasers.get(i);
            for(int j = 0; j < enLen; j++){
                Enemy enemy = enemies.get(j);
                if(laser.bounds.overlaps(enemy.bounds)){
                    //Remove laser and Enemy ship if hp = 0
                    listener.hit();
                    laser.hitEnemy();
                    enemy.hit();
                }
            }
        }
    }

    private void checkEnemyLaserCollisions(){

    }

    //If collide with powerup, give ship power and remove power orb
    private void checkPowerUpCollisions() {
        int len = powerUps.size();
        for (int i = 0; i < len; i++) {
            PowerUps power = powerUps.get(i);
            if (power.bounds.overlaps(ship.bounds)) {
                powerUps.remove(i);
                ship.gotPower();
                listener.power();
            }
        }
    }

    private void checkEnemyCollisions() {
        int len = enemies.size();
        for (int i = 0; i < len; i++) {
            Enemy enemy = enemies.get(i);
            if (ship.bounds.overlaps(enemy.bounds)) {
                ship.gotHit();
            }
        }
    }

    private void checkGameOver() {
        if (ship.state == MainShip.SHIP_DEAD) {
            state = WORLD_STATE_GAME_OVER;
        }
    }
}

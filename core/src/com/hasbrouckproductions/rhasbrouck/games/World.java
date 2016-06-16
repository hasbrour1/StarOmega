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

    //Update each object
    public void update(float deltaTime) {
        updateShip(deltaTime);
        updateEnemies(deltaTime);
        updatePowerUps(deltaTime);
        updateMainShipFire(deltaTime);
        if (ship.state != ship.SHIP_STATE_HIT) checkCollisions();
        checkGameOver();
    }

    //Updates ship position if not touched
    private void updateShip(float deltaTime){
        if (ship.state != ship.SHIP_STATE_HIT) {
            ship.update(deltaTime, ship.xPos, ship.yPos);
        }
    }

    //update ship position if touched
    public void updateShip(float deltaTime, float x, float y) {
        if (ship.state != ship.SHIP_STATE_HIT) {
            ship.update(deltaTime, x, y);
        }
    }

    //Shoot laser after a second sense last laser
    public void addMainShipFire(){
        //fire laser after cool down time if ship is moving
        if(ship.state == MainShip.SHIP_STATE_POWERUP){
            if(TimeUtils.nanoTime() - MainLaser.lastFireTime > 1000000000 / 2){
                mainShipLasers.add(new MainLaser(ship.xPos + 100, ship.yPos + 55));
                mainShipLasers.add(new MainLaser(ship.xPos + 100, ship.yPos + 35));
            }
        }else{
            if(TimeUtils.nanoTime() - MainLaser.lastFireTime > 1000000000 / 2){
                mainShipLasers.add(new MainLaser(ship.xPos + 100, ship.yPos + 45));
            }
        }
    }

    //update laser position
    public void updateMainShipFire(float deltaTime){
        int len = mainShipLasers.size();
        for (int i = 0; i < len; i++) {
            MainLaser laser = mainShipLasers.get(i);
            laser.update();
            if(laser.state == MainLaser.LASER_HIT || laser.xPos >= 800){
                mainShipLasers.remove(i);
                len --;
            }
        }
    }

    //update enemy position
    private void updateEnemies(float deltaTime) {
        int len = enemies.size();
        for (int i = 0; i < len; i++) {
            Enemy enemy = enemies.get(i);
            enemy.update(deltaTime);
            if(enemy.state == Enemy.ENEMY_IS_DEAD || enemy.xPos <= -50){
                enemies.remove(i);
                len --;
            }
        }
    }

    //update powerup position
    private void updatePowerUps(float deltaTime) {
        int len = powerUps.size();
        for (int i = 0; i < len; i++) {
            Gdx.app.log("POWER UP ",  "Updating Power");
            PowerUps power = powerUps.get(i);
            power.update(deltaTime);

            if(power.xPos <= -50){
                powerUps.remove(i);
                len--;
            }
        }
    }

    //Check main ship collisions with each object
    private void checkCollisions() {
        checkPowerUpCollisions();
        checkEnemyCollisions();
        checkMainLaserCollisions();
        checkEnemyLaserCollisions();
    }

    //check if ship laser hits any enemies
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

    //Check if enemy lasers hit main ship
    private void checkEnemyLaserCollisions(){

    }

    //If collide with powerup, give ship power and remove power orb
    private void checkPowerUpCollisions() {
        int len = powerUps.size();
        for (int i = 0; i < len; i++) {
            PowerUps power = powerUps.get(i);
            if (power.bounds.overlaps(ship.bounds)) {
                powerUps.remove(i);
                len--;
                ship.gotPower();
                listener.power();
            }
        }
    }

    //if collide with enemy ship gets hit
    private void checkEnemyCollisions() {
        int len = enemies.size();
        for (int i = 0; i < len; i++) {
            Enemy enemy = enemies.get(i);
            if (ship.bounds.overlaps(enemy.bounds)) {
                ship.gotHit();
            }
        }
    }

    //Checks if game is over
    private void checkGameOver() {
        if (ship.state == MainShip.SHIP_DEAD) {
            state = WORLD_STATE_GAME_OVER;
        }
    }
}

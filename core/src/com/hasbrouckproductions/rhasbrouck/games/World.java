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

    public ArrayList<Enemy> enemies;
    public Boss boss;
    public ArrayList<PowerUps> powerUps;
    public final ArrayList<MainLaser> mainShipLasers;
    public final ArrayList<EnemyMainFire> enemyShipLasers;
    public final WorldListener listener;
    public final Random rand;

    public static MainShip ship;

    public int score;
    public int state;

    private GenericLevel level;

    public World(WorldListener listener, int currentLevel, MainShip mainShip) {
        this.ship = mainShip;
        this.enemies = new ArrayList<Enemy>();
        this.powerUps = new ArrayList<PowerUps>();
        this.mainShipLasers = new ArrayList<MainLaser>();
        this.enemyShipLasers = new ArrayList<EnemyMainFire>();
        this.listener = listener;
        rand = new Random();
        generateLevel(currentLevel);

        this.score = 0;
        this.state = WORLD_STATE_RUNNING;
    }


    //Generate level enemies and powerups according to
    //currentLevel
    private void generateLevel(int currentLevel) {
        switch (currentLevel){
            case 1:
                //level = new TestLevel();
                level = new LevelOne();
                levelBuild();
                break;
            case 2:
                level = new LevelTwo();
                levelBuild();
                break;
            case 3:
                level = new LevelThree();
                levelBuild();
                break;
            case 4:
                level = new LevelFour();
                levelBuild();
                break;
            case 5:
                level = new LevelFive();
                levelBuild();
                break;
            default:
                level = new DefaultLevel();
                levelBuild();
                break;
        }
    }

    //Assign current enemies and powerUps
    private void levelBuild(){
        enemies = level.getEnemies();
        powerUps = level.getPowerUps();
        boss = level.getBoss();
    }

    //Update each object
    public void update(float deltaTime) {
        updateShip(deltaTime);
        updateEnemies(deltaTime);
        updatePowerUps(deltaTime);
        updateMainShipFire(deltaTime);
        updateBoss(deltaTime);
        updateEnemyFire();
        if (ship.state != ship.SHIP_DEAD) checkCollisions();
        checkGameOver();
    }

    //Updates ship position if not touched
    private void updateShip(float deltaTime){
        if (ship.state != ship.SHIP_DEAD) {
            ship.update(deltaTime, ship.xPos, ship.yPos);
        }
    }

    //update ship position if touched
    public void updateShip(float deltaTime, float x, float y) {
        if (ship.state != ship.SHIP_DEAD) {
            ship.update(deltaTime, x, y);
        }
    }

    //Shoot laser after a second sense last laser
    public void addMainShipFire(){
        //fire laser after cool down time if ship is moving
        if(ship.state == MainShip.SHIP_STATE_POWERUP){
            if(TimeUtils.nanoTime() - MainLaser.lastFireTime > 1000000000 / 2){
                listener.shoot();
                mainShipLasers.add(new MainLaser(ship.xPos + 100, ship.yPos + 55));
                mainShipLasers.add(new MainLaser(ship.xPos + 100, ship.yPos + 35));
            }
        }else{
            if(TimeUtils.nanoTime() - MainLaser.lastFireTime > 1000000000 / 2){
                listener.shoot();
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

    public void updateEnemyFire(){
        //Check to add enemy Laser
        for(Enemy enemy: enemies) {
            if(enemy.xPos < 800) {
                if (TimeUtils.nanoTime() - enemy.lastFireTime > 1000000000 * 2) {
                    listener.shoot();
                    enemy.updateFireTime();
                    enemyShipLasers.add(new EnemyMainFire(enemy.xPos - 100, enemy.yPos + 35));
                }
            }
        }

        //Update Enemy Laser Positions
        for(EnemyMainFire laser : enemyShipLasers){
            laser.update();
        }
    }

    //update enemy position
    private void updateEnemies(float deltaTime) {
        int len = enemies.size();
        for (int i = 0; i < len; i++) {
            Enemy enemy = enemies.get(i);
            enemy.update(deltaTime);
            if(enemy.state == Enemy.ENEMY_IS_DEAD){
                score += 10;
                enemies.remove(i);
                len --;
            }
            if(enemy.xPos <= -50){
                enemies.remove(i);
                len --;
            }
        }
    }

    //update powerup position
    private void updatePowerUps(float deltaTime) {
        int len = powerUps.size();
        for (int i = 0; i < len; i++) {
            PowerUps power = powerUps.get(i);
            power.update(deltaTime);

            if(power.xPos <= -50){
                powerUps.remove(i);
                len--;
            }
        }
    }

    private void updateBoss(float deltaTime){
        boss.update(deltaTime, this);
    }

    //Check main ship collisions with each object
    private void checkCollisions() {
        checkPowerUpCollisions();
        checkEnemyCollisions();
        checkMainLaserCollisions();
        checkEnemyLaserCollisions();
        checkBossLaserCollisions();
        checkBossCollisions();
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

            if(laser.bounds.overlaps(boss.bounds)){
                listener.hit();
                laser.hitEnemy();
                boss.hit();
            }
        }
    }

    //Check if enemy lasers hit main ship
    private void checkEnemyLaserCollisions(){
        int len = enemyShipLasers.size();
        for(int i = 0; i < len; i++){
            EnemyMainFire laser = enemyShipLasers.get(i);
            if(laser.bounds.overlaps(ship.bounds)){
                enemyShipLasers.remove(i);
                len--;
                ship.gotHit();
                listener.hit();
            }
        }
    }

    private void checkBossLaserCollisions(){
        int len = boss.bossLasers.size();
        for(int i = 0; i < len; i++){
            EnemyMainFire laser = boss.bossLasers.get(i);
            if(laser.bounds.overlaps(ship.bounds)){
                boss.bossLasers.remove(i);
                len--;
                ship.gotHit();
                listener.hit();
            }
        }

        len = boss.bossBeams.size();
        for(int i = 0; i < len; i++){
            EnemyBeam beam = boss.bossBeams.get(i);
            if(beam.bounds.overlaps(ship.bounds)){
                ship.gotHit();
                listener.hit();
            }
        }
    }

    public void checkBossCollisions(){
        if(boss.bounds.overlaps(ship.bounds)){
            ship.gotHit();
            listener.hit();
        }
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
                listener.hit();
                //TODO: show explosion animation
                enemies.remove(i);
                len--;
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

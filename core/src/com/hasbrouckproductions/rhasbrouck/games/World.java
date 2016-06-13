package com.hasbrouckproductions.rhasbrouck.games;

/**
 * Created by hasbrouckr on 6/10/2016.
 * Generates level and objects
 * passes values to WorldRender to render
 * every object on screen
 *
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;

public class World {
    public interface WorldListener {
        public void shoot();

        public void hit();
    }

    public static final float WORLD_WIDTH = 10;
    public static final float WORLD_HEIGHT = 15 * 20;
    public static final int WORLD_STATE_RUNNING = 0;
    public static final int WORLD_STATE_NEXT_LEVEL = 1;
    public static final int WORLD_STATE_GAME_OVER = 2;

    public final MainShip ship;
    public final List<Enemy> enemies;
    public final List<PowerUps> powerUps;
    public final WorldListener listener;
    public final Random rand;

    public int score;
    public int state;

    public World(WorldListener listener) {
        this.ship = new MainShip(5, 1);
        this.enemies = new ArrayList<Enemy>();
        this.powerUps = new ArrayList<PowerUps>();
        this.listener = listener;
        rand = new Random();
        generateLevel();

        this.score = 0;
        this.state = WORLD_STATE_RUNNING;
    }

    private void generateLevel() {

        //For now create 5 enemies and 1 power up
        enemies.add(new Enemy(100, 100));
        enemies.add(new Enemy(200, 50));
        enemies.add(new Enemy(300, 200));
        enemies.add(new Enemy(400, 100));
        enemies.add(new Enemy(500, 150));

        powerUps.add(new PowerUps(200, 650));


    }

    public void update(float deltaTime, float x, float y) {
        updateShip(deltaTime, x, y);
        updateEnemies(deltaTime);
        updatePowerUps(deltaTime);
        if (ship.state != ship.SHIP_STATE_HIT) checkCollisions();
        checkGameOver();
    }

    private void updateShip(float deltaTime, float x, float y) {
        if (ship.state != ship.SHIP_STATE_HIT) {
            ship.xPos = x;
            ship.yPos = y;
        }
        ship.update(deltaTime);
    }

    private void updateEnemies(float deltaTime) {
        int len = enemies.size();
        for (int i = 0; i < len; i++) {
            Enemy enemy = enemies.get(i);
            enemy.update(deltaTime);
        }
    }

    private void updatePowerUps(float deltaTime) {
        int len = powerUps.size();
        for (int i = 0; i < len; i++) {
            PowerUps power = powerUps.get(i);
            power.update(deltaTime);
        }
    }

    private void checkCollisions() {
        checkPowerUpCollisions();
        checkEnemyCollisions();
    }

    private void checkPowerUpCollisions() {
        int len = powerUps.size();
        for (int i = 0; i < len; i++) {
            PowerUps power = powerUps.get(i);
            if (power.bounds.overlaps(ship.bounds)) {
                ship.gotPower();
                listener.hit();
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

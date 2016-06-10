package com.hasbrouckproductions.rhasbrouck.games;

/**
 * Created by hasbrouckr on 6/10/2016.
 * Generates level and goes through it
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;

public class World {
    public interface WorldListener {
        public void shoot ();

        public void hit ();
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

    public World (WorldListener listener) {
        this.ship = new MainShip(5, 1);
        this.enemies = new ArrayList<Enemy>();
        this.powerUps = new ArrayList<PowerUps>();
        this.listener = listener;
        rand = new Random();
        generateLevel();

        this.score = 0;
        this.state = WORLD_STATE_RUNNING;
    }

    private void generateLevel () {
        float y = Platform.PLATFORM_HEIGHT / 2;
        float maxJumpHeight = Bob.BOB_JUMP_VELOCITY * Bob.BOB_JUMP_VELOCITY / (2 * -gravity.y);
        while (y < WORLD_HEIGHT - WORLD_WIDTH / 2) {
            int type = rand.nextFloat() > 0.8f ? Platform.PLATFORM_TYPE_MOVING : Platform.PLATFORM_TYPE_STATIC;
            float x = rand.nextFloat() * (WORLD_WIDTH - Platform.PLATFORM_WIDTH) + Platform.PLATFORM_WIDTH / 2;

            Platform platform = new Platform(type, x, y);
            platforms.add(platform);

            if (rand.nextFloat() > 0.9f && type != Platform.PLATFORM_TYPE_MOVING) {
                Spring spring = new Spring(platform.position.x, platform.position.y + Platform.PLATFORM_HEIGHT / 2
                        + Spring.SPRING_HEIGHT / 2);
                springs.add(spring);
            }

            if (y > WORLD_HEIGHT / 3 && rand.nextFloat() > 0.8f) {
                Squirrel squirrel = new Squirrel(platform.position.x + rand.nextFloat(), platform.position.y
                        + Squirrel.SQUIRREL_HEIGHT + rand.nextFloat() * 2);
                squirrels.add(squirrel);
            }

            if (rand.nextFloat() > 0.6f) {
                Coin coin = new Coin(platform.position.x + rand.nextFloat(), platform.position.y + Coin.COIN_HEIGHT
                        + rand.nextFloat() * 3);
                coins.add(coin);
            }

            y += (maxJumpHeight - 0.5f);
            y -= rand.nextFloat() * (maxJumpHeight / 3);
        }

        castle = new Castle(WORLD_WIDTH / 2, y);
    }

    public void update (float deltaTime, float accelX) {
        updateShip(deltaTime, accelX);
        updateEnemies(deltaTime);
        updatePowerUps(deltaTime);
        if (ship.state != ship.SHIP_STATE_HIT) checkCollisions();
        checkGameOver();
    }

    private void updateShip (float deltaTime, float accelX) {
        if (ship.state != ship.SHIP_STATE_HIT) ship.velocity.x = -accelX / 10 * ship.SHIP_MOVE_VELOCITY;
        ship.update(deltaTime);
    }

    private void updateEnemies (float deltaTime) {
        int len = squirrels.size();
        for (int i = 0; i < len; i++) {
            Squirrel squirrel = squirrels.get(i);
            squirrel.update(deltaTime);
        }
    }

    private void updatePowerUps (float deltaTime) {
        int len = coins.size();
        for (int i = 0; i < len; i++) {
            Coin coin = coins.get(i);
            coin.update(deltaTime);
        }
    }

    private void checkCollisions () {
        checkSquirrelCollisions();
        checkItemCollisions();
    }

    private void checkSquirrelCollisions () {
        int len = squirrels.size();
        for (int i = 0; i < len; i++) {
            Squirrel squirrel = squirrels.get(i);
            if (squirrel.bounds.overlaps(bob.bounds)) {
                bob.hitSquirrel();
                listener.hit();
            }
        }
    }

    private void checkItemCollisions () {
        int len = coins.size();
        for (int i = 0; i < len; i++) {
            Coin coin = coins.get(i);
            if (bob.bounds.overlaps(coin.bounds)) {
                coins.remove(coin);
                len = coins.size();
                listener.coin();
                score += Coin.COIN_SCORE;
            }

        }

        if (bob.velocity.y > 0) return;

        len = springs.size();
        for (int i = 0; i < len; i++) {
            Spring spring = springs.get(i);
            if (bob.position.y > spring.position.y) {
                if (bob.bounds.overlaps(spring.bounds)) {
                    bob.hitSpring();
                    listener.highJump();
                }
            }
        }
    }

    private void checkCastleCollisions () {
        if (castle.bounds.overlaps(bob.bounds)) {
            state = WORLD_STATE_NEXT_LEVEL;
        }
    }

    private void checkGameOver () {
        if (heightSoFar - 7.5f > bob.position.y) {
            state = WORLD_STATE_GAME_OVER;
        }
    }

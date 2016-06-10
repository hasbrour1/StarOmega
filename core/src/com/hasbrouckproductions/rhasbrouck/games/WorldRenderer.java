package com.hasbrouckproductions.rhasbrouck.games;

/**
 * Created by hasbrouckr on 6/10/2016.
 *
 * Renders the objects present from World Class
 *
 */
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class WorldRenderer {
    static final float FRUSTUM_WIDTH = 10;
    static final float FRUSTUM_HEIGHT = 15;
    World world;
    OrthographicCamera cam;
    SpriteBatch batch;

    public WorldRenderer (SpriteBatch batch, World world) {
        this.world = world;
        this.cam = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        this.cam.position.set(FRUSTUM_WIDTH / 2, FRUSTUM_HEIGHT / 2, 0);
        this.batch = batch;
    }

    public void render () {
        if (world.ship.position.y > cam.position.y) cam.position.y = world.ship.position.y;
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        renderBackground();
        renderObjects();
    }

    public void renderBackground () {
        batch.disableBlending();
        batch.begin();
        batch.draw(Assets.backgroundRegion, cam.position.x - FRUSTUM_WIDTH / 2, cam.position.y - FRUSTUM_HEIGHT / 2, FRUSTUM_WIDTH,
                FRUSTUM_HEIGHT);
        batch.end();
    }

    public void renderObjects () {
        batch.enableBlending();
        batch.begin();
        renderShip();
        renderEnemies();
        renderPowerUps();
        batch.end();
    }

    //render the ship depending on ship state
    private void renderShip () {
        TextureRegion keyFrame;
        switch (world.ship.state) {
            case MainShip.SHIP_STATE_ALIVE:
                keyFrame = Assets.mainShip;
                break;
            case MainShip.SHIP_DEAD:
                keyFrame = Assets.explosion;
                break;
            case MainShip.SHIP_STATE_HIT:
                keyFrame = Assets.shipHit;
                break;
            default:
                keyFrame = Assets.shipHit;
        }
    }

    private void renderEnemies () {
        int len = world.enemies.size();
        for (int i = 0; i < len; i++) {
            Enemy enemy = world.enemies.get(i);
            TextureRegion keyFrame = Assets.enemyShip;
            if (enemy.state == Enemy.ENEMY_IS_ALIVE) {
                //use enemy sprite
                //keyFrame = Assets.brakingPlatform.getKeyFrame(platform.stateTime, Animation.ANIMATION_NONLOOPING);
            }

            batch.draw(keyFrame, enemy.position.x - 1, enemy.position.y - 0.25f, 2, 0.5f);
        }
    }

    private void renderPowerUps () {
        int len = world.powerUps.size();
        for (int i = 0; i < len; i++) {
            PowerUps power = world.powerUps.get(i);
            batch.draw(Assets.powerUp, power.position.x - 0.5f, power.position.y - 0.5f, 1, 1);
        }
    }
}
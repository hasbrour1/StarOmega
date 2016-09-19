package com.hasbrouckproductions.rhasbrouck.games;

/**
 * Created by hasbrouckr on 6/10/2016.
 *
 * Game screen for playing.  Uses World
 * and WorldRenderer classes to maintain
 * objects
 *
 */
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.hasbrouckproductions.rhasbrouck.games.World.WorldListener;

public class GameScreen extends ScreenAdapter {

    static final int GAME_READY = 0;
    static final int GAME_RUNNING = 1;
    static final int GAME_PAUSED = 2;
    static final int GAME_LEVEL_END = 3;
    static final int GAME_OVER = 4;

    static final int SOUND_STATE_ON = 0;
    static final int SOUND_STATE_OFF = 1;

    StarOmega game;

    int state;
    int soundState;
    int level;
    OrthographicCamera guiCam;
    Vector3 touchPoint;
    World world;
    WorldListener worldListener;
    WorldRenderer renderer;
    Rectangle pauseBounds;
    Rectangle resumeBounds;
    Rectangle quitBounds;
    Rectangle soundBounds;
    int lastScore;
    String scoreString;

    public final MainShip ship;

    GlyphLayout glyphLayout = new GlyphLayout();

    public GameScreen (StarOmega game) {
        this.game = game;
        this.ship = new MainShip(5, 1);

        state = GAME_READY;
        soundState = SOUND_STATE_ON;
        level = 1;
        guiCam = new OrthographicCamera();
        guiCam.setToOrtho(false, 800, 480);
        touchPoint = new Vector3();
        worldListener = new WorldListener() {
            @Override
            public void shoot () {
                Assets.playSound(Assets.shootSound);
            }

            @Override
            public void hit () {
                Assets.playSound(Assets.hitSound);
            }

            @Override
            public void power() {Assets.playSound(Assets.hitSound);}
        };
        world = new World(worldListener, level, ship);
        renderer = new WorldRenderer(game.batch, world);
        pauseBounds = new Rectangle(320 - 64, 480 - 64, 64, 64);
        resumeBounds = new Rectangle(200 - (160/2), 240 - (40/2), 120, 40);
        soundBounds = new Rectangle(750 - (52/2), 420 - (52/2), 52, 52);
        quitBounds = new Rectangle(600 - (160/2), 240 - (40/2), 147, 40);
        lastScore = 0;
        scoreString = "SCORE: 0";
    }

    public void update (float deltaTime) {
        if (deltaTime > 0.1f) deltaTime = 0.1f;

        switch (state) {
            case GAME_READY:
                updateReady();
                break;
            case GAME_RUNNING:
                updateRunning(deltaTime);
                break;
            case GAME_PAUSED:
                updatePaused();
                break;
            case GAME_LEVEL_END:
                updateLevelEnd();
                break;
            case GAME_OVER:
                updateGameOver();
                break;
        }
    }

    private void updateReady () {
        if (Gdx.input.justTouched()) {
            state = GAME_RUNNING;
        }
    }

    private void updateRunning (float deltaTime) {
        if (Gdx.input.justTouched()) {
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (pauseBounds.contains(touchPoint.x, touchPoint.y)) {
                Assets.playSound(Assets.clickSound);
                state = GAME_PAUSED;
                return;
            }
        }

        if(Gdx.input.isTouched()){
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            guiCam.unproject(touchPos);
            //Move Ship
            world.updateShip(deltaTime, touchPos.x - 50, touchPos.y - 50);

            //Shoot Laser
            world.addMainShipFire();
        }

        world.update(deltaTime);

        if (world.score != lastScore) {
            lastScore = world.score;
            scoreString = "SCORE: " + lastScore;
        }
        if (world.state == World.WORLD_STATE_NEXT_LEVEL) {
            game.setScreen(new WinScreen(game));
        }
        if (world.state == World.WORLD_STATE_GAME_OVER) {
            state = GAME_OVER;
            if (Settings.addHighScore(lastScore))
                scoreString = "NEW HIGHSCORE: " + lastScore;
            else
                scoreString = "SCORE: " + lastScore;
            Settings.save();
        }

        //if enemy array is empty then level is complete
        if(world.enemies.isEmpty() && (world.boss.state == Boss.BOSS_IS_DEAD)){
            level++;
            world.score += 100;
            state = GAME_LEVEL_END;
        }
    }

    private void updatePaused () {
        if (Gdx.input.justTouched()) {
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (resumeBounds.contains(touchPoint.x, touchPoint.y)) {
                Assets.playSound(Assets.clickSound);
                state = GAME_RUNNING;
                return;
            }

            if (quitBounds.contains(touchPoint.x, touchPoint.y)) {
                Assets.playSound(Assets.clickSound);
                game.setScreen(new MainMenuScreen(game));
                return;
            }

            if(soundBounds.contains(touchPoint.x, touchPoint.y)){
                //Stop/Start Music
                if(soundState == SOUND_STATE_ON){
                    Assets.gameMusic.pause();
                    soundState = SOUND_STATE_OFF;
                }else{
                    Assets.gameMusic.play();
                    soundState = SOUND_STATE_ON;
                }

            }
        }
    }

    private void updateLevelEnd () {
        if (Gdx.input.justTouched()) {
            world = new World(worldListener, level, ship);
            renderer = new WorldRenderer(game.batch, world);
            world.score = lastScore;
            state = GAME_READY;
        }
    }

    private void updateGameOver () {
        if (Gdx.input.justTouched()) {
            game.setScreen(new MainMenuScreen(game));
        }
    }

    public void draw () {
        GL20 gl = Gdx.gl;
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        guiCam.update();
        game.batch.setProjectionMatrix(guiCam.combined);
        game.batch.enableBlending();
        game.batch.begin();
        switch (state) {
            case GAME_READY:
                presentReady();
                break;
            case GAME_RUNNING:
                presentRunning();
                break;
            case GAME_PAUSED:
                presentPaused();
                break;
            case GAME_LEVEL_END:
                presentLevelEnd();
                break;
            case GAME_OVER:
                presentGameOver();
                break;
        }
        game.batch.end();
    }

    private void presentReady () {
        game.batch.draw(Assets.ready, 320 - 64, 480 - 64, 102, 40);
    }

    private void presentRunning () {
        game.batch.draw(Assets.pause, 320 - 64, 480 - 64, 64, 64);
        Assets.font.draw(game.batch, scoreString, 16, 480 - 20);
    }

    private void presentPaused () {
        game.batch.draw(Assets.quitButton, 600 - (160/2), 240 - (40/2), 147, 40);
        game.batch.draw(Assets.resumeButton, 200 - (160/2), 240 - (40/2), 120, 40);

        //Draw sound icon
        switch(soundState){
            case SOUND_STATE_ON:
                game.batch.draw(Assets.soundOnButton, 750 - (52/2), 420 - (52/2), 52, 52);
                break;
            case SOUND_STATE_OFF:
                game.batch.draw(Assets.soundOffButton, 750 - (52/2), 420 - (52/2), 52, 52);
                break;
            default:
                //Draw nothing
                break;
        }

        Assets.font.draw(game.batch, scoreString, 16, 480 - 20);
    }

    private void presentLevelEnd () {
        Assets.font.draw(game.batch, glyphLayout, 160 - glyphLayout.width / 2, 480 - 40);
        glyphLayout.setText(Assets.font, "Level " + (level - 1) + " Complete!");
        Assets.font.draw(game.batch, glyphLayout, 160 - glyphLayout.width / 2, 40);
    }

    private void presentGameOver () {
        game.batch.draw(Assets.gameOver, 160 - 160 / 2, 240 - 96 / 2, 160, 96);
        glyphLayout.setText(Assets.font, scoreString);
        Assets.font.draw(game.batch, scoreString, 160 - glyphLayout.width / 2, 480 - 20);
    }

    @Override
    public void render (float delta) {
        update(delta);
        draw();
    }

    @Override
    public void pause () {
        if (state == GAME_RUNNING) state = GAME_PAUSED;
    }

}
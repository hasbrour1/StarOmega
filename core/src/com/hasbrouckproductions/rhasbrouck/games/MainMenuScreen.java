package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by hasbrouckr on 6/10/2016.
 * Sets up the main menu screen
 *
 */
public class MainMenuScreen extends ScreenAdapter {
    StarOmega game;
    OrthographicCamera guiCam;
    Rectangle playBounds;
    Rectangle highscoresBounds;
    Rectangle helpBounds;
    Rectangle creditsBounds;
    Vector3 touchPoint;

    public MainMenuScreen (StarOmega game) {
        this.game = game;

        guiCam = new OrthographicCamera();
        guiCam.setToOrtho(false, 800, 480);
        playBounds = new Rectangle(380, 180, 88, 40);
        highscoresBounds = new Rectangle(350, 110, 153, 40);
        helpBounds = new Rectangle(380, 50, 84, 41);
        creditsBounds = new Rectangle(600, 400, 110, 40);
        touchPoint = new Vector3();
    }

    //Handle screen touches
    public void update () {
        if (Gdx.input.justTouched()) {
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (playBounds.contains(touchPoint.x, touchPoint.y)) {
                Assets.playSound(Assets.clickSound);
                game.setScreen(new GameScreen(game));
                return;
            }
            if (highscoresBounds.contains(touchPoint.x, touchPoint.y)) {
                Assets.playSound(Assets.clickSound);
                game.setScreen(new HighscoresScreen(game));
                return;
            }
            if (helpBounds.contains(touchPoint.x, touchPoint.y)) {
                Assets.playSound(Assets.clickSound);
                game.setScreen(new HelpScreen(game));
                return;
            }
        }
    }

    //Render Objects for Main Menu
    public void draw () {
        GL20 gl = Gdx.gl;
        gl.glClearColor(1, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        guiCam.update();
        game.batch.setProjectionMatrix(guiCam.combined);

        game.batch.disableBlending();
        game.batch.begin();
        game.batch.draw(Assets.backgroundRegion, 0, 0, 800, 480);
        game.batch.end();

        game.batch.enableBlending();
        game.batch.begin();
        game.batch.draw(Assets.logo, 300, 260, 240, 200);
        game.batch.draw(Assets.startButton, 380, 180, 88, 40);
        game.batch.draw(Assets.highScoreButton, 350, 110, 153, 40);
        game.batch.draw(Assets.helpButton, 380, 50, 84, 41);
        game.batch.draw(Assets.creditsButton, 600, 400, 110, 40);
        game.batch.end();
    }

    @Override
    public void render (float delta) {
        update();
        draw();
    }

    @Override
    public void pause () {
        Settings.save();
    }
}

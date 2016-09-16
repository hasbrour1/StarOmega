package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by hasbrouckr on 9/15/2016.
 */
public class CreditsScreen extends ScreenAdapter {

    StarOmega game;

    OrthographicCamera guiCam;

    public CreditsScreen (StarOmega game) {
        this.game = game;
        guiCam = new OrthographicCamera();
        guiCam.setToOrtho(false, 800, 480);
    }

    public void update () {
        if (Gdx.input.justTouched()) {
            game.setScreen(new MainMenuScreen(game));
        }
    }

    public void draw () {
        GL20 gl = Gdx.gl;
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        guiCam.update();
        game.batch.setProjectionMatrix(guiCam.combined);
        game.batch.disableBlending();
        game.batch.begin();
        game.batch.draw(Assets.backgroundRegion, 0, 0, 800, 480);
        game.batch.end();

        game.batch.enableBlending();
        game.batch.begin();
        Assets.font.draw(game.batch, "Game Design by: Robert Hasbrouck", 100, 360);
        Assets.font.draw(game.batch, "Music by: Kyle L. Smith", 100, 330);
        Assets.font.draw(game.batch, "Game Sprites from: opengameart.org and MillionthVector", 100, 300);
        Assets.font.draw(game.batch, "Game Sounds from: freesound.org", 100, 270);
        game.batch.end();

    }

    @Override
    public void render (float delta) {
        draw();
        update();
    }
}

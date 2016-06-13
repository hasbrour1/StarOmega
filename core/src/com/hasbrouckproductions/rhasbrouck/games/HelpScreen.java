package com.hasbrouckproductions.rhasbrouck.games;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by hasbrouckr on 6/10/2016.
 */
public class HelpScreen extends ScreenAdapter{
    StarOmega game;

    OrthographicCamera guiCam;
    Texture helpImage;
    TextureRegion helpRegion;

    public HelpScreen (StarOmega game) {
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
        Assets.font.draw(game.batch, "How to play:", 300, 360);
        Assets.font.draw(game.batch, "Touch screen to move ship", 300, 330);
        Assets.font.draw(game.batch, "and fire weapons.  Don't", 300, 300);
        Assets.font.draw(game.batch, "get hit by enemy fire.  Power UPs give", 300, 270);
        Assets.font.draw(game.batch, "another laser.", 300, 230);
        game.batch.end();

    }

    @Override
    public void render (float delta) {
        draw();
        update();
    }

    @Override
    public void hide () {
        helpImage.dispose();
    }
}

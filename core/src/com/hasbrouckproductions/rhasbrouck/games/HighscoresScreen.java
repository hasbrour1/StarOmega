package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by hasbrouckr on 6/10/2016.
 */
public class HighscoresScreen extends ScreenAdapter {

    StarOmega game;
    OrthographicCamera guiCam;

    public HighscoresScreen(StarOmega game){
        this.game = game;
        guiCam = new OrthographicCamera();
        guiCam.setToOrtho(false, 800, 480);
    }

    public void update(){
        if (Gdx.input.justTouched()) {
            game.setScreen(new MainMenuScreen(game));
        }
    }

    public void draw(){
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
        Assets.font.draw(game.batch, "High Scores:", 200, 360);
        Assets.font.draw(game.batch, "1.", 200, 330);
        Assets.font.draw(game.batch, "2.", 200, 300);
        Assets.font.draw(game.batch, "3.", 200, 270);
        Assets.font.draw(game.batch, "4.", 200, 230);
        game.batch.end();
    }

    @Override
    public void render (float delta) {
        update();
        draw();
    }

}

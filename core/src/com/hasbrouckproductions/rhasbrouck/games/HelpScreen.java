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
    Vector3 touchPoint;
    Texture helpImage;
    TextureRegion helpRegion;

    public HelpScreen (StarOmega game) {
        this.game = game;
        guiCam = new OrthographicCamera();
        guiCam.setToOrtho(false, 800, 480);
        helpImage = Assets.loadTexture("data/help.png");
        helpRegion = new TextureRegion(helpImage, 0, 0, 320, 480);
    }

    public void update () {

    }

    public void draw () {
        GL20 gl = Gdx.gl;
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        guiCam.update();
        game.batch.setProjectionMatrix(guiCam.combined);
        game.batch.disableBlending();
        game.batch.begin();
        game.batch.draw(helpRegion, 0, 0);
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

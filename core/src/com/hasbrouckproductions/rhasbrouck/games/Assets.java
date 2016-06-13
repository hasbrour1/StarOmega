package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import javafx.scene.text.Font;

/**
 * Created by hasbrouckr on 6/10/2016.
 */
public class Assets {

    public static Texture logo;
    public static Texture backgroundRegion;

    //no texture file yet
    public static Texture ready;
    public static Texture pause;
    public static Texture pauseMenu;
    public static Texture gameOver;
    public static Texture powerUp;

    public static TextureRegion mainShip;
    public static TextureRegion explosion;
    public static TextureRegion shipHit;
    public static TextureRegion enemyShip;

    public static Sound clickSound;

    //no sound file yet
    public static Sound shootSound;
    public static Sound hitSound;

    //no font yet
    public static BitmapFont font;

    public static void load(){

        backgroundRegion = new Texture(Gdx.files.internal("data/img/backgroundRegion.jpg"));
        logo = new Texture(Gdx.files.internal("data/img/logo.png"));

        clickSound = Gdx.audio.newSound(Gdx.files.internal("data/sound/clickSound.wav"));

    }

    public static Texture loadTexture(String loc){
        return new Texture(Gdx.files.internal(loc));
    }

    public static void playSound(Sound sound){
        sound.play();
    }
}

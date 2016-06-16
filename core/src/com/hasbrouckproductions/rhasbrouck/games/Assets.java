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
    public static Texture startButton;
    public static Texture highScoreButton;
    public static Texture helpButton;
    public static Texture ready;
    public static Texture pause;
    public static Texture pauseMenu;
    public static Texture gameOver;
    public static Texture powerUp;
    public static Texture mainLaser;


    public static TextureRegion mainShip;
    public static Texture enemyShip;
    public static TextureRegion explosion;
    public static TextureRegion shipHit;


    public static Sound clickSound;
    public static Sound shootSound;
    public static Sound hitSound;

    public static BitmapFont font;

    public static void load(){

        backgroundRegion = new Texture(Gdx.files.internal("data/img/backgroundRegion.jpg"));
        logo = new Texture(Gdx.files.internal("data/img/logo.png"));
        highScoreButton = new Texture(Gdx.files.internal("data/img/highScoreButton.png"));
        startButton = new Texture(Gdx.files.internal("data/img/startButton.png"));
        helpButton = new Texture(Gdx.files.internal("data/img/help.png"));
        ready = new Texture(Gdx.files.internal("data/img/ready.png"));
        pause = new Texture(Gdx.files.internal("data/img/pause.png"));
        pauseMenu = new Texture(Gdx.files.internal("data/img/pauseMenu.png"));
        gameOver = new Texture(Gdx.files.internal("data/img/gameOver.png"));
        powerUp = new Texture(Gdx.files.internal("data/img/powerUp.png"));
        mainLaser = new Texture(Gdx.files.internal("data/img/laser.jpg"));

        mainShip = new TextureRegion(new Texture(Gdx.files.internal("data/img/mainShipScaled.png")), 0,0, 100, 100);
        enemyShip = new Texture(Gdx.files.internal("data/img/enemyShipScaled.png"));
        explosion = new TextureRegion(new Texture(Gdx.files.internal("data/img/explosion.png")), 0,0, 50, 50);
        shipHit = new TextureRegion(new Texture(Gdx.files.internal("data/img/explosion.png")), 0,0, 50, 50);

        clickSound = Gdx.audio.newSound(Gdx.files.internal("data/sound/clickSound.wav"));
        shootSound = Gdx.audio.newSound(Gdx.files.internal("data/sound/shootSound.mp3"));
        hitSound = Gdx.audio.newSound(Gdx.files.internal("data/sound/hitSound.wav"));

        font = new BitmapFont(Gdx.files.internal("data/fonts/mainFont.fnt"),
                Gdx.files.internal("data/fonts/mainFont.png"),false);

    }

    public static Texture loadTexture(String loc){
        return new Texture(Gdx.files.internal(loc));
    }

    public static void playSound(Sound sound){
        sound.play();
    }
}

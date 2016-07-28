package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import javafx.scene.text.Font;

/**
 * Created by hasbrouckr on 6/10/2016.
 *
 * This Class holds all the assets for the game
 *
 */
public class Assets {

    public static Texture logo;
    public static Texture backgroundRegion;
    public static Texture startButton;
    public static Texture highScoreButton;
    public static Texture helpButton;
    public static Texture ready;
    public static Texture pause;
    public static Texture gameOver;
    public static Texture powerUp;
    public static Texture mainLaser;
    public static Texture quitButton;
    public static Texture resumeButton;
    public static Texture resetScoreButton;

    public static Texture firstBossTexture;
    public static Texture secondBossTexture;
    public static Texture thirdBossTexture;


    public static TextureRegion mainShip;
    public static Texture enemyShip;
    public static TextureRegion shipHit;


    public static Sound clickSound;
    public static Sound shootSound;
    public static Sound hitSound;

    public static BitmapFont font;

    public static Animation explosionAnimation;
    public static Texture explosion;
    public static TextureRegion[] explosionFrames;

    public static void load(){

        backgroundRegion = new Texture(Gdx.files.internal("data/img/backgroundRegion.jpg"));
        logo = new Texture(Gdx.files.internal("data/img/logo.png"));
        highScoreButton = new Texture(Gdx.files.internal("data/img/highScoreButton.png"));
        startButton = new Texture(Gdx.files.internal("data/img/startButton.png"));
        helpButton = new Texture(Gdx.files.internal("data/img/help.png"));
        ready = new Texture(Gdx.files.internal("data/img/ready.png"));
        pause = new Texture(Gdx.files.internal("data/img/pause.png"));
        gameOver = new Texture(Gdx.files.internal("data/img/gameOver.png"));
        powerUp = new Texture(Gdx.files.internal("data/img/powerUp.png"));
        mainLaser = new Texture(Gdx.files.internal("data/img/mainLaser.png"));
        quitButton = new Texture(Gdx.files.internal("data/img/quit.png"));
        resumeButton = new Texture(Gdx.files.internal("data/img/resume.png"));
        resetScoreButton = new Texture(Gdx.files.internal("data/img/scoreReset.png"));

        firstBossTexture = new Texture(Gdx.files.internal("data/img/enemyShip.png"));
        secondBossTexture = new Texture(Gdx.files.internal("data/img/bossTwo.png"));
        thirdBossTexture = new Texture(Gdx.files.internal("data/img/bossThree.png"));

        mainShip = new TextureRegion(new Texture(Gdx.files.internal("data/img/mainShipScaled.png")), 0,0, 100, 100);
        enemyShip = new Texture(Gdx.files.internal("data/img/enemyShipScaled.png"));
        shipHit = new TextureRegion(new Texture(Gdx.files.internal("data/img/explosion.png")), 0,0, 50, 50);

        clickSound = Gdx.audio.newSound(Gdx.files.internal("data/sound/clickSound.wav"));
        shootSound = Gdx.audio.newSound(Gdx.files.internal("data/sound/shootSound.mp3"));
        hitSound = Gdx.audio.newSound(Gdx.files.internal("data/sound/hitSound.wav"));

        font = new BitmapFont(Gdx.files.internal("data/fonts/mainFont.fnt"),
                Gdx.files.internal("data/fonts/mainFont.png"),false);

        //Configure Explosion Animation
        explosion = new Texture(Gdx.files.internal("data/img/explosionEdit.png"));
        TextureRegion[][] tmp = TextureRegion.split(explosion, explosion.getWidth()/3, explosion.getHeight() / 2 - 10);
        explosionFrames = new TextureRegion[3 * 2];
        int index = 0;
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 3; j++){
                explosionFrames[index++] = tmp[i][j];
            }
        }

        explosionAnimation = new Animation(0.025f, explosionFrames);

    }

    public static Texture loadTexture(String loc){
        return new Texture(Gdx.files.internal(loc));
    }

    public static void playSound(Sound sound){
        sound.play();
    }
}

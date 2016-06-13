package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

/**
 * Created by hasbrouckr on 6/10/2016.
 */
public class Assets {

    public static Texture logo;
    public static Texture backgroundRegion;

    public static Sound clickSound;

    public static void load(){

        backgroundRegion = new Texture(Gdx.files.internal("data/img/backgroundRegion.jpg"));
        logo = new Texture(Gdx.files.internal("data/img/logo.png"));

    }

    public static Texture loadTexture(String loc){
        return new Texture(Gdx.files.internal(loc));
    }

    public static void playSound(Sound sound){
        sound.play();
    }
}

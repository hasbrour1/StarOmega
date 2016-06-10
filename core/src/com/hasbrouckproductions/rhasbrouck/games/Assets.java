package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by hasbrouckr on 6/10/2016.
 */
public class Assets {

    public static void load(){

    }

    public static Texture loadTexture(String loc){
        return new Texture(Gdx.files.internal(loc));
    }
}

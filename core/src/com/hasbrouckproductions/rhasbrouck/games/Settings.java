package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by hasbrouckr on 6/2/2016.
 * This class will load and save the settings
 *
 *
 */

public class Settings {
    public static int highScore = 0;
    public  static Preferences prefs;

    public final static String SETTINGSHIGHSCORE = "com.hasbrouckproductions.rhasbrouck.StarOmega.SETTINGSHIGHSCORE";
    public final static String SETTINGSPREFS = "com.hasbrouckproductions.rhasbrouck.StarOmega.SETTINGSPREFS";

    public static void load () {
        prefs = Gdx.app.getPreferences(SETTINGSPREFS);
        highScore = prefs.getInteger(SETTINGSHIGHSCORE, 0);
    }

    public static void save () {

        prefs.putInteger(SETTINGSHIGHSCORE, highScore);
        prefs.flush();
    }

    public static void addHighScore(int score){
        highScore = score;
    }
}
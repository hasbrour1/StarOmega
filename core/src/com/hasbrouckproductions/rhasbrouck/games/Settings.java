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
    public static int[] highScores;
    public  static Preferences prefs;

    public final static String SETTINGSHIGHSCOREFIRST = "com.hasbrouckproductions.rhasbrouck.StarOmega.SETTINGSHIGHSCOREFIRST";
    public final static String SETTINGSHIGHSCORESECOND = "com.hasbrouckproductions.rhasbrouck.StarOmega.SETTINGSHIGHSCORESECOND";
    public final static String SETTINGSHIGHSCORETHIRD = "com.hasbrouckproductions.rhasbrouck.StarOmega.SETTINGSHIGHSCORETHIRD";
    public final static String SETTINGSHIGHSCOREFORTH = "com.hasbrouckproductions.rhasbrouck.StarOmega.SETTINGSHIGHSCOREFOURTH";
    public final static String SETTINGSHIGHSCOREFIFTH = "com.hasbrouckproductions.rhasbrouck.StarOmega.SETTINGSHIGHSCOREFIFTH";
    public final static String SETTINGSPREFS = "com.hasbrouckproductions.rhasbrouck.StarOmega.SETTINGSPREFS";

    public static void load () {
        highScores = new int[5];
        prefs = Gdx.app.getPreferences(SETTINGSPREFS);
        highScores[0] = prefs.getInteger(SETTINGSHIGHSCOREFIRST, 0);
        highScores[1] = prefs.getInteger(SETTINGSHIGHSCORESECOND, 0);
        highScores[2] = prefs.getInteger(SETTINGSHIGHSCORETHIRD, 0);
        highScores[3] = prefs.getInteger(SETTINGSHIGHSCOREFORTH, 0);
        highScores[4] = prefs.getInteger(SETTINGSHIGHSCOREFIFTH, 0);
    }

    public static void save () {

        prefs.putInteger(SETTINGSHIGHSCOREFIRST, highScores[0]);
        prefs.putInteger(SETTINGSHIGHSCORESECOND, highScores[1]);
        prefs.putInteger(SETTINGSHIGHSCORETHIRD, highScores[2]);
        prefs.putInteger(SETTINGSHIGHSCOREFORTH, highScores[3]);
        prefs.putInteger(SETTINGSHIGHSCOREFIFTH, highScores[4]);
        prefs.flush();
    }

    public static boolean addHighScore(int score){

        boolean isHighScore = false;
        int current = score;
        int temp;

        for(int i = 0; i < highScores.length; i++){
            if(current > highScores[i]){
                isHighScore = true;
                temp = highScores[i];
                highScores[i] = current;
                current = temp;
            }
        }

        return isHighScore;
    }
}
package com.desitum.crackTheCode.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Zmyth97 on 2/9/2015.
 */
public class Settings {
    public static boolean volumeOn = true;
    public static float volume = 1;
    public static int regularHighscore;
    public static int endlessHighscore;

    public static void getSound() {
        Preferences prefs = Gdx.app.getPreferences("settings");
        prefs.putBoolean("soundOn", volumeOn);
        prefs.flush();
        if(Settings.volumeOn == true)
        {
            //Assets.menuMusic.setVolume(1);
            volume = 1;
        }
        else
        {
            //Assets.menuMusic.setVolume(0);
            volume = 0;
        }
    }

    public static void load(){
        Preferences prefs = Gdx.app.getPreferences("settings");
        volumeOn = prefs.getBoolean("soundOn", true);
        regularHighscore = prefs.getInteger("regularHighscore", 0);
        endlessHighscore = prefs.getInteger("endlessHighscore", 0);
        getSound();
    }

    public static void saveRegularScore(int score) {
        if (regularHighscore > score){
            return;
        }
        regularHighscore = score;
        Preferences prefs = Gdx.app.getPreferences("settings");
        prefs.putInteger("regularHighscore", score);
        prefs.flush();
    }

    public static void saveEndlessScore(int score) {
        if (endlessHighscore > score){
            return;
        }
        endlessHighscore = score;
        Preferences prefs = Gdx.app.getPreferences("settings");
        prefs.putInteger("endlessHighscore", score);
        prefs.flush();
    }
}




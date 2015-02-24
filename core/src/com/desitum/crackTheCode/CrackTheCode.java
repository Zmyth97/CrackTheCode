package com.desitum.crackTheCode;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.desitum.crackTheCode.data.Assets;
import com.desitum.crackTheCode.screens.MainScreen;
import com.desitum.crackTheCode.screens.SplashScreen;

public class CrackTheCode extends Game {
    GooglePlayServicesInterface googlePlay;


    public static final int FIRST_CODE = 0;
    public static final int CODE_NOVICE = 1;
    public static final int CODE_EXPERT = 2;
    public static final int CODE_MASTER = 3;
    public static final int CODE_LEGEND = 4;

    public static final int ENDLESS_BEGINNER = 5;
    public static final int ENDLESS_NOVICE = 6;
    public static final int ENDLESS_EXPERT = 7;
    public static final int ENDLESS_MASTER = 8;
    public static final int ENDLESS_LEGEND = 9;

   public CrackTheCode(GooglePlayServicesInterface googlePlay) {
        this.googlePlay = googlePlay;
    }

    @Override
    public void create () {
        Screen splashScreen = new SplashScreen(googlePlay, this);
        this.setScreen(splashScreen);
    }

    @Override
    public void render () {
        super.render();
    }

    @Override
    public void dispose () {
        super.dispose();

        Assets.dispose();

        getScreen().dispose();
    }

}

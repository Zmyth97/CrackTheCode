package com.desitum.crackTheCode.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Zmyth97 on 2/9/2015.
 */
public class Assets {

    public static Texture endlessButtonTexture;
    public static Texture playButtonTexture;
    public static Texture scoreButtonTexture;
    public static Texture soundButtonOnTexture;
    public static Texture soundButtonOffTexture;
    public static Texture menuTitleTexture;

    public static Texture buttonTexture;
    public static Texture activeButtonTexture;

    public static void loadMenuTextures() {
        playButtonTexture = new Texture(Gdx.files.internal("menu/playButton.png"));
        endlessButtonTexture = new Texture(Gdx.files.internal("menu/endlessButton.png"));
        scoreButtonTexture = new Texture(Gdx.files.internal("menu/scoreButton.png"));
        soundButtonOffTexture = new Texture(Gdx.files.internal("menu/soundButtonOff.png"));
        soundButtonOnTexture = new Texture(Gdx.files.internal("menu/soundButtonOn.png"));
        menuTitleTexture = new Texture(Gdx.files.internal("menu/menuTitle.png"));
    }

    public static void loadGameTextures() {
        //Color for buttons goes here! :D
    }
}

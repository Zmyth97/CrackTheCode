package com.desitum.crackTheCode.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

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

    public static Sound buttonSound;
    public static BitmapFont font;

    public static void loadMenuTextures() {
        playButtonTexture = new Texture(Gdx.files.internal("menu/playButton.png"));
        endlessButtonTexture = new Texture(Gdx.files.internal("menu/endlessButton.png"));
        scoreButtonTexture = new Texture(Gdx.files.internal("menu/scoreButton.png"));
        soundButtonOffTexture = new Texture(Gdx.files.internal("menu/soundButtonOff.png"));
        soundButtonOnTexture = new Texture(Gdx.files.internal("menu/soundButtonOn.png"));
        menuTitleTexture = new Texture(Gdx.files.internal("menu/menuTitle.png"));
        font = new BitmapFont(Gdx.files.internal("font/cartoon.fnt"), Gdx.files.internal("font/cartoon.png"), false);
        font.setScale(0.25f);
        font.setColor(Color.BLACK);
    }

    public static void loadGameTextures() {
        buttonTexture = new Texture(Gdx.files.internal("game/button.png"));
    }

    public static void loadSounds(){
        buttonSound = Gdx.audio.newSound(Gdx.files.internal("sound/Click.mp3"));
    }

    public static void dispose(){
        //TODO Add Disposals!!!!!! (CMON BOY!)
    }
}

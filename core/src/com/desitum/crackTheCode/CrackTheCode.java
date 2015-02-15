package com.desitum.crackTheCode;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.desitum.crackTheCode.data.Assets;
import com.desitum.crackTheCode.screens.MainScreen;

public class CrackTheCode extends Game {
    SpriteBatch batch;
    Texture img;
    GooglePlayServicesInterface googlePlay;

    public CrackTheCode(GooglePlayServicesInterface googlePlay) {
        this.googlePlay = googlePlay;
    }

    @Override
    public void create() {
        Assets.loadMenuTextures();
        Assets.loadGameTextures();
        Screen mainScreen = new MainScreen(googlePlay);
        this.setScreen(mainScreen);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();

        getScreen().dispose();
    }

}

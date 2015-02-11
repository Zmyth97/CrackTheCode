package com.desitum.crackTheCode;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.desitum.crackTheCode.data.Assets;
import com.desitum.crackTheCode.screens.MainScreen;

public class CrackTheCode extends Game {
	SpriteBatch batch;
	Texture img;
    GooglePlayServicesInterface googlePlay;
	
	@Override
	public void create () {
        Assets.loadMenuTextures();
        Screen mainScreen = new MainScreen();
        this.setScreen(mainScreen);
	}

	@Override
	public void render () {
        super.render();
	}

    @Override
    public void dispose()
    {
        super.dispose();

        getScreen().dispose();
    }

}

package com.desitum.crackTheCode.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.desitum.crackTheCode.CrackTheCode;
import com.desitum.crackTheCode.GooglePlayServicesInterface;
import com.desitum.crackTheCode.data.Assets;
import com.desitum.crackTheCode.data.Settings;
import com.desitum.crackTheCode.libraries.Colors;

/**
 * Created by Zmyth97 on 2/24/2015.
 */
public class SplashScreen implements Screen {

    private OrthographicCamera cam;
    private SpriteBatch batch;
    private CrackTheCode game;
    private GooglePlayServicesInterface gps;

    private Sprite desitum;

    private boolean beenThrough = false;
    private boolean hasLoaded = false;
    private float timeElapsed = 0;

    public SplashScreen(GooglePlayServicesInterface gps, CrackTheCode game){

        cam = new OrthographicCamera(MainScreen.SCREEN_WIDTH, MainScreen.SCREEN_HEIGHT);
        cam.position.set(MainScreen.SCREEN_WIDTH/2, MainScreen.SCREEN_HEIGHT/2, 0);
        batch = new SpriteBatch();

        this.gps = gps;
        this.game = game;

        Texture desitumTexture = new Texture(Gdx.files.internal("menu/desitum.png"));
        desitum = new Sprite(desitumTexture);
        desitum.setSize(10, 15);
        desitum.setX(0);
        desitum.setY(MainScreen.SCREEN_HEIGHT/2 - desitum.getHeight()/2);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (timeElapsed > 3){
            game.setScreen(new MainScreen(gps));
        } else if (beenThrough && !hasLoaded){
            hasLoaded = true;
            Assets.loadMenuTextures();
            Assets.loadGameTextures();
            Assets.loadSounds();
            Colors.load();
            Settings.load();
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0,0,0, 1);

        cam.update();

        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        desitum.draw(batch);
        batch.end();
        timeElapsed += delta;
        beenThrough = true;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}


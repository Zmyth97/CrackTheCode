package com.desitum.crackTheCode.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.desitum.crackTheCode.objects.Tile;
import com.desitum.crackTheCode.screens.MainScreen;

/**
 * Created by Zmyth97 on 2/9/2015.
 */
public class GameRenderer {

    private SpriteBatch gameBatch;
    private OrthographicCamera gameCam;
    private GameWorld world;

    public GameRenderer (GameWorld world, SpriteBatch batch)
    {
        this.world = world;
        this.gameBatch = batch;

        gameCam = new OrthographicCamera(MainScreen.SCREEN_WIDTH, MainScreen.SCREEN_HEIGHT);
        gameCam.position.set(MainScreen.SCREEN_WIDTH/2, MainScreen.SCREEN_WIDTH/2, 0);
    }

    public void render()
    {
        gameCam.position.set(MainScreen.SCREEN_WIDTH/2, MainScreen.SCREEN_HEIGHT/2, 0);
        gameCam.update();
        gameBatch.setProjectionMatrix(gameCam.combined);

        //for (Tile t : world.tile) {
        //    t.draw(gameBatch);
        //}
    }

    public OrthographicCamera getCam()
    {
        return gameCam;
    }
    public void resetCam()
    {
        gameCam.position.set(MainScreen.SCREEN_WIDTH/2, MainScreen.SCREEN_HEIGHT/2, 0);
    }
}

package com.desitum.crackTheCode.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.desitum.crackTheCode.data.Assets;
import com.desitum.crackTheCode.objects.Tile;
import com.desitum.crackTheCode.screens.MainScreen;

/**
 * Created by Zmyth97 on 2/9/2015.
 */


public class GameWorld {

    Tile tile;
    private int tileCount;

    public GameWorld()
    {
       tileCount = 0;

       //tile = new Tile(2, 1, false, Assets.playButtonTexture);

    }

    public void update(int state, OrthographicCamera cam, float delta)
    {
        if (state == MainScreen.GAME_RUNNING) {

        }

    }

    public void reset() {
        //Placeholder for MainScreen
    }

}

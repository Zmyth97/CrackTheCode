package com.desitum.crackTheCode.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.desitum.crackTheCode.data.Assets;
import com.desitum.crackTheCode.objects.Tile;
import com.desitum.crackTheCode.screens.MainScreen;

import java.util.ArrayList;

/**
 * Created by Zmyth97 on 2/9/2015.
 */


public class GameWorld {
    ArrayList<Tile> tiles;
    private int tileCount;

    public GameWorld() {
        tileCount = 0;

        tiles = new ArrayList<Tile>();
        tiles.add(new Tile(2, 1, 1, Assets.playButtonTexture));

    }

    public void update(int state, OrthographicCamera cam, float delta) {
        if (state == MainScreen.GAME_RUNNING) {

        }

    }

    public void reset() {
        //Placeholder for MainScreen
    }

}

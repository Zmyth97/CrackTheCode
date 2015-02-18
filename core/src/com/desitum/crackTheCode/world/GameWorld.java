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
        tileCount = 24;

        tiles = new ArrayList<Tile>();
        for(int tilesToDraw = 0; tilesToDraw < tileCount; tilesToDraw++) {
            float locY = (tilesToDraw/4) * 2.4f + 0.4f;
            float locX = (tilesToDraw%4) * 2.4f + 0.4f;
            tiles.add(new Tile(2, locX, locY, Assets.buttonTexture));
        }
    }

    public void setGameMode(){

    }

    public void update(int state, OrthographicCamera cam, float delta) {
        if (state == MainScreen.GAME_RUNNING) {

        }

    }

    public ArrayList<Tile> getTiles(){
        return tiles;
    }

    public void newActiveTile(){

    }

    public void reset() {
        //Placeholder for MainScreen
    }

}

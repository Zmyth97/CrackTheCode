package com.desitum.crackTheCode.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.desitum.crackTheCode.data.Assets;
import com.desitum.crackTheCode.objects.Tile;
import com.desitum.crackTheCode.screens.MainScreen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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

        Collections.shuffle(tiles);
        newActiveTile();
    }

    public void setGameMode(){

    }

    public void update(int state, OrthographicCamera cam, float delta) {
        if (state == MainScreen.GAME_RUNNING) {

        }

        for(Tile t: tiles){
            t.update(delta);
        }

    }

    public ArrayList<Tile> getTiles(){
        return tiles;
    }

    public void newActiveTile(){
        if (MainScreen.GAME_MODE == MainScreen.ENDLESS_MODE){
            Random r = new Random();
            tiles.get(r.nextInt(tiles.size())).makeActive();
        } else {
            for (Tile t: tiles){
                if (!t.isDisabled()){
                    t.makeActive();
                    break;
                }
            }
        }
    }

    public void reset() {
        //Placeholder for MainScreen
    }

}

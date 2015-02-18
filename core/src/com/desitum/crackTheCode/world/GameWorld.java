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
            if(tilesToDraw < 4){
                tiles.add(new Tile(1, 2f + tilesToDraw, 0, Assets.buttonTexture));
            }
            else if(tilesToDraw < 8){
                tiles.add(new Tile(1, 2f + tilesToDraw -4 , 2, Assets.buttonTexture));
            }
            else if(tilesToDraw < 12){
                tiles.add(new Tile(1, 2f + tilesToDraw - 8, 4, Assets.buttonTexture));
            }
            else if(tilesToDraw < 16){
                tiles.add(new Tile(1, 2f + tilesToDraw - 12, 6, Assets.buttonTexture));
            }
            else if(tilesToDraw < 20){
                tiles.add(new Tile(1, 2f + tilesToDraw - 16, 8, Assets.buttonTexture));
            }
            else{
                tiles.add(new Tile(1, 2f + tilesToDraw - 20, 10, Assets.buttonTexture));
            }
        }
    }

    public void update(int state, OrthographicCamera cam, float delta) {
        if (state == MainScreen.GAME_RUNNING) {

        }

    }

    public void reset() {
        //Placeholder for MainScreen
    }

}

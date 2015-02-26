package com.desitum.crackTheCode.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.desitum.crackTheCode.data.Assets;
import com.desitum.crackTheCode.objects.MenuButton;
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
    ArrayList<MenuButton> gameOverButtons;
    private int tileCount;


    public GameWorld() {
        tileCount = 12;


        tiles = new ArrayList<Tile>();
        for (int tilesToDraw = 0; tilesToDraw < tileCount; tilesToDraw++) {
            float locY = (tilesToDraw / 3) * 3.33f + 0.33f;
            float locX = (tilesToDraw % 3) * 3.33f + 0.33f;
            tiles.add(new Tile(3, locX, locY, Assets.buttonTexture));
        }

        gameOverButtons = new ArrayList<MenuButton>();
        gameOverButtons.add(new MenuButton(MainScreen.PLAY, 1, 6, Assets.buttonTexture));
        gameOverButtons.add(new MenuButton(MainScreen.SCORE, 1, 4, Assets.buttonTexture));
        gameOverButtons.add(new MenuButton(MainScreen.SHARE, 1, 2, Assets.buttonTexture));

        Collections.shuffle(tiles);
        newActiveTile();
    }

    public void setGameMode() {

    }

    public void newScreen(){
        tiles = new ArrayList<Tile>();
        for (int tilesToDraw = 0; tilesToDraw < tileCount; tilesToDraw++) {
            float locY = (tilesToDraw / 3) * 3.33f + 0.33f;
            float locX = (tilesToDraw % 3) * 3.33f + 0.33f;
            tiles.add(new Tile(3, locX, locY, Assets.buttonTexture));
        }
        Collections.shuffle(tiles);
        newActiveTile();
    }

    public void update(int state, OrthographicCamera cam, float delta) {
        if (state == MainScreen.GAME_RUNNING) {

            for (Tile t : tiles) {
                t.update(delta);
                t.appear();
            }
        } else if (state == MainScreen.GAME_OVER) {
            for (MenuButton mb : gameOverButtons) {
                mb.update(delta);
            }

        }
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public void newActiveTile() {
        if (MainScreen.GAME_MODE == MainScreen.ENDLESS_MODE) {
            Random r = new Random();
            tiles.get(r.nextInt(tiles.size())).makeActive();
        } else {
            for (Tile t : tiles) {
                if (!t.isDisabled()) {
                    t.makeActive();
                    break;
                }
            }
        }
    }

    public ArrayList<MenuButton> getGameOverButtons() {
        return gameOverButtons;
    }

    public void reset() {
        gameOverButtons.add(new MenuButton(MainScreen.SCORE, 1, 1, Assets.buttonTexture));
        gameOverButtons.add(new MenuButton(MainScreen.PLAY, 4, 1, Assets.buttonTexture));
        gameOverButtons.add(new MenuButton(MainScreen.SOUND, 7, 1, Assets.buttonTexture));
    }

}

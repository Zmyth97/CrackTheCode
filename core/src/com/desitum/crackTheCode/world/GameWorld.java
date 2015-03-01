package com.desitum.crackTheCode.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.desitum.crackTheCode.data.Assets;
import com.desitum.crackTheCode.objects.MenuButton;
import com.desitum.crackTheCode.objects.Tile;
import com.desitum.crackTheCode.screens.MainScreen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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
            tiles.add(new Tile(locX, locY, Assets.buttonTexture));
        }

        gameOverButtons = new ArrayList<MenuButton>();
        gameOverButtons.add(new MenuButton(MainScreen.PLAY, 0.9f, 1, 5.5f, Assets.playButtonTexture));
        gameOverButtons.add(new MenuButton(MainScreen.SCORE, 0.9f, 5.5f, 5.5f, Assets.shareButtonTexture));
        gameOverButtons.add(new MenuButton(MainScreen.SHARE, 0.9f, 3.25f, 1, Assets.scoreButtonTexture));

        for (MenuButton mb: gameOverButtons){
            mb.setScale(0);
        }

        Collections.shuffle(tiles);
        newActiveTile();
    }

    public void setGameMode() {

    }

    public void newScreen(){
        Tile tileToAdd =  null;
        for (Tile t: tiles){
            if (t.isFillingScreen()){
                tileToAdd = t;
            }
        }
        tiles = new ArrayList<Tile>();
        for (int tilesToDraw = 0; tilesToDraw < tileCount; tilesToDraw++) {
            float locY = (tilesToDraw / 3) * 3.33f + 0.33f;
            float locX = (tilesToDraw % 3) * 3.33f + 0.33f;
            tiles.add(new Tile(locX, locY, 1, Assets.buttonTexture));
        }
        Collections.shuffle(tiles);
        if (tileToAdd != null) tiles.add(tileToAdd);
    }

    public void update(int state, OrthographicCamera cam, float delta) {
        if (state == MainScreen.GAME_RUNNING) {

            Iterator<Tile> iter = tiles.iterator();
            while (iter.hasNext()){
                Tile t = iter.next();
                if (t.isFillingScreen() && t.isDoneFillingScreen()){
                    iter.remove();
                    newActiveTile();
                }
                t.update(delta);
                t.appear();
            }
        } else if (state == MainScreen.GAME_OVER) {
            for (Tile t: tiles){
                t.update(delta);
            }
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
        tiles = new ArrayList<Tile>();
        for (int tilesToDraw = 0; tilesToDraw < tileCount; tilesToDraw++) {
            float locY = (tilesToDraw / 3) * 3.33f + 0.33f;
            float locX = (tilesToDraw % 3) * 3.33f + 0.33f;
            tiles.add(new Tile(locX, locY, Assets.buttonTexture));
        }

        gameOverButtons = new ArrayList<MenuButton>();
        gameOverButtons.add(new MenuButton(MainScreen.PLAY, 0.9f, 1, 5.5f, Assets.playButtonTexture));
        gameOverButtons.add(new MenuButton(MainScreen.SCORE, 0.9f, 5.5f, 5.5f, Assets.shareButtonTexture));
        gameOverButtons.add(new MenuButton(MainScreen.SHARE, 0.9f, 3.25f, 1, Assets.scoreButtonTexture));

        for (MenuButton mb: gameOverButtons){
            mb.setScale(0);
        }

        Collections.shuffle(tiles);
        newActiveTile();
    }

    public void putActiveLast(){
        Tile putToLast = null;
        Iterator<Tile> iter = tiles.iterator();
        while(iter.hasNext()){
            Tile t = iter.next();
            if (t.isActive()){
                t.fillScreenGameOver();
                putToLast = t;
                iter.remove();
            }
        }
        if (putToLast != null) tiles.add(putToLast);
    }

    public void endGame(){
        for (MenuButton mb: gameOverButtons){
            mb.appear();
        }
    }
}

package com.desitum.crackTheCode.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.desitum.crackTheCode.world.GameRenderer;
import com.desitum.crackTheCode.world.GameWorld;
import com.desitum.crackTheCode.world.MenuRenderer;
import com.desitum.crackTheCode.world.MenuWorld;

/**
 * Created by Zmyth97 on 2/9/2015.
 */
public class MainScreen implements Screen {

    public static final float SCREEN_WIDTH = 10;
    public static final float SCREEN_HEIGHT = 15;

    public static int state = 0;

    public static final int MENU_BEFORE_TRANSITION = 0;
    public static final int MENU_WAITING = 1;
    public static final int MENU_TRANSITION = 2;
    public static final int GAME_BEFORE = 3;
    public static final int GAME_RUNNING = 4;
    public static final int GAME_PAUSED = 5;
    public static final int GAME_OVER = 6;
    public static final int GAME_OVER_WITH_TRANSITION = 7;

    public static String PLAY = "play";
    public static String ENDLESS = "endless_mode";
    public static String OPEN_SCORES = "open_scores";
    public static String SHARE = "share";

    private OrthographicCamera cam;
    private OrthographicCamera textCam;
    private SpriteBatch spriteBatch;

    private MenuWorld menuWorld;
    private GameWorld gameWorld;

    private MenuRenderer menuRenderer;
    private GameRenderer gameRenderer;

    private Vector3 touchPoint;


    public MainScreen()
    {
        cam = new OrthographicCamera(SCREEN_WIDTH * 10, SCREEN_HEIGHT * 10);
        textCam = new OrthographicCamera(100, 150);
        cam.position.set(SCREEN_WIDTH * 10 / 2, SCREEN_HEIGHT * 10 / 2, 0);
        spriteBatch = new SpriteBatch();

        menuWorld = new MenuWorld();
        gameWorld = new GameWorld();

        menuRenderer = new MenuRenderer(menuWorld, spriteBatch);
        gameRenderer = new GameRenderer(gameWorld, spriteBatch);
    }
}

package com.desitum.crackTheCode.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.desitum.crackTheCode.CrackTheCode;
import com.desitum.crackTheCode.GooglePlayServicesInterface;
import com.desitum.crackTheCode.data.Assets;
import com.desitum.crackTheCode.data.Settings;
import com.desitum.crackTheCode.libraries.CollisionDetection;
import com.desitum.crackTheCode.objects.MenuButton;
import com.desitum.crackTheCode.objects.Tile;
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

    public static int state = 1;
    public int score;
    private float gameTimer;
    public int codesBroken;
    private int tileCounter;

    private Viewport viewport;

    public static final int MENU_BEFORE_TRANSITION = 0;
    public static final int MENU_WAITING = 1;
    public static final int MENU_TRANSITION = 2;
    public static final int GAME_BEFORE = 3;
    public static final int GAME_RUNNING = 4;
    public static final int GAME_PAUSED = 5;
    public static final int GAME_OVER = 6;
    public static final int GAME_OVER_WITH_TRANSITION = 7;

    public static int GAME_MODE = 1;
    public static final int ENDLESS_MODE = 0;
    public static final int REGULAR_MODE = 1;

    public static String PLAY = "play";
    public static String ENDLESS = "endless_mode";
    public static String SCORE = "open_scores";
    public static String SOUND = "sound";
    public static String SHARE = "share";

    private OrthographicCamera cam;
    private OrthographicCamera textCam;
    private SpriteBatch spriteBatch;

    private MenuWorld menuWorld;
    private GameWorld gameWorld;

    private MenuRenderer menuRenderer;
    private GameRenderer gameRenderer;

    private Vector3 touchPoint;

    private GooglePlayServicesInterface gpgs;


    public MainScreen(GooglePlayServicesInterface gps) {
        score = 0;
        codesBroken = 0;
        tileCounter = 0;

        gpgs = gps;
        cam = new OrthographicCamera(SCREEN_WIDTH * 10, SCREEN_HEIGHT * 10);
        textCam = new OrthographicCamera(100, 150);
        cam.position.set(SCREEN_WIDTH * 10 / 2, SCREEN_HEIGHT * 10 / 2, 0);

        //the viewport object will handle camera's attributes
        //the aspect provided (worldWidth/worldHeight) will be kept
        viewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT, cam);

        spriteBatch = new SpriteBatch();

        menuWorld = new MenuWorld();
        gameWorld = new GameWorld();

        menuRenderer = new MenuRenderer(menuWorld, spriteBatch);
        gameRenderer = new GameRenderer(gameWorld, spriteBatch);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       Gdx.gl.glClearColor(1f, 1f, 1f, 1);



        if (Gdx.input.justTouched()) {
            if (state == MENU_WAITING || state == MENU_TRANSITION) {
                touchPoint = menuRenderer.getCam().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            } else if (state == GAME_BEFORE || state == GAME_RUNNING || state == GAME_PAUSED || state == GAME_OVER) {
                touchPoint = gameRenderer.getCam().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            }
            onClick();
        }

        update(delta);

        cam.update();
        spriteBatch.enableBlending();
        spriteBatch.begin();

        draw();

        spriteBatch.end();
    }

    private void onClick() {
        switch (state) {
            case MENU_WAITING:
                onClickMenuWaiting();
                break;
            case GAME_BEFORE:
                onClickGameBefore();
                break;
            case GAME_PAUSED:
                onClickGamePaused();
                break;
            case GAME_RUNNING:
                onClickGameRunning();
                break;
            case GAME_OVER:
                onClickGameOver();
                break;
        }
    }

    private void onClickMenuWaiting() {
        for (MenuButton mb : menuWorld.getMenuButtons()) {
            if (CollisionDetection.pointInRectangle(mb.getBoundingRectangle(), touchPoint)) { // if touched a rectangle
                mb.onClick();
                if (mb.getCommand().equals(PLAY)) { // If the button was play
                    state = GAME_RUNNING;
                    GAME_MODE = REGULAR_MODE;
                } else if (mb.getCommand().equals(ENDLESS)) { // If the button was Endless Mode
                    state = GAME_RUNNING;
                    GAME_MODE = ENDLESS_MODE;
                } else if (mb.getCommand().equals(SCORE)) { // If the button was high scores

                } else if (mb.getCommand().equals(SOUND)) { // If the button was sound
                    Settings.volumeOn = !Settings.volumeOn; // toggle whether the volume is on
                    if (Settings.volumeOn) { // update the texture for the Sound Button
                        mb.setTexture(Assets.soundButtonOnTexture); //No sound on texture yet!
                    } else {
                        mb.setTexture(Assets.soundButtonOffTexture); //No sound off texture yet!
                    }
                }
            }
        }
    }

    private void onClickGameBefore() {
        state = GAME_RUNNING;
        gpgs.hideAd();
    }

    private void onClickGamePaused() {
        state = GAME_RUNNING;
    }

    private void onClickGameRunning() {
        for (Tile t : gameWorld.getTiles()) {
            if (CollisionDetection.pointInRectangle(t.getBoundingRectangle(), touchPoint)) {
                if (t.isActive()) {
                    score += 1;
                    tileCounter += 1;
                    t.fadeBack();
                    Assets.buttonSound.play(Settings.volume);
                    gameWorld.newActiveTile();
                }
                else{
                    state = GAME_OVER;
                }
                if (tileCounter == 12 && GAME_MODE == REGULAR_MODE) {
                    t.fillScreen(); //Works, but doesn't show thanks to newScreen() overwriting it.
                    tileCounter = 0;
                    codesBroken+=1;
                    gameWorld.newScreen();

                }
            }
        }
    }

    private void onClickGameOver() {
        //TODO Add in end game interface
    }

    private void update(float delta) {
        switch (state) {
            case MENU_BEFORE_TRANSITION:
                updateMenuBeforeTransition(delta);
                break;
            case MENU_WAITING:
                updateMenuWaiting(delta);
                break;
            case MENU_TRANSITION:
                updateMenuTransition(delta);
                break;
            case GAME_BEFORE:
                updateGameBefore(delta);
                break;
            case GAME_PAUSED:
                updateGamePaused(delta);
                break;
            case GAME_RUNNING:
                updateGameRunning(delta);
                break;
            case GAME_OVER:
                updateGameOver(delta);
                break;
            case GAME_OVER_WITH_TRANSITION:
                updateGameOverTransition(delta);
                break;
        }
    }

    private void updateMenuBeforeTransition(float delta) {
        menuWorld.update(delta);
    }

    private void updateMenuTransition(float delta) {
        menuWorld.update(delta);
    }

    private void updateMenuWaiting(float delta) {
        menuWorld.update(delta);
    }

    private void updateGameBefore(float delta) {
        gameWorld.update(state, gameRenderer.getCam(), delta);
    }

    private void updateGameRunning(float delta) {
        gameWorld.update(state, gameRenderer.getCam(), delta);
        gameTimer += delta;
        if(gameTimer >= 8){
            state = GAME_OVER;
            if(GAME_MODE == REGULAR_MODE) {
                Settings.saveRegularScore(codesBroken);
                gpgs.submitScore(Settings.regularHighscore);
                if(Settings.regularHighscore >= 1){
                    gpgs.unlockAchievement(CrackTheCode.FIRST_CODE);
                }
                if(Settings.regularHighscore >= 10){
                    gpgs.unlockAchievement(CrackTheCode.CODE_NOVICE);
                }
                if(Settings.regularHighscore >= 25){
                    gpgs.unlockAchievement(CrackTheCode.CODE_EXPERT);
                }
                if(Settings.regularHighscore >= 50){
                    gpgs.unlockAchievement(CrackTheCode.CODE_MASTER);
                }
                if(Settings.regularHighscore >= 100){
                    gpgs.unlockAchievement(CrackTheCode.CODE_LEGEND);
                }

            }else {
                Settings.saveEndlessScore(score);
                gpgs.submitScore(Settings.endlessHighscore);
                if(Settings.endlessHighscore >= 20){
                    gpgs.unlockAchievement(CrackTheCode.ENDLESS_BEGINNER);
                }
                if(Settings.endlessHighscore >= 100){
                    gpgs.unlockAchievement(CrackTheCode.ENDLESS_NOVICE);
                }
                if(Settings.endlessHighscore >= 250){
                    gpgs.unlockAchievement(CrackTheCode.ENDLESS_EXPERT);
                }
                if(Settings.endlessHighscore >= 500){
                    gpgs.unlockAchievement(CrackTheCode.ENDLESS_MASTER);
                }
                if(Settings.endlessHighscore >= 1000){
                    gpgs.unlockAchievement(CrackTheCode.ENDLESS_LEGEND);
                }
            }
            gpgs.showAd();
        }
    }

    private void updateGamePaused(float delta) {

    }

    private void updateGameOver(float delta) {
        gameWorld.update(state, gameRenderer.getCam(), delta);
    }

    private void updateGameOverTransition(float delta) {

    }

    private void draw() {
        switch (state) {
            case MENU_BEFORE_TRANSITION:
                drawMenuBeforeTransition();
                break;
            case GAME_OVER:
                drawGameOver();
                break;
            case GAME_RUNNING:
                drawGameRunning();
                break;
            case MENU_WAITING:
                drawMenuWaiting();
                break;
            case MENU_TRANSITION:
                drawMenuTransition();
                break;
            case GAME_BEFORE:
                drawGameBefore();
                break;
            case GAME_PAUSED:
                drawGamePaused();
                break;
            case GAME_OVER_WITH_TRANSITION:
                drawGameOver();
                break;
        }
    }

    private void drawMenuBeforeTransition() {
        menuRenderer.render();
    }

    private void drawGamePaused() {
        gameRenderer.render();
    }

    private void drawGameBefore() {
        gameRenderer.render();

    }

    private void drawMenuTransition() {
        menuRenderer.render();
    }

    private void drawMenuWaiting() {
        menuRenderer.render();
    }

    private void drawGameRunning() {
        gameRenderer.render();
        spriteBatch.setProjectionMatrix(cam.combined);

        if(GAME_MODE == REGULAR_MODE) {
            float width = Assets.font.getBounds(String.valueOf(codesBroken)).width/2;
            float height = Assets.font.getBounds("" + codesBroken).height;
            Assets.font.draw(spriteBatch, String.valueOf(codesBroken), SCREEN_WIDTH * 10 / 2 - width, height);
        } else {
            float width = Assets.font.getBounds(String.valueOf(score)).width/2;
            float height = Assets.font.getBounds("" + score).height;
            Assets.font.draw(spriteBatch, String.valueOf(score), SCREEN_WIDTH * 10 / 2 - width, height);
        }
    }

    private void drawGameOver() {
        gameRenderer.render();
        spriteBatch.setProjectionMatrix(cam.combined);

        if(GAME_MODE == REGULAR_MODE) {
            Assets.font.setScale(0.1f);
            float width = Assets.font.getBounds("Highscore: " + Settings.regularHighscore).width / 2;
            float height = Assets.font.getBounds("Highscore" + Settings.regularHighscore).height;
            Assets.font.draw(spriteBatch, "Highscore: " + Settings.regularHighscore, SCREEN_WIDTH * 10 / 2 - width, 10 * 10 + height);
            Assets.font.setScale(0.25f);
        } else {
            Assets.font.setScale(0.1f);
            float width = Assets.font.getBounds("Highscore: " + Settings.endlessHighscore).width / 2;
            float height = Assets.font.getBounds("Highscore" + Settings.endlessHighscore).height;
            Assets.font.draw(spriteBatch, "Highscore: " + Settings.endlessHighscore, SCREEN_WIDTH * 10 / 2 - width, 10 * 10 + height);
            Assets.font.setScale(0.25f);
        }

        if(GAME_MODE == REGULAR_MODE) {
            float width = Assets.font.getBounds(String.valueOf(codesBroken)).width/2;
            float height = Assets.font.getBounds("" + codesBroken).height;
            Assets.font.draw(spriteBatch, String.valueOf(codesBroken), SCREEN_WIDTH * 10 / 2 - width, 8 * 10 + height);
        } else {
            float width = Assets.font.getBounds(String.valueOf(score)).width/2;
            float height = Assets.font.getBounds("" + score).height;
            Assets.font.draw(spriteBatch, String.valueOf(score), SCREEN_WIDTH * 10 / 2 - width, 8 * 10 + height);
        }
    }

    private void resetGame() {
        cam.position.set(SCREEN_WIDTH * 10 / 2, SCREEN_HEIGHT * 10 / 2, 0);
        gameWorld.reset();
        gameRenderer.resetCam();
        menuRenderer.resetCam();
    }

    @Override
    public void resize(int width, int height) {
        //notice that the method receives the entire screen size
        //the last argument tells the viewport to center the camera in the screen
        viewport.update(width, height, true);
    }


    @Override
    public void show() {

    }

    @Override
    public void hide() {
        if (state == GAME_RUNNING) {
            state = GAME_PAUSED;
        }
    }

    @Override
    public void pause() {
        if (state == GAME_RUNNING) {
            state = GAME_PAUSED;
        }
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

}

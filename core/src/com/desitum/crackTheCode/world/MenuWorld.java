package com.desitum.crackTheCode.world;

import com.desitum.crackTheCode.data.Assets;
import com.desitum.crackTheCode.objects.MenuButton;
import com.desitum.crackTheCode.screens.MainScreen;

import java.util.ArrayList;

/**
 * Created by Zmyth97 on 2/9/2015.
 */
public class MenuWorld {
    private ArrayList<MenuButton> menuButtons;

    private float loadTiming;

    private MenuButton playButton;
    private MenuButton endlessButton;
    private MenuButton scoreButton;
    private MenuButton soundButton;
    private MenuButton scoreRegularButton;
    private MenuButton scoreEndlessButton;

    public MenuWorld() {
        menuButtons = new ArrayList<MenuButton>();
        createButtons();
        loadTiming = 0;
    }

    private void createButtons() {
        //Create the buttons!
        playButton = new MenuButton(MainScreen.PLAY, 1, 5.5f, Assets.playButtonTexture);
        playButton.appear();
        endlessButton = new MenuButton(MainScreen.ENDLESS, 0.15f, 5.5f, 5.5f, Assets.endlessButtonTexture);
        endlessButton.appear();
        scoreButton = new MenuButton(MainScreen.SCORE, 0.3f, 5.5f, 1, Assets.scoreButtonTexture);
        scoreButton.appear();
        soundButton = new MenuButton(MainScreen.SOUND, 0.15f, 1, 1, Assets.soundButtonOnTexture);
        soundButton.appear();
        scoreRegularButton = new MenuButton(MainScreen.SCORE_REG, 0.2f, 5.5f, 2f, Assets.playButtonTexture);
        scoreRegularButton.setSize(1.5f, 1.5f);
        scoreRegularButton.setScale(0);
        scoreRegularButton.setOriginCenter();
        scoreEndlessButton = new MenuButton(MainScreen.SCORE_END, 0.2f, 5.5f + MenuButton.SIZE_X - 1.5f, 2f, Assets.endlessButtonTexture);
        scoreEndlessButton.setSize(1.5f, 1.5f);
        scoreEndlessButton.setScale(0);
        scoreEndlessButton.setOriginCenter();

        //Add the buttons to the ArrayList
        menuButtons.add(playButton);
        menuButtons.add(endlessButton);
        menuButtons.add(scoreButton);
        menuButtons.add(soundButton);
        menuButtons.add(scoreRegularButton);
        menuButtons.add(scoreEndlessButton);

        playButton.appear();
    }

    public void update(float delta) {
        for (MenuButton mb : menuButtons) {
            mb.update(delta);
        }
    }

    public void leaderBoardAnim(){
        scoreButton.disappear();
        scoreRegularButton.appear();
        scoreEndlessButton.appear();
    }

    public ArrayList<MenuButton> getMenuButtons() {
        return this.menuButtons;
    }
}


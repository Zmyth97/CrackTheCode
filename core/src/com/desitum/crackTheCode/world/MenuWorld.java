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

    public MenuWorld() {
        menuButtons = new ArrayList<MenuButton>();
        createButtons();
    }

    private void createButtons() {
        //Create the buttons!
        MenuButton playButton = new MenuButton(MainScreen.PLAY, 1, 5.5f, Assets.playButtonTexture);
        MenuButton endlessButton = new MenuButton(MainScreen.ENDLESS, 5.5f, 5.5f, Assets.endlessButtonTexture);
        MenuButton scoreButton = new MenuButton(MainScreen.SCORE, 5.5f, 1, Assets.scoreButtonTexture);
        MenuButton soundButton = new MenuButton(MainScreen.SOUND, 1, 1, Assets.soundButtonOnTexture);

        //Add the buttons to the ArrayList
        menuButtons.add(playButton);
        menuButtons.add(endlessButton);
        menuButtons.add(scoreButton);
        menuButtons.add(soundButton);
    }

    public void update(float delta) {
        for (MenuButton mb : menuButtons) {
            mb.update(delta);
        }
    }

    public ArrayList<MenuButton> getMenuButtons() {
        return this.menuButtons;
    }
}


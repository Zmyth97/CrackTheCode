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

    /**
     * Kody said I need documentation, but I figure "create buttons" is pretty obvious in what it does....
     */
    private void createButtons() {
        //Create the buttons!
        //ZACK YOU ARE AN IDIOT SET THESE IN THE CLASS!!!!!!!!!!!!!! MenuButton.SIZE_X = 3.5f;
        //ZACK YOU ARE AN IDIOT SET THESE IN THE CLASS!!!!!!!!!!!!!! MenuButton.SIZE_Y = 3.5f;
        MenuButton playButton = new MenuButton(MainScreen.PLAY, 1, 1, Assets.buttonTexture);
        MenuButton endlessButton = new MenuButton(MainScreen.ENDLESS, 1, 5.5f, Assets.buttonTexture);
        MenuButton scoreButton = new MenuButton(MainScreen.SCORE, 5.5f, 1, Assets.buttonTexture);
        MenuButton soundButton = new MenuButton(MainScreen.SOUND, 5.5f, 5.5f, Assets.buttonTexture);

        //Add the buttons to the ArrayList
        menuButtons.add(playButton);
        menuButtons.add(endlessButton);
        menuButtons.add(scoreButton);
        menuButtons.add(soundButton);
    }

    public void update(float delta){
        for (MenuButton mb: menuButtons){
            mb.update(delta);
            System.out.println(mb.getCommand());
        }
    }

    public ArrayList<MenuButton> getMenuButtons() {
        return this.menuButtons;
    }
}


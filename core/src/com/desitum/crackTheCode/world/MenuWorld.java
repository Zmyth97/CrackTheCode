package com.desitum.crackTheCode.world;

import com.badlogic.gdx.graphics.Color;
import com.desitum.crackTheCode.objects.MenuButton;
import com.desitum.crackTheCode.screens.MainScreen;

import java.util.ArrayList;

/**
 * Created by Zmyth97 on 2/9/2015.
 */
public class MenuWorld {
    private ArrayList<MenuButton> menuButtons;

    public MenuWorld()
    {
        menuButtons = new ArrayList<MenuButton>();
        createButtons();
    }

    /**
     * Kody said I need documentation, but I figure "create buttons" is pretty obvious in what it does....
     */
    private void createButtons()
    {
        //Create the buttons!
        MenuButton playButton = new MenuButton(MainScreen.PLAY, 1, 8, Color.BLUE);
        MenuButton endlessButton = new MenuButton(MainScreen.ENDLESS, 1, 6, Color.BLUE);
        MenuButton scoreButton = new MenuButton(MainScreen.SCORE, 1, 4, Color.BLUE);

        //Add the buttons to the ArrayList
        menuButtons.add(playButton);
        menuButtons.add(endlessButton);
        menuButtons.add(scoreButton);
    }
}
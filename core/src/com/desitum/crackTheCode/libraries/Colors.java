package com.desitum.crackTheCode.libraries;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;

/**
 * Created by Zmyth97 on 2/15/2015.
 */
public class Colors {
    public static final Color ACTIVE_CIRCLE = new Color(0.482f, 0.875f, 0f, 1);
    public static final Color GAME_CIRCLE = new Color(0.106f, 0.071f, 0.671f, 1);
    public static final Color DISABLED_CIRCLE = new Color(0.5f, 0.5f, 0.5f, 1);


    public static ArrayList<Color> Colors;


    public static void load() {
        Colors = new ArrayList<Color>();

        Colors.add(ACTIVE_CIRCLE);
        Colors.add(GAME_CIRCLE);
        Colors.add(DISABLED_CIRCLE);
    }

}
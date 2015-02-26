package com.desitum.crackTheCode.libraries;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;

/**
 * Created by Zmyth97 on 2/15/2015.
 */
public class Colors {
    public static final Color ACTIVE_CIRCLE = new Color(0.992f, 0.231f, 0.251f, 1);
    public static final Color GAME_CIRCLE = new Color(0.173f, 0.635f, 0.702f, 1);
    public static final Color DISABLED_CIRCLE = new Color(0.5f, 0.5f, 0.5f, 1);
    public static final Color GREEN = new Color(0.01f, 1f, 0.01f, 1f);
    public static final Color WHITE = new Color(0.99f, 0.99f, 0.99f, 0.99f);

    public static ArrayList<Color> Colors;


    public static void load() {
        Colors = new ArrayList<Color>();

        Colors.add(ACTIVE_CIRCLE);
        Colors.add(GAME_CIRCLE);
        Colors.add(DISABLED_CIRCLE);
        Colors.add(GREEN);
    }

}
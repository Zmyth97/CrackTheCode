package com.desitum.crackTheCode.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
/**
 * Created by Zmyth97 on 2/9/2015. Cmment
 */
public class MenuButton extends Sprite {

    private String command;

    public static float SIZE_X = 8;
    public static float SIZE_Y = 1.5f;
    private float time = 0;

    public MenuButton(String command, float locationX, float locationY, Color color)
    {
        this.command = command;
        this.setPosition(locationX, locationY);
        this.setSize(SIZE_X, SIZE_Y);
    }

    public void update(float delta)
    {
       time += delta;
        if(time > 1) {
            time = 1;
        }
    }

    public String getCommand()
    {
        return command;
    }

    public void setCommand(String command)
    {
        this.command = command;
    }

}

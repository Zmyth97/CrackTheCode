package com.desitum.crackTheCode.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.desitum.crackTheCode.libraries.ColorEffects;

/**
 * Created by Zmyth97 on 2/9/2015.
 */
public class MenuButton extends Sprite {

    private String command;

    private ColorEffects colorChanger;

    public static float SIZE_X = 3.5f;
    public static float SIZE_Y = 3.5f;
    private float time = 0;

    public MenuButton(String command, float locationX, float locationY, Texture texture) {
        super(texture, 0, 0, texture.getWidth(), texture.getHeight());
        this.command = command;
        this.setPosition(locationX, locationY);
        this.setSize(SIZE_X, SIZE_Y);
        this.setColor(0, 0, 0.2f, 1);

        this.colorChanger = new ColorEffects(this.getColor(), new Color(0.98f, 0.85f, 0.4f, 1), 1);
    }

    public void update(float delta) {
        colorChanger.update(delta);
        this.setColor(colorChanger.getCurrentColor());
        time += delta;
        if (time > 1) {
            time = 1;
        }
    }

    private void onClick(){
        colorChanger.start();
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

}

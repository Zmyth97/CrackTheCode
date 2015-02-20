package com.desitum.crackTheCode.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.desitum.crackTheCode.libraries.ScaleAnimation;

/**
 * Created by Zmyth97 on 2/9/2015.
 */
public class MenuButton extends Sprite {

    private String command;

    private ScaleAnimation shrinker;

    public static float SIZE_X = 3.5f;
    public static float SIZE_Y = 3.5f;
    private float time = 0;

    public MenuButton(String command, float locationX, float locationY, Texture texture) {
        super(texture, 0, 0, texture.getWidth(), texture.getHeight());
        this.command = command;
        this.setPosition(locationX, locationY);
        this.setSize(SIZE_X, SIZE_Y);
    }

    public void update(float delta) {
        time += delta;
        if (time > 1) {
            time = 1;
        }
    }

    public void scaleOverShoot(){

    }
    public void onClick(){
        //shrinker.start();
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

}

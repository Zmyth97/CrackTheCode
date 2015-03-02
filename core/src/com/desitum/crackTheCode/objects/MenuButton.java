package com.desitum.crackTheCode.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.desitum.crackTheCode.libraries.animation.ScaleAnimator;
import com.desitum.crackTheCode.libraries.interpolation.Interpolation;

/**
 * Created by Zmyth97 on 2/9/2015.
 */
public class MenuButton extends Sprite {

    private String command;

    public static float SIZE_X = 3.5f;
    public static float SIZE_Y = 3.5f;
    private float time = 0;

    private ScaleAnimator appearAnimator;
    private ScaleAnimator disappearAnimator;

    public MenuButton(String command, float locationX, float locationY, Texture texture) {
        super(texture, 0, 0, texture.getWidth(), texture.getHeight());
        this.command = command;
        this.setPosition(locationX, locationY);
        this.setSize(SIZE_X, SIZE_Y);
        setOriginCenter();

        appearAnimator = new ScaleAnimator(0.8f, 0, 1, Interpolation.OVERSHOOT_INTERPOLATOR);
        disappearAnimator = new ScaleAnimator(0.8f, 1, 0, Interpolation.ANTICIPATE_INTERPOLATOR);
    }

    public MenuButton(String command, float delay, float locationX, float locationY, Texture texture) {
        super(texture, 0, 0, texture.getWidth(), texture.getHeight());
        this.command = command;
        this.setPosition(locationX, locationY);
        this.setSize(SIZE_X, SIZE_Y);
        setOriginCenter();

        appearAnimator = new ScaleAnimator(0.8f, delay, 0, 1, Interpolation.OVERSHOOT_INTERPOLATOR);
        disappearAnimator = new ScaleAnimator(0.8f, 1, 0, Interpolation.ANTICIPATE_INTERPOLATOR);
    }

    public void update(float delta) {
        if (appearAnimator.isRunning()){
            appearAnimator.update(delta);
            setScale(appearAnimator.getScaleSize());
        } if (disappearAnimator.isRunning()){
            disappearAnimator.update(delta);
            setScale(disappearAnimator.getScaleSize());
        }
    }

    public void onClick() {
    }

    public void appear(){
        appearAnimator.start(true);
    }

    public void disappear(){
        disappearAnimator.start(true);
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public boolean hasDisappeared(){
        if (disappearAnimator.didFinish()){
            return true;
        }
        return false;
    }
}

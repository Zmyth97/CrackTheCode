package com.desitum.crackTheCode.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.desitum.crackTheCode.libraries.ColorEffects;
import com.desitum.crackTheCode.libraries.Colors;
import com.desitum.crackTheCode.libraries.animation.ScaleAnimator;
import com.desitum.crackTheCode.libraries.interpolation.Interpolation;
import com.desitum.crackTheCode.screens.MainScreen;

/**
 * Created by Zmyth97 on 2/11/2015.
 */
public class Tile extends Sprite {
    private float scaleAmount;

    private boolean active;
    private boolean disabled;
    private boolean fillingScreen;

    private ColorEffects colorChanger;
    private ScaleAnimator appearAnimator;
    private ScaleAnimator fillAnimator;

    private float animationDelay;

    public Tile(float size, float locationX, float locationY, Texture texture) {
        super(texture, 0, 0, texture.getWidth(), texture.getHeight());

        this.animationDelay = 0;
        this.setColor(Colors.GAME_CIRCLE);
        this.setSize(size, size);
        this.setPosition(locationX, locationY);
        this.scaleAmount = 1;
        this.active = false;
        this.disabled = false;
        fillingScreen = false;

        setOriginCenter();

        fillAnimator = new ScaleAnimator(1f, 3, 15, Interpolation.ANTICIPATE_INTERPOLATOR);
        appearAnimator = new ScaleAnimator(0.8f, 0, 1, Interpolation.OVERSHOOT_INTERPOLATOR);
    }

    public Tile(float size, float locationX, float locationY, float animationDelay, Texture texture) {
        super(texture, 0, 0, texture.getWidth(), texture.getHeight());

        this.animationDelay = animationDelay;
        this.setColor(Colors.GAME_CIRCLE);
        this.setSize(size, size);
        this.setPosition(locationX, locationY);
        this.scaleAmount = 1;
        this.active = false;
        this.disabled = false;
        fillingScreen = false;

        setOriginCenter();

        fillAnimator = new ScaleAnimator(1f, 3.5f/15f , 1, Interpolation.ANTICIPATE_INTERPOLATOR);
        appearAnimator = new ScaleAnimator(0.8f, 0, 1, Interpolation.OVERSHOOT_INTERPOLATOR);
    }

    public void appear(){
        appearAnimator.start(true);
    }

    public boolean isActive() {
        return active;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void fadeBack() {
        System.out.println("active: " + active + ", disabled: " + disabled);
        active = false;
        disabled = true;

        if (MainScreen.GAME_MODE == MainScreen.REGULAR_MODE) {
            colorChanger = new ColorEffects(Colors.ACTIVE_CIRCLE, Colors.DISABLED_CIRCLE, 0.5f);
            colorChanger.start();
        } else {
            colorChanger = new ColorEffects(Colors.ACTIVE_CIRCLE, Colors.GAME_CIRCLE, 0.5f);
            colorChanger.start();
        }
    }

    public void fillScreen(){
        active = false;
        colorChanger = new ColorEffects(Colors.GREEN, Color.WHITE, 1);
        colorChanger.start();
        fillAnimator.start(false);
        float previousSize = getWidth();
        setSize(30, 30);
        setCenterX(getX() + previousSize/2);
        setCenterY(getY() + previousSize/2);
        fillingScreen = true;
    }

    public boolean isFillingScreen(){
        return fillingScreen;
    }

    public void makeActive() {
        active = true;
        colorChanger = new ColorEffects(Colors.GAME_CIRCLE, Colors.ACTIVE_CIRCLE, 0.2f);
        colorChanger.start();

    }

    public void update(float delta) {
        if (animationDelay > 0){
            animationDelay -= delta;
            return;
        }
        if (colorChanger != null) {
            colorChanger.update(delta);
            setColor(colorChanger.getCurrentColor());
        }
        if (fillAnimator.isRunning()){
            fillAnimator.update(delta);
            setScale(fillAnimator.getScaleSize());
        } else {
            appearAnimator.update(delta);
            setScale(appearAnimator.getScaleSize());
        }
    }

}

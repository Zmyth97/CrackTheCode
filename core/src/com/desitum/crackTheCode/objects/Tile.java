package com.desitum.crackTheCode.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.desitum.crackTheCode.libraries.ColorEffects;
import com.desitum.crackTheCode.libraries.Colors;
import com.desitum.crackTheCode.libraries.animation.MovementAnimator;
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

    private static float SIZE = 3;
    private static float FILL_SIZE = 31;

    private ColorEffects colorChanger;
    private MovementAnimator clickAnimator;
    private ScaleAnimator appearAnimator;
    private ScaleAnimator fillAnimator;

    private float animationDelay;

    public Tile(float locationX, float locationY, Texture texture) {
        super(texture, 0, 0, texture.getWidth(), texture.getHeight());

        this.animationDelay = 0;
        this.setColor(Colors.DISABLED_CIRCLE);
        this.setSize(SIZE, SIZE);
        this.setPosition(locationX, locationY);
        this.scaleAmount = 1;
        this.active = false;
        this.disabled = false;
        fillingScreen = false;

        setOriginCenter();

        fillAnimator = new ScaleAnimator(1f, SIZE/FILL_SIZE, 1, Interpolation.ACCELERATE_INTERPOLATOR);
        appearAnimator = new ScaleAnimator(0.8f, 0, 1, Interpolation.OVERSHOOT_INTERPOLATOR);
        clickAnimator = new MovementAnimator(getY() - 0.1f, getY(), 0.1f, Interpolation.LINEAR_INTERPOLATOR);
    }

    public Tile(float locationX, float locationY, float animationDelay, Texture texture) {
        super(texture, 0, 0, texture.getWidth(), texture.getHeight());

        this.animationDelay = animationDelay;
        this.setColor(Colors.DISABLED_CIRCLE);
        this.setSize(SIZE, SIZE);
        this.setPosition(locationX, locationY);
        this.scaleAmount = 1;
        this.active = false;
        this.disabled = false;
        fillingScreen = false;

        setOriginCenter();

        fillAnimator = new ScaleAnimator(1f, SIZE/FILL_SIZE , 1, Interpolation.ACCELERATE_INTERPOLATOR);
        appearAnimator = new ScaleAnimator(0.8f, 0, 1, Interpolation.OVERSHOOT_INTERPOLATOR);
        clickAnimator = new MovementAnimator(getY() - 0.1f, getY(), 0.1f, Interpolation.LINEAR_INTERPOLATOR);
    }

    public void appear(){
        appearAnimator.start(true);
        if (!active && getScaleX() < 0.5f && getWidth() == SIZE) setColor(Colors.GAME_CIRCLE);
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
        clickAnimator.start(false);
    }

    public void fillScreen(){
        active = false;
        colorChanger = new ColorEffects(Colors.ACTIVE_CIRCLE, Colors.WHITE, 0.9f);
        colorChanger.start();
        fillAnimator.start(false);
        float previousCenterX = getX() + getWidth()/2;
        float previousCenterY = getY() + getHeight()/2;
        setSize(FILL_SIZE, FILL_SIZE);
        setX(previousCenterX - FILL_SIZE / 2);
        setY(previousCenterY - FILL_SIZE / 2);
        setOriginCenter();
        fillingScreen = true;
    }

    public void fillScreenGameOver(){
        active = false;
        colorChanger = new ColorEffects(getColor(), Colors.ACTIVE_CIRCLE, 0.9f);
        colorChanger.start();
        fillAnimator.start(false);
        float previousCenterX = getX() + getWidth()/2;
        float previousCenterY = getY() + getHeight()/2;
        setSize(FILL_SIZE, FILL_SIZE);
        setX(previousCenterX-FILL_SIZE/2);
        setY(previousCenterY-FILL_SIZE/2);
        setOriginCenter();
        fillingScreen = true;
    }

    public boolean isFillingScreen(){
        return fillingScreen;
    }

    public boolean isDoneFillingScreen(){
        if (fillingScreen){
            if (fillAnimator.didFinish()){
                return true;
            }
        }
        return false;
    }

    public void makeActive() {
        active = true;
        colorChanger = null;
        setColor(Colors.ACTIVE_CIRCLE);

    }

    public void update(float delta) {
        if (animationDelay > 0){
            animationDelay -= delta;
            return;
        }
        if (colorChanger != null) {
            colorChanger.update(delta);
            setColor(colorChanger.getCurrentColor());
            System.out.println(colorChanger.getCurrentColor());
        }
        if (clickAnimator.isRunning() && !fillAnimator.isRunning()){
            clickAnimator.update(delta);
            setY(clickAnimator.getCurrentPos());
            if (clickAnimator.didFinish()){
                clickAnimator.reset();
            }
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

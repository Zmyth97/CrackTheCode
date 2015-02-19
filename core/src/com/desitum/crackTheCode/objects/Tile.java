package com.desitum.crackTheCode.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.desitum.crackTheCode.data.Assets;
import com.desitum.crackTheCode.libraries.ColorEffects;
import com.desitum.crackTheCode.libraries.Colors;
import com.desitum.crackTheCode.screens.MainScreen;

/**
 * Created by Zmyth97 on 2/11/2015.
 */
public class Tile extends Sprite {
    private float scaleAmount;

    private boolean active;
    private boolean disabled;

    private float tilePositionX;
    private float tilePositionY;

    private ColorEffects colorChanger;

    public Tile(float size, float locationX, float locationY,  Texture texture) {
        super(texture, 0, 0, texture.getWidth(), texture.getHeight());

        this.setColor(Colors.GAME_CIRCLE);
        this.setSize(size, size);
        this.setPosition(locationX, locationY);
        this.scaleAmount = 1;
        this.active = false;
        this.disabled = false;


    }

    public boolean isActive(){
        return active;
    }

    public boolean isDisabled(){
        return disabled;
    }
    public void fadeBack(){
        System.out.println("active: " + active + ", disabled: " + disabled);
        active = false;
        disabled = true;

        if(MainScreen.GAME_MODE == MainScreen.REGULAR_MODE) {
            colorChanger = new ColorEffects(Colors.ACTIVE_CIRCLE, Colors.DISABLED_CIRCLE, 0.2f);
            colorChanger.start();
        }
        else {
            colorChanger = new ColorEffects(Colors.ACTIVE_CIRCLE, Colors.GAME_CIRCLE, 0.2f);
            colorChanger.start();
        }
    }

    public void makeActive(){
        active = true;
        colorChanger = new ColorEffects(Colors.GAME_CIRCLE, Colors.ACTIVE_CIRCLE, 0.2f);
        colorChanger.start();

    }

    public void update(float delta) {
        if (colorChanger != null) {
            colorChanger.update(delta);
            setColor(colorChanger.getCurrentColor());
        }
    }

}

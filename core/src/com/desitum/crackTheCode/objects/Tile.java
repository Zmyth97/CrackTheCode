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

    private float tilePositionX;
    private float tilePositionY;

    private ColorEffects colorChanger;

    public Tile(float size, float locationX, float locationY,  Texture texture) {
        super(texture, 0, 0, texture.getWidth(), texture.getHeight());

        this.setColor(0, 0, 0.2f, 1);
        this.setSize(size, size);
        this.setPosition(locationX, locationY);
        this.scaleAmount = 1;
        this.active = false;


    }

    public boolean isActive(){
        return active;
    }

    public void fadeBack(){
        active = false;

        if(MainScreen.GAME_MODE == MainScreen.REGULAR_MODE) {
            colorChanger = new ColorEffects(Colors.ACTIVE_CIRCLE, Colors.DISABLED_CIRCLE, 0.5f);
        }
        else {

        }
    }

    public void update(float delta) {

    }

}

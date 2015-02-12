package com.desitum.crackTheCode.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;
/**
 * Created by Zmyth97 on 2/11/2015.
 */
public class Tile extends Sprite {
        private float size;
        private float scaleAmount;

        private boolean active;

    public Tile(float size, float scaleAmount, boolean active, Texture texture)
    {
        super(texture, 0, 0, texture.getWidth(), texture.getHeight());

        this.size = size;
        this.scaleAmount = scaleAmount;
        this.active = active;

    }

    public void update(float delta)
    {

    }

}

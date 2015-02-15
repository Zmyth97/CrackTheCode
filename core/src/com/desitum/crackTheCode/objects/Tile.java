package com.desitum.crackTheCode.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Zmyth97 on 2/11/2015.
 */
public class Tile extends Sprite {
    private float scaleAmount;

    private boolean active;

    public Tile(float size, float x, float y, Texture texture) {
        super(texture, 0, 0, texture.getWidth(), texture.getHeight());

        this.setSize(size, size);
        this.scaleAmount = 1;
        this.active = false;
    }

    public void update(float delta) {

    }

}

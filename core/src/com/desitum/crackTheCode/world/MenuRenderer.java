package com.desitum.crackTheCode.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.desitum.crackTheCode.data.Assets;
import com.desitum.crackTheCode.objects.MenuButton;
import com.desitum.crackTheCode.screens.MainScreen;

/**
 * Created by Zmyth97 on 2/9/2015.
 */
public class MenuRenderer {

    private SpriteBatch menuBatch;
    private OrthographicCamera menuCamera;
    private MenuWorld world;

    public MenuRenderer(MenuWorld world, SpriteBatch batch) {
        this.world = world;
        this.menuBatch = batch;

        menuCamera = new OrthographicCamera(MainScreen.SCREEN_WIDTH, MainScreen.SCREEN_HEIGHT);
        menuCamera.position.set(MainScreen.SCREEN_WIDTH / 2, MainScreen.SCREEN_HEIGHT / 2, 0);
    }

    public void render() {
        menuCamera.update();
        menuBatch.setProjectionMatrix(menuCamera.combined);

        for (MenuButton menuItem : this.world.getMenuButtons()) {
            menuBatch.draw(Assets.shadowTexture, menuItem.getX() + 0.2f, menuItem.getY() -0.4f, menuItem.getWidth() * menuItem.getScaleX(), menuItem.getHeight() * menuItem.getScaleY());
            menuItem.draw(menuBatch);
        }

        menuBatch.draw(Assets.menuTitleTexture, 0, 11, 10, 3);
    }

    public void resetCam() {
        menuCamera.position.set(MainScreen.SCREEN_WIDTH / 2, MainScreen.SCREEN_HEIGHT / 2, 0);
    }

    public OrthographicCamera getCam() {
        return menuCamera;
    }
}

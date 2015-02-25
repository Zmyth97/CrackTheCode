package com.desitum.crackTheCode.desktop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.desitum.crackTheCode.CrackTheCode;
import com.desitum.crackTheCode.GooglePlayServicesInterface;

public class DesktopLauncher{
    GooglePlayServicesInterface gps;

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new CrackTheCode(), config);

	}
}

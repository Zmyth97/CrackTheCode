package com.desitum.crackTheCode.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.desitum.crackTheCode.CrackTheCode;
import com.desitum.crackTheCode.GooglePlayServicesInterface;

public class AndroidLauncher extends AndroidApplication implements GooglePlayServicesInterface {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useImmersiveMode = true;
        config.useAccelerometer = false;
        config.useCompass = false;
        config.useWakelock = false;
		initialize(new CrackTheCode(this), config);
	}

    @Override
    public void getLeaderBoard() {

    }

    @Override
    public void submitScore() {

    }

    @Override
    public void login() {

    }

    @Override
    public void logout() {

    }
}

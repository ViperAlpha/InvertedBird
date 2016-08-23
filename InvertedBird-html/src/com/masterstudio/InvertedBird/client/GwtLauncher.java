package com.masterstudio.InvertedBird.client;

import com.viper.InvertedBird.IBGame;
import com.viper.ui.IActivityRequestHandler;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class GwtLauncher extends GwtApplication implements IActivityRequestHandler {
	@Override
	public GwtApplicationConfiguration getConfig () {
		GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(1080/4, 1020/4);
		return cfg;
	}

	@Override
	public ApplicationListener getApplicationListener () {
		return new IBGame(null);
	}

	@Override
	public void showAds(boolean show)
		{
		}
}
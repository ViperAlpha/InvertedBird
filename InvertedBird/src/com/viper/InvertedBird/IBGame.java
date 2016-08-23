package com.viper.InvertedBird;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.viper.IBHelpers.AssetLoader;
import com.viper.Screens.SplashScreen;
import com.viper.ui.IActivityRequestHandler;

public class IBGame extends Game implements ApplicationListener
{
	public static IActivityRequestHandler myRequestHandler;
	
	public IBGame(IActivityRequestHandler handler) {
        myRequestHandler = handler;
	}
	
	@Override
	public void create() {
		myRequestHandler.showAds(true);
//		System.out.println("IBGame Created!");
		AssetLoader.load();
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}

}

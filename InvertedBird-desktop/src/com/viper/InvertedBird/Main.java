package com.viper.InvertedBird;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.viper.InvertedBird.IBGame;
import com.viper.ui.IActivityRequestHandler;

public class Main implements IActivityRequestHandler{
	private static Main application;
	public static void main(String[] args) {
		 if (application == null) {
		            application = new Main();
		        }
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Inverted Bird";
		cfg.useGL20 = false;
		cfg.width = 272;
		cfg.height = 408;
		new LwjglApplication(new IBGame(application), cfg);
	}
	 @Override
	    public void showAds(boolean show) {
	    }
}

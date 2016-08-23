package com.viper.InvertedBird;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
//import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.viper.InvertedBird.IBGame;
import com.viper.ui.IActivityRequestHandler;

public class MainActivity extends AndroidApplication implements IActivityRequestHandler {
	private final static int SHOW_ADS = 1;
	private final static int HIDE_ADS = 0;

	protected static Handler handler = new Handler()
		{
			@Override
			public void handleMessage(Message msg) 
				{
					switch(msg.what) {
						case SHOW_ADS:
						{
							adView.setVisibility(View.VISIBLE);
							break;
						}
						case HIDE_ADS:
						{
							adView.setVisibility(View.GONE);
							break;
						}
					}
				}
		};


		private static final String AD_UNIT_ID = "ca-app-pub-8601680048319595/1086743065";
		public static AdRequest adRequest;
		protected static AdView adView;

		@Override
		public void onCreate(Bundle savedInstanceState) 
			{

				super.onCreate(savedInstanceState);

				//			AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
				//			cfg.useGL20 = false;
				//			cfg.useCompass = true;
				//			cfg.useAccelerometer = true;


				// Create the layout
				RelativeLayout layout = new RelativeLayout(this);

				// Do the stuff that initialize() would do for you
				requestWindowFeature(Window.FEATURE_NO_TITLE);
				getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
						WindowManager.LayoutParams.FLAG_FULLSCREEN);
				getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

				// Create the libgdx View
				View gameView = initializeForView(new IBGame(this), false);

				// Create and setup the AdMob view
				adView = new AdView(this, AdSize.BANNER, AD_UNIT_ID); // Put in your secret key here
				adRequest = new AdRequest();
				adView.loadAd(adRequest);

				// Add the libgdx view
				layout.addView(gameView);

				// Add the AdMob view
				RelativeLayout.LayoutParams adParams = 
						new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 
								RelativeLayout.LayoutParams.WRAP_CONTENT);
				adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
				adParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

				layout.addView(adView, adParams);

				// Hook it all up
				setContentView(layout);


			}

		@Override
		public void showAds(boolean show)
			{
				handler.sendEmptyMessage(show ? SHOW_ADS : HIDE_ADS);
			}


}
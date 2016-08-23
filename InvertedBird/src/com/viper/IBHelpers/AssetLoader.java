package com.viper.IBHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

	public static Texture texture, logoTexture, texture2;
	public static TextureRegion logo, ibLogo, bg, grass, bird, birdDown,
	birdUp, skullUp, skullDown, bar, playButtonUp, playButtonDown,
	ready, gameOver, highScore, scoreboard, star, noStar, retry, soundOn,soundOff;
	public static Animation birdAnimation,birdAnimationMENU;
	public static Sound dead, flap, coin, fall;
	public static BitmapFont font, shadow, whiteFont;
	public static boolean shouldSound = true;
	private static Preferences prefs;

	public static void load() {

		logoTexture = new Texture(Gdx.files.internal("data/logo.png"));
		logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		logo = new TextureRegion(logoTexture, 0, 0, 512, 128);

//		texture = new Texture(Gdx.files.internal("data/texture.png"));
//		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		texture2 = new Texture(Gdx.files.internal("data/2.png"));
		texture2.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		//		playButtonUp = new TextureRegion(texture, 0, 83, 29, 16);
		//		playButtonDown = new TextureRegion(texture, 29, 83, 29, 16);

		playButtonUp = new TextureRegion(texture2, 641, 198, 194, 107);
		playButtonDown = new TextureRegion(texture2, 837, 198, 187, 107);

		playButtonUp.flip(false, true);
		playButtonDown.flip(false, true);

		soundOn = new TextureRegion(texture2,79,535,56,44);
		soundOff = new TextureRegion(texture2,137,537,52,53);
		soundOn.flip(false, true);
		soundOff.flip(false,true);

		//		ready = new TextureRegion(texture, 59, 83, 34, 7);
		ready = new TextureRegion(texture2, 437, 434, 173, 46);

		ready.flip(false, true);

		//		retry = new TextureRegion(texture, 59, 110, 33, 7);
		retry = new TextureRegion(texture2, 437, 481, 177, 46);
		retry.flip(false, true);

		//		gameOver = new TextureRegion(texture, 59, 92, 46, 7);
		gameOver = new TextureRegion(texture2, 437, 317, 240, 50);

		gameOver.flip(false, true);


		//		highScore = new TextureRegion(texture, 59, 101, 48, 7);
		highScore = new TextureRegion(texture2, 436, 374, 191, 51);

		highScore.flip(false, true);

		//		scoreboard = new TextureRegion(texture, 111, 83, 97, 37);
		scoreboard = new TextureRegion(texture2, 540, 46, 333, 145);
		scoreboard.flip(false, true);

		//		star = new TextureRegion(texture, 152, 70, 10, 10);
		//		noStar = new TextureRegion(texture, 165, 70, 10, 10);

		star = new TextureRegion(texture2, 211, 265, 208, 205);
		noStar = new TextureRegion(texture2, 0, 277, 208, 205);

		star.flip(false, true);
		noStar.flip(false, true);




		//		ibLogo = new TextureRegion(texture, 0, 55, 135, 24);
		ibLogo = new TextureRegion(texture2, 0, 230, 209, 45);
		ibLogo.flip(false, true);

		//bg = new TextureRegion(texture, 0, 0, 136, 43);
//		bg = new TextureRegion(texture2, 0, 0, 540, 182);
		bg = new TextureRegion(texture2,396,226,136,43);
		bg.flip(false, true);


		grass = new TextureRegion(texture2, 0, 483, 165, 11);
		grass.flip(false, true);

		//		birdDown = new TextureRegion(texture, 136, 0, 17, 12);
		//		birdDown = new TextureRegion(texture2, 540, 0, 66, 46);
		birdDown = new TextureRegion(texture2, 738, 0, 66, 46);

		birdDown.flip(false, true);

		//		bird = new TextureRegion(texture, 153, 0, 17, 12);
		//		bird = new TextureRegion(texture2, 606, 0, 66, 46);
		bird = new TextureRegion(texture2, 803, 0, 66, 46);
		bird.flip(false, true);

		//		birdUp = new TextureRegion(texture, 170, 0, 17, 12);
		//		birdUp = new TextureRegion(texture2, 674, 0, 66, 46);
		birdUp = new TextureRegion(texture2, 869, 0, 66, 46);
		birdUp.flip(false, true);

		TextureRegion[] birds = { birdDown, bird, birdUp };
		birdAnimation = new Animation(0.09f, birds);
		birdAnimation.setPlayMode(Animation.LOOP_PINGPONG);

		birdAnimationMENU = new Animation(0.13f, birds);
		birdAnimationMENU.setPlayMode(Animation.LOOP_PINGPONG);

		//		skullUp = new TextureRegion(texture, 192, 0, 24, 14);
		skullUp = new TextureRegion(texture2, 541, 194, 98, 46);
		// Create by flipping existing skullUp
		skullDown = new TextureRegion(skullUp);
		skullDown.flip(false, true);

		//		bar = new TextureRegion(texture, 136, 16, 22, 3);
		bar = new TextureRegion(texture2, 541, 241, 90, 57);
		bar.flip(false, true);

		dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
		flap = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));
		coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));
		fall = Gdx.audio.newSound(Gdx.files.internal("data/fall.wav"));

		font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
		font.setScale(.25f, -.25f);

		whiteFont = new BitmapFont(Gdx.files.internal("data/whitetext.fnt"));
		whiteFont.setScale(.1f, -.1f);

		shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
		shadow.setScale(.25f, -.25f);

		// Create (or retrieve existing) preferences file
		prefs = Gdx.app.getPreferences("InvertedBird");

		if (!prefs.contains("highScore")) {
			prefs.putInteger("highScore", 0);
		}
	}

	public static void setHighScore(int val) {
		prefs.putInteger("highScore", val);
		prefs.flush();
	}

	public static int getHighScore() {
		return prefs.getInteger("highScore");
	}

	public static void play(Sound sound){
		if(AssetLoader.shouldSound){
			sound.play();
		}
	}
	
	public static void setShouldSound(boolean should){
		shouldSound = should;
	}

	public static void dispose() {
		// We must dispose of the texture when we are finished.
		texture2.dispose();

		// Dispose sounds
		dead.dispose();
		flap.dispose();
		coin.dispose();

		font.dispose();
		whiteFont.dispose();
		shadow.dispose();
	}

}
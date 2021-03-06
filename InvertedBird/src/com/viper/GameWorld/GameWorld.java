package com.viper.GameWorld;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.viper.GameObjects.Bird;
import com.viper.GameObjects.ScrollHandler;
import com.viper.IBHelpers.AssetLoader;

public class GameWorld {

	private Bird bird;
	private ScrollHandler scroller;
	private Rectangle top;
//	private Rectangle ground;
	private int score = 0;
	private float runTime = 0;
	private int midPointY;
	private int midPointX;
	private float gameWidth;
	private float gameHeight;
	private GameRenderer renderer;
	
	private GameState currentState;

	public enum GameState {
		MENU, READY, RUNNING, GAMEOVER, HIGHSCORE
	}

	public GameWorld(float screenWidth,float ScreenHeight) {
		currentState = GameState.MENU;
		gameWidth = 136;
		gameHeight = ScreenHeight / (screenWidth / gameWidth);
		midPointY = (int) (gameHeight / 2);
		midPointX = (int) (gameWidth / 2);
		
		bird = new Bird(gameWidth-33, midPointY - 5, 17, 12);
		// The grass should start 66 pixels below the midPointY
		scroller = new ScrollHandler(this, midPointY + 66);
//		ground = new Rectangle(0, midPointY + 66, 137, 11);
		top = new Rectangle(0,-5,137,5);
	}

	public void update(float delta) {
		runTime += delta;

		switch (currentState) {
		case READY:
		case MENU:
			updateReady(delta);
			break;

		case RUNNING:
			updateRunning(delta);
			break;
		default:
			break;
		}

	}

	private void updateReady(float delta) {
		bird.updateReady(runTime);
		scroller.updateReady(delta);
	}

	public void updateRunning(float delta) {
		if (delta > .15f) {
			delta = .15f;
		}

		bird.update(delta);
		scroller.update(delta);

		if (scroller.collides(bird) && bird.isAlive()) {
			scroller.stop();
			bird.die();
			AssetLoader.play(AssetLoader.dead);
			renderer.prepareTransition(255, 255, 255, .3f);

			AssetLoader.play(AssetLoader.fall);
		}
		if (Intersector.overlaps(bird.getBoundingCircle(),top)){
//		if (Intersector.overlaps(bird.getBoundingCircle(), ground)) {

			if (bird.isAlive()) {
				AssetLoader.play(AssetLoader.dead);
				renderer.prepareTransition(255, 255, 255, .3f);

				bird.die();
			}

			scroller.stop();
			bird.decelerate();
			currentState = GameState.GAMEOVER;

			if (score > AssetLoader.getHighScore()) {
				AssetLoader.setHighScore(score);
				currentState = GameState.HIGHSCORE;
			}
		}
	}

	public Bird getBird() 
		{
		return bird;

	}

	public int getMidPointY() {
		return midPointY;
	}
	
	public int getMidPointX(){
		return midPointX;
	}

	public ScrollHandler getScroller() {
		return scroller;
	}

	public int getScore() {
		return score;
	}

	public void addScore(int increment) {
		score += increment;
	}

	public void start() {
		currentState = GameState.RUNNING;
	}

	public void ready() 
	{
		currentState = GameState.READY;
		renderer.prepareTransition(0, 0, 0, 1f);
	}

	public void restart() {
		score = 0;
		bird.onRestart(midPointY - 5);
		scroller.onRestart();
		ready();
	}

	public boolean isReady() {
		return currentState == GameState.READY;
	}

	public boolean isGameOver() {
		return currentState == GameState.GAMEOVER;
	}

	public boolean isHighScore() {
		return currentState == GameState.HIGHSCORE;
	}

	public boolean isMenu() {
		return currentState == GameState.MENU;
	}

	public boolean isRunning() {
		return currentState == GameState.RUNNING;
	}

	public void setRenderer(GameRenderer renderer) {
		this.renderer = renderer;
	}
	
	public float getGameHeight(){
		return gameHeight;
	}
	
	public float getGameWidth(){
		return gameWidth;
	}

}

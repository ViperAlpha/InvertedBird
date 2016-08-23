package com.viper.GameObjects;

import com.badlogic.gdx.math.Vector2;

public class Scrollable {
	
	
	// Protected is similar to private, but allows inheritance by subclasses.
	protected Vector2 position;
	protected Vector2 velocity;
	protected int width;
	protected int height;
	protected boolean isScrolledLeft;
	protected boolean isScrolledRight;

	public Scrollable(float x, float y, int width, int height, float scrollSpeed) {
		position = new Vector2(x, y);
		velocity = new Vector2(scrollSpeed, 0);
		this.width = width;
		this.height = height;
		isScrolledLeft = false;
		isScrolledRight = false;
	}

	public void update(float delta) {
		
		position.sub(velocity.cpy().scl(delta));

		// If the Scrollable object is no longer visible:
		if(position.x > 136){
			isScrolledRight = true;
		}
		
//		if (position.x + width < 0) {
//			isScrolledLeft = true;
//		}
	}

	// Reset: Should Override in subclass for more specific behavior.
	public void reset(float newX) {
		position.x = newX;
		isScrolledLeft = false;
		isScrolledRight = false;
	}

	public void stop() {
		velocity.x = 0;
	}
	
	// Getters for instance variables
	public boolean isScrolledLeft() {
		return isScrolledLeft;
	}
	public boolean isScrolledRight() {
		return isScrolledRight;
	}

	public float getTailX() {
//		return position.x + width;
		return position.x - width;
	}
	

	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}

package com.viper.ui;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.viper.IBHelpers.AssetLoader;

public class SimpleButton {

	public enum Type {
		Clickable,Activable
	}
	private float x, y, width, height;

	private TextureRegion buttonUp;
	private TextureRegion buttonDown;

	private Rectangle bounds;

	private boolean isPressed = false;

	private Type type;
	private boolean ClickableActivated = true;

	public SimpleButton(float x, float y, float width, float height,
			TextureRegion buttonUp, TextureRegion buttonDown,boolean type) {
				this.x = x;
				this.y = y;
				this.width = width;
				this.height = height;
				this.buttonUp = buttonUp;
				this.buttonDown = buttonDown;

				bounds = new Rectangle(x, y, width, height);

				if(type){
					this.type = Type.Clickable;
				}
				else{
					this.type = Type.Activable;
				}

	}

	public boolean isClicked(int screenX, int screenY) {
		return bounds.contains(screenX, screenY);
	}

	public void draw(SpriteBatch batcher) {
		if(type == Type.Clickable){
			if (isPressed) {
				batcher.draw(buttonDown, x, y, width, height);
			}
			else {
				batcher.draw(buttonUp, x, y, width, height);
			}
		}
		else if(type == Type.Activable)
            	{
            		if(ClickableActivated){
            			batcher.draw(buttonUp, x, y, width, height);
            		}
            		else{
            			batcher.draw(buttonDown, x, y, width, height);
            		}
            		
            		if (isPressed){
            			ClickableActivated = !ClickableActivated;
            			isPressed = false;
            		}
            		
            	}

	}

	public boolean isTouchDown(int screenX, int screenY) {

		if (bounds.contains(screenX, screenY)) {
			isPressed = true;
			if(type == Type.Activable){
				AssetLoader.setShouldSound(!ClickableActivated);
				}
			return true;
		}

		return false;
	}

	public boolean isTouchUp(int screenX, int screenY) {
		
		// It only counts as a touchUp if the button is in a pressed state.
		if (bounds.contains(screenX, screenY) && isPressed) {
			isPressed = false;
			return true;
		}

		// Whenever a finger is released, we will cancel any presses.
		isPressed = false;
		return false;
	}
	
	public boolean getClickable(){
		return ClickableActivated;
	}
	
	public Type getType(){
		return type;
	}

}

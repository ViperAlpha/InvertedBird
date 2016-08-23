package com.viper.IBHelpers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

import com.viper.GameObjects.Bird;
import com.viper.GameWorld.GameWorld;
import com.viper.ui.SimpleButton;

public class InputHandler implements InputProcessor {
    private Bird myBird;
    private GameWorld myWorld;

    private List<SimpleButton> menuButtons;

    private SimpleButton playButton;
    private SimpleButton soundButton;

    private float scaleFactorX;
    private float scaleFactorY;

    public InputHandler(GameWorld myWorld, float scaleFactorX,
            float scaleFactorY) {
        this.myWorld = myWorld;
        myBird = myWorld.getBird();

        int midPointY = myWorld.getMidPointY();

        this.scaleFactorX = scaleFactorX;
        this.scaleFactorY = scaleFactorY;

        menuButtons = new ArrayList<SimpleButton>();
        playButton = new SimpleButton(
      		  136/2 - 15,midPointY+30, 
      		  29, 16, AssetLoader.playButtonUp,
                AssetLoader.playButtonDown,true);
        menuButtons.add(playButton);
        
        soundButton = new SimpleButton(123,(2*midPointY) - 14,12,12,
      		  AssetLoader.soundOn,AssetLoader.soundOff,false);
        menuButtons.add(soundButton);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);
        System.out.println(screenX + " " + screenY);
        if (myWorld.isMenu()) {
            playButton.isTouchDown(screenX, screenY);
            soundButton.isTouchDown(screenX, screenY);
        } else if (myWorld.isReady()) {
      	if(!soundButton.isTouchDown(screenX, screenY))
      		myWorld.start();
        }

        myBird.onClick();

        if (myWorld.isGameOver() || myWorld.isHighScore()) {
            myWorld.restart();
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);

        if (myWorld.isMenu()) {
            if (playButton.isTouchUp(screenX, screenY)) {
                myWorld.ready();
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean keyDown(int keycode) {

        // Can now use Space Bar to play the game
        if (keycode == Keys.SPACE) {

            if (myWorld.isMenu()) {
                myWorld.ready();
            } else if (myWorld.isReady()) {
                myWorld.start();
            }

            myBird.onClick();

            if (myWorld.isGameOver() || myWorld.isHighScore()) {
                myWorld.restart();
            }

        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private int scaleX(int screenX) {
        return (int) (screenX / scaleFactorX);
    }

    private int scaleY(int screenY) {
        return (int) (screenY / scaleFactorY);
    }

    public List<SimpleButton> getMenuButtons() {
        return menuButtons;
    }
    
    public void getRotationMatrix(float []matrix){
	    
    }
}
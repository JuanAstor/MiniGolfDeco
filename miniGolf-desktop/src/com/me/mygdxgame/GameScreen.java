package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;

@SuppressWarnings("unused")
public class GameScreen implements Screen, InputProcessor {
	
	private World world;
	private WorldRenderer renderer; 
	private WorldController control;
	private BallController ballCont;
	
	private int width, height; 
	
	@Override 
	public void show() { 
		world = new World(); 
		renderer = new WorldRenderer(world, false); 
		control = new WorldController(world); 
		ballCont = new BallController(world); 
		Gdx.input.setInputProcessor(this); 
	}
	
	@Override 
	public void render(float delta) { 
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		control.update(delta);
		ballCont.update();
		renderer.render();
	}
	
	@Override 
	public void resize(int width, int height) { 
		renderer.setSize(width, height);
		this.width = width; 
		this.height = height; 
	}
	
	
	@Override 
	public void hide() { 
		Gdx.input.setInputProcessor(null);
		
	}
	
	@Override 
	public void pause() { 
		
	}
	
	@Override 
	public void resume() { 
		
	}
	
	@Override 
	public void dispose() { 
		Gdx.input.setInputProcessor(null);
	}

	//a key from keyboard is pressed
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.LEFT)
			control.leftKeyPressed();
		return true;
	}

	@Override
	public boolean keyTyped(char arg0) {
		return false;
	}

	//a key from keyboard is released
	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.LEFT)
			control.leftKeyReleased();
		return true;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		return false;
	}

	//mouse click pressed
	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int button) {
		if(button == Buttons.LEFT)
			control.leftKeyPressed();
		if (button == Buttons.RIGHT){
			control.rightKeyPressed();
		}
		return true;
	}
	
	//mouse click released
	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int button) {
		if(button == Buttons.LEFT)
			control.leftKeyReleased();
		if (button == Buttons.RIGHT){
			control.rightKeyReleased();
		}
		return true;
	}


	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	
}

package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;

@SuppressWarnings("unused")
public class Hole1Screen implements Screen, InputProcessor {
	
	GolfGame golf; 
	private World world;
	private WorldRenderer renderer; 
	private WorldController wControl;
	private BallController ballCont;
	
	private int width, height;
	private int hole = 1;
	
	public Hole1Screen(GolfGame game){
		this.golf = game; 
	}
	
	@Override 
	public void show() { 
			System.out.println("called 1");
			world = new World(hole); //create hole 
			renderer = new WorldRenderer(world, false); 
			wControl = new WorldController(world);
			wControl.setState(hole); //set current hole
			ballCont = new BallController(world);
			Gdx.input.setInputProcessor(this); 
	}
	
	@Override 
	public void render(float delta) { 
		if(this.hole != wControl.getState()){ //if current hole has ended
			this.hole = wControl.getState(); //change hole to be the new hole
			renderer.dispose();
			golf.setScreen(golf.hole2);			
		}		
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		renderer.render();
		ballCont.update();
		wControl.update(delta, renderer.getPower(), renderer.getDir());
		
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
			wControl.leftKeyPressed();
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
			wControl.leftKeyReleased();
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
			wControl.leftKeyPressed();
		if (button == Buttons.RIGHT){
			//wControl.rightKeyPressed();
		}
		return true;
	}
	
	//mouse click released
	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int button) {
		if(button == Buttons.LEFT)
			wControl.leftKeyReleased();
		if (button == Buttons.RIGHT){
			//wControl.rightKeyReleased();
		}
		return true;
	}


	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	
}

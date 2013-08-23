package com.me.mygdxgame;

import java.util.Map; 
import java.util.HashMap;

import org.lwjgl.input.Mouse;

import com.me.mygdxgame.Ball; 
import com.me.mygdxgame.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.*;

@SuppressWarnings("unused")
public class WorldController{
		
	private World world; 
	private Ball ball;
	
	enum Buttons {
		LEFT, RIGHT
	}
	//hashmap of buttons
	static Map<Buttons, Boolean> buttons = new HashMap<WorldController.Buttons, Boolean>(); 
	static {
		buttons.put(Buttons.LEFT,  false); 
		buttons.put(Buttons.RIGHT, false);
	};
	
	public WorldController(World world) {
		this.world = world; 
		this.ball = world.getBall();
	}
	//enable left click (this is for processInput, left click is always enabled)
	public void leftKeyPressed() { 
		buttons.get(buttons.put(Buttons.LEFT, true));
	}
	//disable
	public void leftKeyReleased() {
		buttons.get(buttons.put(Buttons.LEFT, false));
	}
	//get input and update ball 
	public void update(float delta) {
		processInput(delta); 
		ball.update(delta);
	}
	
	//updates the speed and position of the ball when the mouse is clicked
	private void processInput(float delta) {
		//if the left click is enabled then change position
		if(buttons.get(Buttons.LEFT)){
			//change x and y velocity
			ball.getVelocity().x = (-ball.SPEED * delta);
			ball.getVelocity().y = (ball.SPEED * (delta/2));
		}
		if(!buttons.get(Buttons.LEFT) && (!buttons.get(Buttons.RIGHT))){
			ball.getVelocity().x = 0;
			ball.getVelocity().y = 0;
		}
		
	}
	
//	private void processInput() {
//		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){ 
//			float mouseX = Gdx.input.getX(); //get mouse X 
//			float mouseY = (Gdx.input.getY()); //get mouse Y		
//		}
//		
//	}
}

package com.me.mygdxgame;

import java.util.Map; 
import java.util.HashMap;

import org.lwjgl.input.Mouse;

import com.me.mygdxgame.Ball; 
import com.me.mygdxgame.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
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
	
	// enable right click
	public void rightKeyPressed() { 
		buttons.get(buttons.put(Buttons.RIGHT, true));
	}
	//disable
	public void rightKeyReleased() {
		buttons.get(buttons.put(Buttons.RIGHT, false));
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
			//change x velocity (move left)
			if(!ball.bounceX) ball.position.x += 0.1f;
			else ball.position.x -= 0.1f;
		}
		if(buttons.get(Buttons.RIGHT)){
			//change x velocity (move right)
			if(!ball.bounceY) ball.position.y += 0.1f;
			else ball.position.y -= 0.1f;
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

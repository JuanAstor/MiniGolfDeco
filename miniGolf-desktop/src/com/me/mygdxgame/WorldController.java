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
	
	private int leftButtonCount = 0;
	private int rightButtonCount = 0;
	private float speedChange = 100.0f;
	
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
		leftButtonCount = 1;
	}
	
	// enable right click
	public void rightKeyPressed() { 
		buttons.get(buttons.put(Buttons.RIGHT, true));
	}
	//disable
	public void rightKeyReleased() {
		buttons.get(buttons.put(Buttons.RIGHT, false));
		rightButtonCount = 1;
	}
	
	//get input and update ball 
	public void update(float delta) {
		processInput(delta); 
		ball.update(delta);
	}
	
	//updates the speed and position of the ball when the mouse is clicked
	private void processInput(float delta) {
		//if the left click is enabled then change position
		if(leftButtonCount == 1){
			if(speedChange != 0.0){
				speedChange -=0.5f;
				if(speedChange < 0) speedChange = 0;
				System.out.println(speedChange);
			
				if(!ball.bounce) ball.getVelocity().x = (speedChange * delta);
				else ball.getVelocity().x = (-speedChange * delta);
			}
			else{ 
				    //reset speed and button count
					speedChange = 100; 
					leftButtonCount = 0;
				}
		}
		if(rightButtonCount == 1){
			if(speedChange > 0.0){
				speedChange -=0.75f;
				if(speedChange < 0) speedChange = 0;
				System.out.println(speedChange);
				
				if(!ball.bounce) ball.getVelocity().y = (speedChange * delta);
				else ball.getVelocity().y = (-speedChange * delta);
			}
			else {
				speedChange = 100; 
				rightButtonCount = 0;
			}
		}
	}
//		
////		if(buttons.get(Buttons.LEFT)){
////			//change x velocity (move left)
////			if(!ball.bounce) ball.getVelocity().x = (-ball.SPEED * delta*3);
////			else ball.getVelocity().x = (ball.SPEED * delta*3);
////		}
//		if(buttons.get(Buttons.RIGHT)){
//			//change x velocity (move right)
//			if(!ball.bounce) ball.getVelocity().y = (ball.SPEED * delta*3);
//			else ball.getVelocity().y = (-ball.SPEED * delta*3);
//		}
	
//	private void processInput() {
//		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){ 
//			float mouseX = Gdx.input.getX(); //get mouse X 
//			float mouseY = (Gdx.input.getY()); //get mouse Y		
//		}
//		
//	}
}

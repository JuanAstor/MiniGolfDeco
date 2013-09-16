package com.me.mygdxgame;

import java.util.Map; 
import java.util.HashMap;

import org.lwjgl.input.Mouse;
 
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.*;
import com.badlogic.gdx.math.Vector2;

@SuppressWarnings("unused")
public class WorldController{
		
	private World world; 
	private Ball ball;
	private WorldRenderer wRend;
	private direcLogic directionLogic; 
	private int leftButtonCount = 0;
	private int rightButtonCount = 0;
	private float deceleration = 1f;
	private boolean notZero = true;
	private float speedChange = 10000.0f;  
	private int worldState;
	
	
	public WorldController(World world) {
		this.world = world; 
		this.ball = world.getBall();
	}
	
	//enable left click (this is for processInput, left click is always enabled)
	public void leftKeyPressed() { 
		//buttons.get(buttons.put(Buttons.LEFT, true));
	}
	//disable
	public void leftKeyReleased() {
		//buttons.get(buttons.put(Buttons.LEFT, false));
		leftButtonCount = 1;
	}
	
	// enable right click
	public void rightKeyPressed() { 
		//buttons.get(buttons.put(Buttons.RIGHT, true));
	}
	//disable
	public void rightKeyReleased() {
		//buttons.get(buttons.put(Buttons.RIGHT, false));
		rightButtonCount = 1;
	}
	
	public int getState(){
		return worldState;
	}
	public void setState(int value){
		this.worldState = value;
	}
	
	//get input and update ball gets power and dir from direcLogic class
	public void update(float delta, float power, Vector2 dir) {
		processInput(delta, power, dir); 
		if (ball.inHole == true){
			ball.getVelocity().x = 0;
			ball.getVelocity().y = 0;
			this.worldState = 2;
		}		
		ball.update(delta);
	}
	
	//updates the speed and position of the ball when the mouse is clicked
	private void processInput(float delta, float power, Vector2 dir) {
				
		if(leftButtonCount == 0 && rightButtonCount == 0){
			ball.getVelocity().x = 0; 
			ball.getVelocity().y = 0;
		}
		//if left mouse clicked (and released)
		if(leftButtonCount == 1){
			if(this.notZero){
				power -= 5.0f * deceleration;
				deceleration += 0.25;
				System.out.println(power);
				if(power < 0){ 
					power = 0; 
					this.notZero = false;
				}
				if(power > 250) power = 250; 
				if(ball.bounceX) ball.getVelocity().x = ((-(dir.x)) * power * delta); 
				else ball.getVelocity().x = ((dir.x) * power * delta); 
				if(ball.bounceY) ball.getVelocity().y = ((-(dir.y)) * power * delta); 
				else ball.getVelocity().y = ((dir.y) * power * delta);
				
			} else { //reset everything for next move
				ball.bounceX = false; 
				ball.bounceY = false;  
				this.notZero = true;
				deceleration = 1;
				leftButtonCount = 0;
			}
//			if(speedChange != 0.0){
//				speedChange -=50f;
//				if(speedChange < 0) speedChange = 0;
//				if(power > 225) power = 225;
//				if (ball.bounceX) ball.getVelocity().x = (-(dir.x) * (power)) * delta;
//				else ball.getVelocity().x = ((dir.x) * (power)) * delta;
//				if (ball.bounceY) ball.getVelocity().y = (-(dir.y) * (power)) * delta;
//				else ball.getVelocity().y = ((dir.y) * (power)) * delta;
//				
//			} else { 
//				    //reset speed and button count
//					ball.bounceX = false;
//					ball.bounceY = false;
//					speedChange = 10000; 
//					leftButtonCount = 0;
//			}
		}
	}
}

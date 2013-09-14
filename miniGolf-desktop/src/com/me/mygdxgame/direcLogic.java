package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/* 
 * This class controls the logic for the direction trajectory
 */

public class direcLogic {
	
	private DirectionController directController;
	final Vector2 ballPos = new Vector2(); //holds ball position
	final Vector2 currentPos = new Vector2(); //holds current position
	public final Vector2 temp = new Vector2(); //holds updated trajectory
	
	public direcLogic(DirectionController control, Vector2 ballPos){
		this.directController = control; 
		this.ballPos.set(ballPos);
	}
	
	public void update(){
		//get mouse x & y input
		float mouseX = Gdx.input.getX(); 
		float mouseY = Gdx.input.getY();
	
		currentPos.set(mouseX, Gdx.graphics.getHeight() - mouseY);
		//update the current position based on ball position and mouse
		temp.set(currentPos).sub(ballPos);
		temp.mul(-1f);
		//update the angle and power based on the trajectory
		directController.angle = temp.angle(); 
		directController.power = temp.len();
	}
	public Vector2 getDirection(){
		return temp;
	}
	public float getPower(){
		return directController.power;
	}

}

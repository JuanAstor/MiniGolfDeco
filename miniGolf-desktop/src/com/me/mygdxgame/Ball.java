package com.me.mygdxgame;

import com.badlogic.gdx.math.Rectangle; 
import com.badlogic.gdx.math.Vector2; 

public class Ball {
	
	static final float SIZE = 0.2f;
	float SPEED; 
	float ACCELERATION; 
	
	Vector2 position = new Vector2();  
	Rectangle bounds = new Rectangle();  
	
	public Ball(Vector2 position) { 
		this.position = position; 
		this.bounds.width = SIZE; 
		this.bounds.height = SIZE; 
	}
	
	public void setSpeed(float speed) { 
		this.SPEED = speed; 
	}
	public Float getBallSpeed() {
		return this.SPEED; 
	}
	
	public void setAccel(float accel){
		this.ACCELERATION = accel; 
	}
	public Float getAccel(){
		return this.ACCELERATION;
	}
	
	public Rectangle getBounds(){ 
		return this.bounds; 
	}	
	public Vector2 getPosition(){
		return this.position; 
	}	

}

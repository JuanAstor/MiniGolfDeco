package com.me.mygdxgame;

import com.badlogic.gdx.math.*;


public class Ball {
	
	static final float SIZE = 0.2f;
	float SPEED; 
	float ACCELERATION; 
	
	Vector2 position = new Vector2();  
	Polygon bounds;  
	
	public Ball(Vector2 position) { 
		this.position = position; 
		this.bounds = new Polygon(new float[]{0,0,0,SIZE,SIZE,0,SIZE,SIZE});
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
	
	public Polygon getBounds(){ 
		return this.bounds; 
	}	
	public Vector2 getPosition(){
		return this.position; 
	}	

}

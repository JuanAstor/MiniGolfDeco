package com.me.mygdxgame;

import com.badlogic.gdx.math.Rectangle; 
import com.badlogic.gdx.math.Vector2; 

public class Ball {
	
	public enum State { 
		IDLE, MOVING
	}
	 
	static final float SIZE = 0.5f;
	float SPEED; 
	
	Vector2 position = new Vector2(); 
	Vector2 acceleration = new Vector2(); 
	Vector2 velocity = new Vector2(); 
	Rectangle bounds = new Rectangle(); 
	State state = State.IDLE; 
	
	public Ball(Vector2 position) { 
		this.position = position; 
		this.bounds.width = SIZE; 
		this.bounds.height = SIZE; 
	}
	
	public void setSpeed(float speed) { 
		this.SPEED = speed; 
	}
	
	public Float getBallSpeed() {
		return SPEED; 
	}
	

}

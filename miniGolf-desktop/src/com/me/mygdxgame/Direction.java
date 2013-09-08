package com.me.mygdxgame;

import com.badlogic.gdx.math.Vector2; 

public class Direction {
	
	public Vector2 startVelocity = new Vector2(); 
	public Vector2 startPoint = new Vector2(); 
	
	public float getX(float a){
		return startVelocity.x * a + startPoint.x; 
	}
	public float getY(float a){
		return startVelocity.y * a + startPoint.y; 
	}
}
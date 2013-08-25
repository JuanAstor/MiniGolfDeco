package com.me.mygdxgame;

import com.badlogic.gdx.math.Polygon; 
import com.badlogic.gdx.math.Vector2; 

public class Ball {
	
	static final float SIZE = 0.5f;
	float SPEED = 100f;  
	public Boolean bounce = false;
	
	Vector2 position = new Vector2();  
	Vector2 velocity = new Vector2(); 
	Polygon bounds;  
	
	public Ball(Vector2 position) { 
		this.position = position; 
		this.bounds = new Polygon(new float[]{0,0,0,SIZE,SIZE,0,SIZE,SIZE});
		this.bounds.setPosition(position.x, position.y);
		this.bounds.setScale(0.5f, 0.5f);
	}
	public void update(float delta){
		position.add(velocity.cpy().mul(delta));
		this.bounds.setPosition(position.x, position.y);
	}
	public Vector2 getVelocity() {
		return this.velocity;
	}
	public void setSpeed(float speed) { 
		this.SPEED = speed; 
	}
	public Float getBallSpeed() {
		return this.SPEED; 
	}
	
	public Polygon getBounds(){ 
		return this.bounds; 
	}	
	public Vector2 getPosition(){
		return this.position; 
	}	
	public void setPosition(float x, float y){
		this.position = new Vector2(x,y);
		this.bounds.setPosition(x, y);
	}

}

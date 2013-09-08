package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class direcLogic {
	
	private DirectionController direct;
	final Vector2 ballPos = new Vector2();
	final Vector2 currentPos = new Vector2();
	final Vector2 temp = new Vector2(); 
	
	public direcLogic(DirectionController control, Vector2 ballPos){
		this.direct = control; 
		this.ballPos.set(ballPos);
	}
	
	public void update(){
		float mouseX = Gdx.input.getX();
		float mouseY = Gdx.input.getY();
		
		currentPos.set(mouseX, Gdx.graphics.getHeight() - mouseY);
		temp.set(currentPos).sub(ballPos);
		temp.mul(-1f);
		
		direct.angle = temp.angle(); 
		direct.power = temp.len();
	}
	public Vector2 getDirection(){
		return temp;
	}

}

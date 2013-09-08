package com.me.mygdxgame;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.mygdxgame.Direction;
import com.me.mygdxgame.DirectionController;

public class Trajectory extends Actor {
	
	private Direction direct;
	private DirectionController controller; 
	private Sprite trajectorySprite; 
	
	public int trajectoryPoints = 5; //number of images
	public float timeSeparation = 2f;
	
	public Trajectory(DirectionController control, Sprite trajectorySprite){
		this.controller = control; 
		this.trajectorySprite = trajectorySprite; 
		this.direct = new Direction(); 
	}
	
	@Override
	public void act(float delta){
		super.act(delta); 
		direct.startVelocity.set(controller.power, 0f); 
		direct.startVelocity.rotate(controller.angle);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		float t = 0f; 
		float width = 8; //size of image
		float height = 8; 
		
		float timeSeparation = this.timeSeparation;
		
		for(int i = 0; i < trajectoryPoints; i++){
			float x = (width*4) + direct.getX(t);
			float y = (height*4) + direct.getY(t);
			
			batch.setColor(new Color(1,1,1,1));
			batch.draw(trajectorySprite, x, y, width, height);
			
			t += timeSeparation;
		}
	}

}

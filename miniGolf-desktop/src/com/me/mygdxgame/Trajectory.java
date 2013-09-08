package com.me.mygdxgame;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.mygdxgame.DirectionController;

public class Trajectory extends Actor {
	
	public Ball ball; 
	public World world;
	private DirectionController controller; 
	private Sprite trajectorySprite;
	
	public Vector2 startVelocity = new Vector2(); 
	public Vector2 startPoint = new Vector2(); 
	
	public int trajectoryPoints = 5; //number of images
	public float timeSeparation = 2f;
	
	public Trajectory(DirectionController control, Sprite trajectorySprite, World world){
		this.controller = control; 
		this.trajectorySprite = trajectorySprite;
		this.world = world;
	}
	
	@Override
	public void act(float delta){
		super.act(delta); 
		startVelocity.set(controller.power, 0f); 
		startVelocity.rotate(controller.angle);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		float t = 0f; 
		float width = 8; //size of image
		float height = 8; 
		
		float timeSeparation = this.timeSeparation;
		
		for(int i = 0; i < trajectoryPoints; i++){
			float x = (width) + this.getX(t);
			float y = (height) + this.getY(t);
			
			batch.setColor(new Color(1,1,1,1));
			batch.draw(trajectorySprite, (x-6), (y-6), width, height);
			
			t += timeSeparation;
		}
	}
	
	public float getX(float a){
		ball = world.getBall();
		return startVelocity.x * a + ball.getPosition().x ;//+ ball.getPosition().x;//+ startPoint.x; 
	}
	public float getY(float a){
		ball = world.getBall();
		return startVelocity.y * a + ball.getPosition().y;// + startPoint.y; 
	}

}

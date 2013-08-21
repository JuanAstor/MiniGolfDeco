package com.me.mygdxgame;

import com.me.mygdxgame.Ball; 
import com.me.mygdxgame.Block1; 
import com.me.mygdxgame.World; 
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10; 
import com.badlogic.gdx.graphics.OrthographicCamera; 
import com.badlogic.gdx.graphics.glutils.ShapeRenderer; 
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType; 
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

//Renders blocks and actors into the world

@SuppressWarnings("unused")
public class WorldRenderer { 
	
	private World world; 
	private OrthographicCamera cam; 
	
	ShapeRenderer debugRend = new ShapeRenderer(); 
	
	public WorldRenderer (World world) { 
		this.world = world; 
		this.cam = new OrthographicCamera(10,7); 
		this.cam.position.set(5, 3.5f, 0); 
		this.cam.update(); 		
	}
	
	public void render() { 
		//render the blocks
		debugRend.setProjectionMatrix(cam.combined); 
		debugRend.begin(ShapeType.Rectangle); 
		for(Block1 block : world.getBlocks()) {
			Polygon poly = block.getBounds(); 
			float x1 = block.getPosition().x + poly.getX();
			float y1 = block.getPosition().y + poly.getY(); 
			debugRend.setColor(new Color(1,0,0,1)); 
			debugRend.rect(x1, y1, poly.getScaleX(), poly.getScaleY());
			//possible use of poly.getBoundingRectangle....
		}
		//render the ball
		Ball ball = world.getBall(); 
		Rectangle rect = ball.getBounds(); 
		float x1 = ball.getPosition().x + rect.x; 
		float y1 = ball.getPosition().y + rect.y; 
		debugRend.setColor(new Color(0,1,0,1)); 
		debugRend.rect(x1, y1, rect.width, rect.height);
		debugRend.end();
	}
	
}
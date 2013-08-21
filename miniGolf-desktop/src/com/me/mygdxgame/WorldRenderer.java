package com.me.mygdxgame;

import com.me.mygdxgame.Ball; 
import com.me.mygdxgame.Block1; 
import com.me.mygdxgame.World; 
import com.badlogic.gdx.Gdx; 
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10; 
import com.badlogic.gdx.graphics.OrthographicCamera; 
import com.badlogic.gdx.graphics.glutils.ShapeRenderer; 
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType; 
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

//Renders blocks and actors into the world

@SuppressWarnings("unused")
public class WorldRenderer { 
	
	private static final float CAM_WIDTH = 10f; 
	private static final float CAM_HEIGHT = 7f; 
	
	private World world; 
	private OrthographicCamera cam; 
	
	ShapeRenderer debugRend = new ShapeRenderer();
	private Texture ballTexture; 
	private Texture wallTexture;
	private SpriteBatch sprite; 
	private int width; 
	private int height; 
	private float ppuX; //pixels/unit on width
	private float ppuY; //pixels/unit on height
	private boolean debug = false; 
	
	public void setSize(int w, int h) {
		this.width = w; 
		this.height = h;
		ppuX = (float)width/CAM_WIDTH; 
		ppuY = (float)height/CAM_HEIGHT;
	}
	
	
	public WorldRenderer (World world, boolean debug) { 
		this.world = world; 
		this.cam = new OrthographicCamera(10,7); 
		this.cam.position.set(5, 3.5f, 0); 
		this.cam.update(); 
		this.debug = debug;
		sprite = new SpriteBatch(); 
		loadTextures(); 
	}
	
	public void render() {
		sprite.begin(); 
			drawBall(); 
			drawWall();
		sprite.end(); 
		if(debug) {
			debug(); 
		}
		
	}
	
	private void loadTextures() {
		ballTexture = new Texture (Gdx.files.internal("images/ball.png"));
		wallTexture = new Texture (Gdx.files.internal("images/wall.png"));
	}
	private void drawBall() {
		Ball ball = world.getBall(); 
		sprite.draw(ballTexture, ball.getPosition().x * ppuX, ball.getPosition().y * ppuY,
				Ball.SIZE * ppuX, Ball.SIZE * ppuY);
	}
	
	private void drawWall() {
		for(Block1 block : world.getWallBlocks()){
			sprite.draw(wallTexture, block.getPosition().x * ppuX, block.getPosition().y * ppuY, 
					Block1.SIZE * ppuX, Block1.SIZE * ppuY);
		}
	}
	public void debug() { 
		//render the outline of the blocks
		debugRend.setProjectionMatrix(cam.combined); 
		debugRend.begin(ShapeType.Rectangle); 
		for(Block1 block : world.getBlocks()) {
			Polygon poly = block.getBounds(); 
			float x1 = block.getPosition().x + poly.getX();
			float y1 = block.getPosition().y + poly.getY(); 
			debugRend.setColor(new Color(1,0,0,1)); //red
			debugRend.rect(x1, y1, poly.getScaleX(), poly.getScaleY());
		}
		//render the outline of the ball
		Ball ball = world.getBall(); 
		Rectangle rect = ball.getBounds(); 
		float x1 = ball.getPosition().x + rect.x; 
		float y1 = ball.getPosition().y + rect.y; 
		debugRend.setColor(new Color(0,1,0,1)); //green
		debugRend.rect(x1, y1, rect.width, rect.height);
		debugRend.end();
	}
	
}
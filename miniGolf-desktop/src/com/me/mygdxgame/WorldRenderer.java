package com.me.mygdxgame;

import com.me.mygdxgame.Ball; 
import com.me.mygdxgame.Block1; 
import com.me.mygdxgame.World; 
import com.badlogic.gdx.graphics.GL10; 
import com.badlogic.gdx.graphics.OrthographicCamera; 
import com.badlogic.gdx.graphics.glutils.ShapeRenderer; 
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType; 
import com.badlogic.gdx.math.Rectangle; 

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
		
		debugRend.setProjectionMatrix(cam.combined); 
		debugRend.begin(ShapeType.Box); 
		for(Block1 block : world.getBlocks()) {
			
		}
		
	}
	
}
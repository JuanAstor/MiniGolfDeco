package com.me.mygdxgame;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;

public class BallController {

	private World world;
	private Ball ball;
	private Array<Block1> collisionBlocks;
	
	public BallController(World world){
		this.world = world;
		this.ball = world.getBall();	
		this.collisionBlocks = world.getWallBlocks();
	}
	
	
	public void update(){
		collisionDetect();
	}
	
	public void collisionDetect(){
		Polygon ballBounds = ball.getBounds();
		for (Block1 block: collisionBlocks){
			//if(Intersector.overlapConvexPolygons(ballBounds, block.getBounds())){
			//		System.out.println("COLLISION!!!!!");
			//	}
			
		}			
	}		
}

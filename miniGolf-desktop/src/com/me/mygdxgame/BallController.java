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
			if(Intersector.overlapConvexPolygons(ballBounds, block.getBounds())){
					System.out.println("COLLISION!!!!!");
					if(block.dir == Block1.FacingDir.WEST || block.dir == Block1.FacingDir.EAST){
						ball.bounceX = ball.bounceX == true? false:true;
					}
					else if(block.dir == Block1.FacingDir.NORTH || block.dir == Block1.FacingDir.SOUTH){
						ball.bounceY = ball.bounceY == true? false:true;
					}
				}
			
		}			
	}		
}

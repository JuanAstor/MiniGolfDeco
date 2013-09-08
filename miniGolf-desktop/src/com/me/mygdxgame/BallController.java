package com.me.mygdxgame;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;

public class BallController {

	private World world;
	private Ball ball;
	private Array<Block1> collisionBlocks;
	private Array<Block1> openBlocks;
	private Boolean collision = false;
	
	public BallController(World world){
		this.world = world;
		this.ball = world.getBall();	
		this.collisionBlocks = world.getWallBlocks();
		this.collisionBlocks.addAll(world.getHoleBlock());
		this.openBlocks = world.getGroundBlocks();
	}
	
	
	public void update(){
		collisionDetect();
	}
	
	public void collisionDetect(){
		Polygon ballBounds = ball.getBounds();
		for (Block1 block: collisionBlocks){
			Block1.FacingDir blockDir = block.getDir();
			Block1.BlockType blockType = block.getType();
			if(Intersector.overlapConvexPolygons(ballBounds, block.getBounds())){
				
				if(collision == false){
				
				
					if(blockType == Block1.BlockType.WALL){
						if((blockDir == Block1.FacingDir.EAST) ||
								(blockDir == Block1.FacingDir.WEST)){ 
							ball.bounceX = ball.bounceX == true? false:true;
						} else {
							ball.bounceY = ball.bounceY == true? false:true;
						}
						collision = true;
					}
				
					else if(blockType == Block1.BlockType.CORNER){
						ball.bounceX = ball.bounceX == true? false:true;
						ball.bounceY = ball.bounceY == true? false:true;
						collision = true;
					}
				
					else if(blockType == Block1.BlockType.HOLE){
						if(ball.inHole == false){
							System.out.println("YOU WIN!!!! (Not really)");
							ball.inHole = true;
							collision = true;
						}
					}
					
				}
				
			}			
		}
		for (Block1 groundBlock: openBlocks){
			if(Intersector.overlapConvexPolygons(ballBounds, groundBlock.getBounds())){
				if(collision == true){
					collision = false;
				}
			}
		}
	}
	
}	

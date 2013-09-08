package com.me.mygdxgame;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.Array; 
import com.me.mygdxgame.Block1.BlockType;
import com.me.mygdxgame.Block1.FacingDir;

//add blocks in createWorld function to the array
//adds blocks to be rendered, doesn't actually render anything

public class World {

	Array<Block1> block = new Array<Block1>(); 
	Array<Block1> wallArray = new Array<Block1>(); 
	Array<Block1> groundArray = new Array<Block1>(); 
	Array<Block1> cornerArray = new Array<Block1>(); 
	Array<Block1> holeArray = new Array<Block1>(); 
	Ball ball; 
//	Arrow arrow; 
	
	public Array<Block1> getBlocks() { 
		return block; 
	}
	
	public Array<Block1> getWallBlocks() {
		return wallArray;
	}
	public Array<Block1> getGroundBlocks() {
		return groundArray; 
	}
	public Array<Block1> getCornerBlocks() {
		return cornerArray;
	}
	public Array<Block1> getHoleBlock() {
		return holeArray;
	}
	
	public Ball getBall() { 
		return ball; 
	}	
//	public Arrow getArrow(){
//		return arrow; 
//	}
	
	public World() { 
		createHole1(); 
	}
	
	private void createHole1() {
		ball = new Ball(new Vector2(35,35));
		//arrow = new Arrow(new Vector2(30,30));
		cornerArray.add(new Block1(new Vector2(0,0), BlockType.CORNER, FacingDir.EAST));
		cornerArray.add(new Block1(new Vector2(0,300), BlockType.CORNER, FacingDir.NORTH));
		cornerArray.add(new Block1(new Vector2(540,300), BlockType.CORNER, FacingDir.WEST));
		cornerArray.add(new Block1(new Vector2(540,0), BlockType.CORNER, FacingDir.SOUTH));
		
		for (int i = 0; i <= 540; i+=30) {
			for(int j = 0; j <= 300; j+=30){
				//add walls
				if(i == 0 && j < 300){
					wallArray.add(new Block1(new Vector2(i,j), BlockType.WALL, FacingDir.WEST));
				}
				if(i < 540 && j == 300){
					wallArray.add(new Block1(new Vector2(i,j), BlockType.WALL, FacingDir.NORTH));
				}
				if(i == 540 && j < 300){
					wallArray.add(new Block1(new Vector2(i,j), BlockType.WALL, FacingDir.EAST));
				}
				if(i < 540 && j == 0){
					wallArray.add(new Block1(new Vector2(i,j), BlockType.WALL, FacingDir.SOUTH));
				}
				//ground + hole
				if(i > 0 && i < 540 || j < 0 && j < 300){
					if(i == 480 && j == 240){
						holeArray.add(new Block1(new Vector2(i,j), BlockType.HOLE, FacingDir.NORTH));
					} else {
					groundArray.add(new Block1(new Vector2(i,j), BlockType.OPEN, FacingDir.NORTH));
					}
				}
			}
		}
	}
	
	
//	private void createWorld() { 
//		ball = new Ball(new Vector2(2,2)); 
//		
//		wallArray.add(new Block1(new Vector2(0,1), BlockType.WALL, FacingDir.WEST));
//		wallArray.add(new Block1(new Vector2(0,2), BlockType.WALL, FacingDir.WEST));
//		cornerArray.add(new Block1(new Vector2(0,3), BlockType.CORNER, FacingDir.NORTH));
//		cornerArray.add(new Block1(new Vector2(0,0), BlockType.CORNER, FacingDir.EAST));
//		
//		wallArray.add(new Block1(new Vector2(1,3), BlockType.WALL, FacingDir.NORTH));
//		wallArray.add(new Block1(new Vector2(2,3), BlockType.WALL, FacingDir.NORTH));
//		
//		groundArray.add(new Block1(new Vector2(1,1), BlockType.OPEN, FacingDir.SOUTH));
//		groundArray.add(new Block1(new Vector2(1,2), BlockType.OPEN, FacingDir.NORTH));
//		holeArray.add(new Block1(new Vector2(2,2), BlockType.HOLE, FacingDir.NORTH));
//		groundArray.add(new Block1(new Vector2(2,1), BlockType.OPEN, FacingDir.NORTH));
//		
//		wallArray.add(new Block1(new Vector2(1,0), BlockType.WALL, FacingDir.SOUTH));
//		wallArray.add(new Block1(new Vector2(2,0), BlockType.WALL, FacingDir.SOUTH));
//		
//		cornerArray.add(new Block1(new Vector2(3,3), BlockType.CORNER, FacingDir.WEST));
//		wallArray.add(new Block1(new Vector2(3,1), BlockType.WALL, FacingDir.EAST));
//		wallArray.add(new Block1(new Vector2(3,2), BlockType.WALL, FacingDir.EAST));
//		cornerArray.add(new Block1(new Vector2(3,0), BlockType.CORNER, FacingDir.SOUTH));		
//	}
	
}

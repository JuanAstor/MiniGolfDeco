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
		ball = new Ball(new Vector2(250,220));
		//arrow = new Arrow(new Vector2(30,30));
		cornerArray.add(new Block1(new Vector2(210,180), BlockType.CORNER, FacingDir.EAST));
		cornerArray.add(new Block1(new Vector2(210,510), BlockType.CORNER, FacingDir.NORTH));
		cornerArray.add(new Block1(new Vector2(750,510), BlockType.CORNER, FacingDir.WEST));
		cornerArray.add(new Block1(new Vector2(750,180), BlockType.CORNER, FacingDir.SOUTH));
		
		for (int i = 210; i <= 750; i+=30) {
			for(int j = 180; j <= 510; j+=30){
				//add walls
				if(i == 210 && j < 510){
					wallArray.add(new Block1(new Vector2(i,j), BlockType.WALL, FacingDir.WEST));
				}
				if(i < 750 && j == 510){
					wallArray.add(new Block1(new Vector2(i,j), BlockType.WALL, FacingDir.NORTH));
				}
				if(i == 750 && j < 510){
					wallArray.add(new Block1(new Vector2(i,j), BlockType.WALL, FacingDir.EAST));
				}
				if(i < 750 && j == 180){
					wallArray.add(new Block1(new Vector2(i,j), BlockType.WALL, FacingDir.SOUTH));
				}
				//ground + hole
				if(i > 210 && i < 750 || j < 180 && j < 510){
					if(i == 690 && j == 450){
						holeArray.add(new Block1(new Vector2(i,j), BlockType.HOLE, FacingDir.NORTH));
					} else {
					groundArray.add(new Block1(new Vector2(i,j), BlockType.OPEN, FacingDir.NORTH));
					}
				}
			}
		}
	}	
}

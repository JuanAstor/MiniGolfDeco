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
	
	public World() { 
		createWorld(); 
	}
	
	private void createWorld() { 
		ball = new Ball(new Vector2(7,2)); 
		
		wallArray.add(new Block1(new Vector2(0,1), BlockType.WALL, FacingDir.WEST));
		cornerArray.add(new Block1(new Vector2(0,2), BlockType.CORNER, FacingDir.NORTH));
		cornerArray.add(new Block1(new Vector2(0,0), BlockType.CORNER, FacingDir.EAST));
		
		wallArray.add(new Block1(new Vector2(1,2), BlockType.WALL, FacingDir.NORTH));
		groundArray.add(new Block1(new Vector2(1,1), BlockType.OPEN, FacingDir.SOUTH));
		wallArray.add(new Block1(new Vector2(1,0), BlockType.WALL, FacingDir.SOUTH));
		
		cornerArray.add(new Block1(new Vector2(2,2), BlockType.CORNER, FacingDir.WEST));
		wallArray.add(new Block1(new Vector2(2,1), BlockType.WALL, FacingDir.EAST));
		cornerArray.add(new Block1(new Vector2(2,0), BlockType.CORNER, FacingDir.SOUTH));
		
		holeArray.add(new Block1(new Vector2(5,5), BlockType.HOLE, FacingDir.NORTH));
	}
	
}

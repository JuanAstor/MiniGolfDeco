package com.me.mygdxgame;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;
import java.util.*;

public class Block1 {
	
	public enum BlockType {
		CORNER, WALL, HOLE, OPEN
	}
	
	public enum FacingDir {
		WEST, NORTH, SOUTH, EAST
	}
	
	static final float SIZE = 1f;
	
	
	
	Vector2 position = new Vector2();
	Polygon bounds = new Polygon(null);
	BlockType type = BlockType.OPEN;
	FacingDir dir = FacingDir.WEST;
	
	float[] coords = {0, 0, 0, SIZE/2, 0, SIZE, SIZE/2, 0, SIZE/2, SIZE/2, SIZE/2, SIZE,
			SIZE, 0, SIZE, SIZE/2, SIZE, SIZE};
	
	
	public Block1(Vector2 pos, BlockType blockType, FacingDir dir){
		switch(blockType){
			case OPEN:
				
				this.bounds = new Polygon(null);
				break;
			case HOLE:
				float[] coords = {0, 0, SIZE/5, 0, 0, SIZE/5, SIZE/5, SIZE/5};
				this.bounds = new Polygon(coords);
				this.bounds.setPosition((2*SIZE)/5, (2*SIZE)/5);
				break;	
			case CORNER:
				switch(dir){
					case NORTH:
						float[] coords = {0, 0, 0, SIZE, SIZE, 0, SIZE/2, 
								SIZE, SIZE, SIZE/2, SIZE/2, SIZE/2};
						this.bounds = new Polygon(coords);
						break;
					case SOUTH:
						float[] coords = {0, SIZE/2, 0, SIZE, SIZE/2, 0,
								SIZE/2, SIZE/2,	SIZE, 0, SIZE, SIZE};
						this.bounds = new Polygon(coords);
				
				}
				break;	
			case WALL:
				
				break;	
		
		}
		this.position = pos;
		
		
	}
	
}

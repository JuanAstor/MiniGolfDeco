package deco2800.arcade.minigolf;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.Array; 
import deco2800.arcade.minigolf.Block1.BlockType;
import deco2800.arcade.minigolf.Block1.FacingDir;

/* Holds the object positions to be rendered on screen */

public class World {

	// arrays which hold all blocks of a certain type
	Array<Block1> block = new Array<Block1>(); 
	Array<Block1> wallArray = new Array<Block1>();
	Array<Block1> invWallArray = new Array<Block1>();
	Array<Block1> groundArray = new Array<Block1>(); 
	Array<Block1> cornerArray = new Array<Block1>();
	Array<Block1> invCornerArray = new Array<Block1>();
	Array<Block1> hillArray = new Array<Block1>();
	Array<Block1> waterArray = new Array<Block1>();
	Array<Block1> holeArray = new Array<Block1>();
	Array<Block1> teleArray = new Array<Block1>();
	Array<Block1> diagArray = new Array<Block1>();
	
	int holeStartX;
	int holeStartY;
	
	Ball ball;  
	
	/* arrays which hold different block types */
	public Array<Block1> getInvWallBlocks() {
		return invWallArray;
	}
	
	public Array<Block1> getInvCornerBlocks() {
		return invCornerArray;
	}
	
	public Array<Block1> getDiagBlocks() {
		return diagArray;
	}
	
	public Array<Block1> getHillBlocks() {
		return hillArray;
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
	public Array<Block1> getWaterBlocks() {
		return waterArray;
	}
	
	public Array<Block1> getTeleBlocks() {
		return teleArray;
	}
	public Array<Block1> getHoleBlock() {
		return holeArray;
	}
	
	public Ball getBall() { 
		return ball; 
	}
	
	/* construct level/hole based upon @param */
	public World(int state) { 
		if(state == 1) {
			System.out.println("level 1");
			createHole2();  
		}
		if(state == 2){
			System.out.println("level 2");
			createHole2();
		}
		if(state == 3){
			System.out.println("level 3");
			createHole3();
		}
		if(state == 4){
			System.out.println("level 4");
		}
	}
	/* Level 1 */
	private void createHole1() {
		//make sure all arrays are empty upon start
		cornerArray = new Array<Block1>();
		wallArray = new Array<Block1>();
		groundArray = new Array<Block1>(); 
		holeStartX = 250;
		holeStartY = 220;
		ball = new Ball(new Vector2(250,220));
		
		//Allocate positions for each block
		for (int i = 210; i <= 750; i+=15) { // width or x
			for(int j = 180; j <= 510; j+=15){// height or y
				//add corners
				if(i == 210 && j == 180)
					cornerArray.add(new Block1(new Vector2(i,j), BlockType.CORNER, FacingDir.EAST, 0));
				if(i == 210 && j == 510)
					cornerArray.add(new Block1(new Vector2(210,510), BlockType.CORNER, FacingDir.NORTH, 0));
				if(i == 750 && j == 510)
					cornerArray.add(new Block1(new Vector2(750,510), BlockType.CORNER, FacingDir.WEST, 0));
				if(i == 750 && j == 180)
					cornerArray.add(new Block1(new Vector2(750,180), BlockType.CORNER, FacingDir.SOUTH, 0));
				
				//add walls
				else if(i == 210 && j < 510){
					wallArray.add(new Block1(new Vector2(i,j), BlockType.WALL, FacingDir.WEST, 0));
				}
				else if(i < 750 && j == 510){
					wallArray.add(new Block1(new Vector2(i,j), BlockType.WALL, FacingDir.NORTH, 0));
				}
				else if(i == 750 && j < 510){
					wallArray.add(new Block1(new Vector2(i,j), BlockType.WALL, FacingDir.EAST, 0));
				}
				else if(i < 750 && j == 180){
					wallArray.add(new Block1(new Vector2(i,j), BlockType.WALL, FacingDir.SOUTH, 0));
				}
				//ground + hole
				if(i > 210 && i < 750 || j < 180 && j < 510){
					if(i == 690 && j == 450){
						holeArray.add(new Block1(new Vector2(i,j), BlockType.HOLE, FacingDir.NORTH, 0));
					} else {
					groundArray.add(new Block1(new Vector2(i,j), BlockType.OPEN, FacingDir.NORTH, 0));
					}
				}
			}
		}
	}	
	/* level 2 */
	private void createHole2() {
		//make sure all arrays are empty upon start
		cornerArray = new Array<Block1>();
		wallArray = new Array<Block1>();
		groundArray = new Array<Block1>();
		hillArray = new Array<Block1>();
		holeStartX = 630;
		holeStartY = 120;
		ball = new Ball(new Vector2(630,120)); //set ball start position
		
		for (int i = 300; i <= 750; i+=15) { // width or x
			for(int j = 90; j <= 600; j+=15){// height or y
				//add corners
				if(i == 510 && j == 90)
					cornerArray.add(new Block1(new Vector2(i,j), BlockType.CORNER, FacingDir.EAST, 0));
				if(i == 300 && j == 270)
					cornerArray.add(new Block1(new Vector2(i,j), BlockType.CORNER, FacingDir.EAST, 0));
				if(i == 750 && j == 90)
					cornerArray.add(new Block1(new Vector2(i,j), BlockType.CORNER, FacingDir.SOUTH, 0));
				if(i == 750 && j == 420)
					cornerArray.add(new Block1(new Vector2(i,j), BlockType.CORNER, FacingDir.WEST, 0));
				if(i == 570 && j == 600)
					cornerArray.add(new Block1(new Vector2(i,j), BlockType.CORNER, FacingDir.WEST, 0));
				if(i == 300 && j == 600)
					cornerArray.add(new Block1(new Vector2(i,j), BlockType.CORNER, FacingDir.NORTH, 0));

				
				//add walls				
				else if(i > 510 && i < 750 && j == 90)
					wallArray.add(new Block1(new Vector2(i,j), BlockType.WALL, FacingDir.SOUTH, 0));
				else if(i > 300 && i < 510 && j == 270)
					wallArray.add(new Block1(new Vector2(i,j), BlockType.WALL, FacingDir.SOUTH, 0));
				else if(i == 510 && j > 90 && j < 270)
					wallArray.add(new Block1(new Vector2(i,j), BlockType.WALL, FacingDir.WEST, 0));
				else if(i == 300 && j > 270 && j < 600)
					wallArray.add(new Block1(new Vector2(i,j), BlockType.WALL, FacingDir.WEST, 0));				
				else if(i == 750 && j > 90 && j < 420)
					wallArray.add(new Block1(new Vector2(i,j), BlockType.WALL, FacingDir.EAST, 0));
				else if(i == 570 && j > 420 && j < 600)
					wallArray.add(new Block1(new Vector2(i,j), BlockType.WALL, FacingDir.EAST, 0));
				else if(i > 300 && i < 570 && j == 600)
					wallArray.add(new Block1(new Vector2(i,j), BlockType.WALL, FacingDir.NORTH, 0));
				else if(i > 570 && i < 750 && j == 420)
					wallArray.add(new Block1(new Vector2(i,j), BlockType.WALL, FacingDir.NORTH, 0));
				
				//add obstacles (CHANGED TO WATER FOR TESTING)
				else if(i == 510 && j == 270)
					diagArray.add(new Block1(new Vector2(i,j), BlockType.DIAGONAL, FacingDir.NORTH, 1));
				else if(i == 570 && j == 420)
					diagArray.add(new Block1(new Vector2(i,j), BlockType.DIAGONAL, FacingDir.SOUTH, 1));	
			
				
				//ground + hole
				else{
					if(i == 420 && j == 570)
						holeArray.add(new Block1(new Vector2(i,j), BlockType.HOLE, FacingDir.NORTH, 0));
					else{
						groundArray.add(new Block1(new Vector2(i,j), BlockType.OPEN, FacingDir.NORTH, 0));
					}
				}
			}
		}		
	}	
	
	private void createHole3(){		
		//make sure all arrays are empty upon start
		cornerArray = new Array<Block1>();
		wallArray = new Array<Block1>();
		groundArray = new Array<Block1>();
		holeStartX = 780;
		holeStartY = 510;
		ball = new Ball(new Vector2(780,510)); //set ball start position
		

		for (int i = 150; i <= 870; i+=15) { // width or x
			for(int j = 210; j <= 540; j+=15){// height or y
			//add corners	
			if(i == 690 && j == 540)
				cornerArray.add(new Block1(new Vector2(i,j), BlockType.CORNER, FacingDir.NORTH, 0));
			if(i == 150 && j == 540)
				cornerArray.add(new Block1(new Vector2(i,j), BlockType.CORNER, FacingDir.NORTH, 0));
			if(i == 870 && j == 540)
				cornerArray.add(new Block1(new Vector2(i,j), BlockType.CORNER, FacingDir.WEST, 0));
			if(i == 330 && j == 540)
				cornerArray.add(new Block1(new Vector2(i,j), BlockType.CORNER, FacingDir.WEST, 0));
			if(i == 870 && j == 210)
				cornerArray.add(new Block1(new Vector2(i,j), BlockType.CORNER, FacingDir.SOUTH, 0));
			if(i == 150 && j == 210)
				cornerArray.add(new Block1(new Vector2(i,j), BlockType.CORNER, FacingDir.EAST, 0));
			
			//add walls
			else if(i > 690 && i < 870 && j == 540)
				wallArray.add(new Block1(new Vector2(i,j), BlockType.WALL, FacingDir.NORTH, 0));
			else if(i > 150 && i < 330 && j == 540)
				wallArray.add(new Block1(new Vector2(i,j), BlockType.WALL, FacingDir.NORTH, 0));
			else if(i > 330 && i < 690 && j == 360)
				wallArray.add(new Block1(new Vector2(i,j), BlockType.WALL, FacingDir.NORTH, 0));
			else if(i == 870 && j > 210 && j < 540)
				wallArray.add(new Block1(new Vector2(i,j), BlockType.WALL, FacingDir.EAST, 0));
			else if(i == 330 && j > 360 && j < 540)
				wallArray.add(new Block1(new Vector2(i,j), BlockType.WALL, FacingDir.EAST, 0));
			else if(i > 150 && i < 870 && j == 210)
				wallArray.add(new Block1(new Vector2(i,j), BlockType.WALL, FacingDir.SOUTH, 0));
			else if(i == 150 && j > 210 && j < 540)
				wallArray.add(new Block1(new Vector2(i,j), BlockType.WALL, FacingDir.WEST, 0));
			else if(i == 690 && j > 360 && j < 540)
				wallArray.add(new Block1(new Vector2(i,j), BlockType.WALL, FacingDir.WEST, 0));
			
			//add obstacles
			else if(i == 690 && j > 240 && j < 360)
				wallArray.add(new Block1(new Vector2(i,j), BlockType.WALL, FacingDir.WEST, 0));
			else if(i == 510 && j > 210 && j < 330)
				wallArray.add(new Block1(new Vector2(i,j), BlockType.WALL, FacingDir.WEST, 0));
			else if(i == 330 && j > 240 && j < 360)
				wallArray.add(new Block1(new Vector2(i,j), BlockType.WALL, FacingDir.EAST, 0));
			
			else{
				if(i == 240 && j == 510)
					holeArray.add(new Block1(new Vector2(i,j), BlockType.HOLE, FacingDir.NORTH, 0));
				else{
					groundArray.add(new Block1(new Vector2(i,j), BlockType.OPEN, FacingDir.NORTH, 0));
				}
			}
			
			}
		}
		
	}
}

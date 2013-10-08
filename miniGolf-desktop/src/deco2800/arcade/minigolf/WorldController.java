package deco2800.arcade.minigolf;

import java.util.Map; 
import java.util.HashMap;

import org.lwjgl.input.Mouse;
 
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.*;
import com.badlogic.gdx.math.Vector2;

@SuppressWarnings("unused")
public class WorldController{
		
	private World world;
	private GameScreen level;
	private Ball ball;
	private WorldRenderer wRend;
	private DirectionLogic directionLogic; 
	
	boolean leftButtonClick = false; //left mouse click
	private float deceleration = 1f;
	private boolean notZero = true; //speed  
	
	public int hole;
	boolean inHole;
	private int shotNum; //shot counter
	
	public WorldController(World world, int level) {
		this.world = world; 
		this.ball = world.getBall();
		this.hole = level;
		this.inHole = false;
		shotNum = 0;
	}
	
	//left mouse button clicked
	public void leftKeyPressed() { 
	
	}
	//left mouse button released
	public void leftKeyReleased() {
		//buttons.get(buttons.put(Buttons.LEFT, false));
		leftButtonClick = true;
	}
	
	public void setHole(int level){
		this.hole = level;
	}
	public int getHole(){
		return this.hole;
	}
	public int getNumShot(){
		return this.shotNum;
	}
	
	//get input and update ball gets power and dir from direcLogic class
	public void update(float delta, float power, Vector2 dir) {
		processInput(delta, power, dir); 
		if (ball.inHole == true){
			ball.getVelocity().x = 0;
			ball.getVelocity().y = 0;
			if(this.inHole == false){
				this.hole += 1; 
				this.inHole = true;
			}
			//this.worldState = 2;
		}		
		ball.update(delta);
	}
	
	//updates the speed and position of the ball when the mouse is clicked
	private void processInput(float delta, float power, Vector2 dir) {
				
		if(leftButtonClick == false){
			ball.getVelocity().x = 0; 
			ball.getVelocity().y = 0;
		}
		//if left mouse clicked (and released)
		if(leftButtonClick == true){
			if(this.notZero){ //if speed doesn't equal zero
				power -= 6.0f * deceleration; //apply deceleration
				deceleration += 0.25;
				if(power <= 0){
					power = 0; 
					this.notZero = false; //speed is now zero, stop decelerating
				}
				if(power > 250) power = 250; //cap speed (if not already)
				//apply velocity directional changes on wall/object contact
				if(ball.bounceX) ball.getVelocity().x = ((-(dir.x)) * power * delta); 
				else ball.getVelocity().x = ((dir.x) * power * delta); 
				if(ball.bounceY) ball.getVelocity().y = ((-(dir.y)) * power * delta); 
				else ball.getVelocity().y = ((dir.y) * power * delta);
				
			} else { //ball has stopped and waiting for input
				//so reset everything for next move
				ball.bounceX = false; 
				ball.bounceY = false;  
				this.notZero = true;
				deceleration = 1;
				leftButtonClick = false;
				shotNum++; //increase shot counter
			}
		}
	}
}

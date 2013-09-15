package com.me.mygdxgame;

import com.badlogic.gdx.Game; 

public class GolfGame extends Game {
	
	Hole1Screen hole1; 
	Hole2Screen hole2; 

	@Override
	public void create() {
		hole1 = new Hole1Screen(this); 
		hole2 = new Hole2Screen(this);
		setScreen(hole1);
	}
	

}

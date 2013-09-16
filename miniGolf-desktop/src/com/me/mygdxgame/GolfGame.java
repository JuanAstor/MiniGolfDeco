package com.me.mygdxgame;

import com.badlogic.gdx.Game; 

public class GolfGame extends Game {
	
	Hole1Screen hole1; 
	Hole2Screen hole2; 
	MenuScreen menu; 

	@Override
	public void create() {
		menu = new MenuScreen(this);
		hole1 = new Hole1Screen(this); 
		hole2 = new Hole2Screen(this);
		setScreen(menu);
	}
	

}

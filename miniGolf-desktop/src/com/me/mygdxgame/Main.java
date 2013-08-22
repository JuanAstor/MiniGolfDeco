package com.me.mygdxgame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.*; 
import com.badlogic.gdx.audio.*; 

@SuppressWarnings("unused")
public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "miniGolf";
		cfg.useGL20 = false;
		cfg.width = 1024;
		cfg.height = 720;
		
		new LwjglApplication(new GolfGame(), cfg);
	}
}

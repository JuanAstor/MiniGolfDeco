package deco2800.arcade.minigolf;

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
		cfg.width = 1280;
		cfg.height = 720;
		cfg.resizable = false;
		
		new LwjglApplication(new MiniGolf(), cfg);
	}
}

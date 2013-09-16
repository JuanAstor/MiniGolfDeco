package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

@SuppressWarnings("unused")
public class Hole2Screen implements Screen, InputProcessor {
	
	GolfGame golf; 
	private World world;
	private WorldRenderer renderer; 
	private WorldController wControl;
	private BallController ballCont;
	
	private int width, height;
	private int hole = 2;
	private float power;
	
	//Variables for the button
	BitmapFont font1;
	Stage stage;
	TextureAtlas butAtlas;
	Skin butSkin;
	SpriteBatch butBatch;
	TextButton mainButton;
	TextButton resetButton;
	int disposeCount = 0;
	
	public Hole2Screen(GolfGame game){
		this.golf = game; 
	}
	
	@Override 
	public void show() { 
		System.out.println("called 2");
		world = new World(hole); //create hole 
		renderer = new WorldRenderer(world, false); 
		wControl = new WorldController(world);
		wControl.setState(hole); //set current hole
		ballCont = new BallController(world);
		Gdx.input.setInputProcessor(this); 
		
		// Button code
		butBatch = new SpriteBatch();
		butAtlas = new TextureAtlas("images/butttoon.pack");
		butSkin = new Skin();
		butSkin.addRegions(butAtlas);
		font1 = new BitmapFont(Gdx.files.internal("images/font_white.fnt"),false);
	}
	
	@Override 
	public void render(float delta) { 
		if(this.hole != wControl.getState()){ //if current hole has ended
			this.hole = wControl.getState(); //change hole to be the new hole
			//golf.setScreen(golf.hole3); //render the new screen
			
		}		
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		renderer.render();
		ballCont.update();
		capPower();
		wControl.update(delta, this.power, renderer.getDir());
		
		//Button code
		stage.act(delta);		
		butBatch.begin();
		stage.draw();
		butBatch.end();
		
	}
	
	private void capPower(){
		this.power = renderer.getPower(); //get the power
		if(this.power > 50) this.power = 50.0f; //cap it at 50
		this.power = this.power * 5;
	}
	
	@Override 
	public void resize(int width, int height) { 
		renderer.setSize(width, height);
		this.width = width; 
		this.height = height; 
		
		//button code
		if(stage == null){
			stage = new Stage(width,height,true);
		}
		stage.clear();
		InputMultiplexer inpMult = new InputMultiplexer(stage);
		inpMult.addProcessor(stage);
		inpMult.addProcessor(this);
		Gdx.input.setInputProcessor(inpMult);
				
		TextButtonStyle butStyle = new TextButtonStyle();
		butStyle.up = butSkin.getDrawable("butdown");
		butStyle.down = butSkin.getDrawable("butup");
		butStyle.font = font1;
		butStyle.font.setScale(0.5f, 0.5f);
				
		mainButton = new TextButton("Menu", butStyle);
		resetButton = new TextButton("Reset", butStyle);
				
		mainButton.setWidth(100);
		mainButton.setHeight(25);
		mainButton.setX(370);
		mainButton.setY(150);
				
		resetButton.setWidth(100);
		resetButton.setHeight(25);
		resetButton.setX(520);
		resetButton.setY(150);
				
				

		mainButton.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer,int button){
				System.out.println("down");				
				return true;
			}			
			public void touchUp(InputEvent event, float x, float y, int pointer, int button){
				golf.setScreen(golf.menu);
				wControl.setState(1);
			}			
					
		});
				
		resetButton.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer,int button){
				System.out.println("down");				
				return true;
			}
					
			public void touchUp(InputEvent event, float x, float y, int pointer, int button){
				golf.setScreen(golf.hole2);					
			}
				
					
		});
				
				
		stage.addActor(mainButton);
		stage.addActor(resetButton);
				
	}
	
	
	@Override 
	public void hide() { 
		Gdx.input.setInputProcessor(null);
		
	}
	
	@Override 
	public void pause() { 
		
	}
	
	@Override 
	public void resume() { 
		
	}
	
	@Override 
	public void dispose() { 
		Gdx.input.setInputProcessor(null);
		//button code
		if (disposeCount == 1) return;
		butBatch.dispose();
		butAtlas.dispose();
		font1.dispose();		
		butSkin.dispose();
		stage.dispose();
	}
	
	//a key from keyboard is pressed
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.LEFT)
			wControl.leftKeyPressed();
		return true;
	}

	@Override
	public boolean keyTyped(char arg0) {
		return false;
	}

	//a key from keyboard is released
	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.LEFT)
			wControl.leftKeyReleased();
		return true;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		return false;
	}

	//mouse click pressed
	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int button) {
		if(button == Buttons.LEFT)
			wControl.leftKeyPressed();
		if (button == Buttons.RIGHT){
			//wControl.rightKeyPressed();
		}
		return true;
	}
	
	//mouse click released
	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int button) {
		if(button == Buttons.LEFT)
			wControl.leftKeyReleased();
		if (button == Buttons.RIGHT){
			//wControl.rightKeyReleased();
		}
		return true;
	}


	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	
}

package deco2800.arcade.minigolf;

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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;


public class GameScreen implements Screen, InputProcessor {
	
	MiniGolf golf; 
	private World world;
	private WorldRenderer renderer; 
	private WorldController wControl;
	private BallController ballCont;
	
	@SuppressWarnings("unused")
	private int width, height;
	public int level; //hole
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
	
	public GameScreen(MiniGolf game, int hole){
		this.golf = game;
		this.level = hole;
		System.out.println("hole num: "+this.level);
	}
	
	/* get and set current level */
	private int getLevel() {
		return level; 
	}
	private void setLevel(int level) {
		this.level = level; 
	}
	
	@Override 
	public void show() { 
			System.out.println("called 1");
			world = new World(level); //create hole 
			renderer = new WorldRenderer(world, false, this.level); //render objects 
			wControl = new WorldController(world, this.level); //initialise controller
			wControl.setHole(level); //set current hole 
			ballCont = new BallController(world); //initialise controller
			
			// Button code
			butBatch = new SpriteBatch();
			butAtlas = new TextureAtlas("images/butttoon.pack");
			butSkin = new Skin();
			butSkin.addRegions(butAtlas);
			font1 = new BitmapFont(Gdx.files.internal("images/font_white.fnt"),false);			
			
	}
	
	@Override //continuously render all objects
	public void render(float delta) { 
		if(this.level != wControl.getHole()){ //if ball is in hole
			int nextHole = (getLevel() + 1); 
			setLevel(nextHole); //set the next hole
			System.out.println("level is: "+ this.level); 
			golf.setScreen(golf.hole, level); //render next hole			
		}
		//clear everything on screen
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		//render everything and apply updates
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
	/* cap the speed that the player can hit the ball at */
	private void capPower(){
		this.power = renderer.getPower(); //get the power
		if(this.power > 45) this.power = 45.0f; //cap it at 50
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
//		MAINBUTTON.SETX(370);
//		MAINBUTTON.SETY(150);
		
		resetButton.setWidth(100);
		resetButton.setHeight(25);
//		resetButton.setX(520);
//		resetButton.setY(150);
		
		
		//menu
		mainButton.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer,int button){
				System.out.println("down");				
				return true;
			}			
			public void touchUp(InputEvent event, float x, float y, int pointer, int button){
				golf.setScreen(golf.menu);					
			}			
			
		});
		//reset
		resetButton.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer,int button){
				System.out.println("down");				
				return true;
			}
			
			public void touchUp(InputEvent event, float x, float y, int pointer, int button){
				int curLevel = getLevel(); 
				golf.setScreen(golf.hole, curLevel);					
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
	
	public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
		if (stage.getActors().contains(fromActor, true)){
			Gdx.input.setInputProcessor(stage);
		}
	}	
	
	public void exit(InputEvent event,	float x, float y,int pointer, Actor toActor){
		if (stage.getActors().contains(toActor, true)){
			Gdx.input.setInputProcessor(this);
		}		
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

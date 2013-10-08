package deco2800.arcade.minigolf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

@SuppressWarnings("unused")
public class MenuScreen implements Screen, InputProcessor {
	
	MiniGolf golf; 	
	Stage stage;
	BitmapFont font1;
	int disposeCount = 0;
	
	Texture logoTexture;
	Sprite logoSprite;
	SpriteBatch logoBatch;
	
	TextureAtlas butAtlas;
	Skin butSkin;
	SpriteBatch butBatch;
	TextButton mainButton;
	TextButton closeButton;
	
	
	public MenuScreen(MiniGolf game){
		this.golf = game; 
	}
	
	@Override 
	public void show() { 
		butBatch = new SpriteBatch();
		logoTexture = new Texture("images/logo.png");		
		logoSprite = new Sprite(logoTexture);
		
		logoSprite.setX(Gdx.graphics.getWidth()/2 - logoSprite.getWidth()/2);
		logoSprite.setY(Gdx.graphics.getHeight()/2 - Gdx.graphics.getHeight()/7);
		
		logoBatch = new SpriteBatch();
		
		butAtlas = new TextureAtlas("images/button.pack");
		butSkin = new Skin();
		butSkin.addRegions(butAtlas);
		font1 = new BitmapFont(Gdx.files.internal("images/font_black.fnt"),false);		
	}
	
	@Override 
	public void render(float delta) { 
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		
		logoBatch.begin();
		logoSprite.draw(logoBatch);
		logoBatch.end();
		
		butBatch.begin();
		stage.draw();		
		butBatch.end();
		
		
	}
	
	@Override 
	public void resize(int width, int height) { 
		if(stage == null){
			stage = new Stage(width,height,true);
		}
		stage.clear();
		
		Gdx.input.setInputProcessor(stage);
		
		TextButtonStyle butStyle = new TextButtonStyle();
		butStyle.up = butSkin.getDrawable("butdown");
		butStyle.down = butSkin.getDrawable("butup");
		butStyle.font = font1;
		
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = font1;
		
		
		mainButton = new TextButton("Start Game!", butStyle);
		closeButton = new TextButton("Do nothing", butStyle);
		
		mainButton.setWidth(400);
		mainButton.setHeight(100);
		mainButton.setX(Gdx.graphics.getWidth()/2 - mainButton.getWidth()/2);
		mainButton.setY(Gdx.graphics.getHeight()/2 - 2*(mainButton.getHeight()/1.2f));
		
		closeButton.setWidth(400);
		closeButton.setHeight(100);
		closeButton.setX(Gdx.graphics.getWidth()/2 - closeButton.getWidth()/2);
		closeButton.setY(Gdx.graphics.getHeight()/2 - 2*(closeButton.getHeight()/1.2f)- (closeButton.getHeight()+5));
		
		
		mainButton.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer,int button){
				System.out.println("down");
				
				return true;
			}
			
			public void touchUp(InputEvent event, float x, float y, int pointer, int button){
				golf.setScreen(golf.hole, 1);
				
				
			}
			
			
		});
		
		stage.addActor(mainButton);
		stage.addActor(closeButton);
		
	}
	
	
	@Override 
	public void hide() { 
		dispose();
		
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
		
		if (disposeCount == 1) return;
		butBatch.dispose();
		butAtlas.dispose();
		font1.dispose();		
		butSkin.dispose();
		stage.dispose();
		disposeCount = 1;
		
		
		
	}

	//a key from keyboard is pressed
	@Override
	public boolean keyDown(int keycode) {
		return true;
	}

	@Override
	public boolean keyTyped(char arg0) {
		return false;
	}

	//a key from keyboard is released
	@Override
	public boolean keyUp(int keycode) {
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
		
		return true;
	}
	
	//mouse click released
	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int button) {
	
		return true;
	}


	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
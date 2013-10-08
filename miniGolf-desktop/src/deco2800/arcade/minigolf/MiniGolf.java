package deco2800.arcade.minigolf;

import com.badlogic.gdx.Game; 
//import deco2800.arcade.model.Game;
//import deco2800.arcade.model.Game.ArcadeGame;
//import deco2800.arcade.model.Player;
//import deco2800.arcade.breakout.PongBall;
//import deco2800.arcade.client.ArcadeSystem;
//import deco2800.arcade.client.GameClient;
//import deco2800.arcade.client.network.NetworkClient;
//import deco2800.arcade.client.ArcadeSystem;

/* main game class, sets the screens to be displayed */

//@ArcadeGame(id = "MiniGolf")
public class MiniGolf extends Game {
	
	GameScreen hole; 
	MenuScreen menu;
	//private String playerName;
	//public GolfGame(Player player, NetworkClient network){
		//super(player, network); 
		//this.playerName = player.getUsername();
	//}
	
	
	@Override
	public void create() {
		menu = new MenuScreen(this);
		hole = new GameScreen(this, 1);
		setScreen(menu);
	}
	
	@Override
	public void dispose(){
		menu.dispose(); 
		//hole.dispose();
		super.dispose();
	}
	
	public void setScreen(GameScreen hole, int level) {
		super.setScreen(hole); 		
	}
	
	//private static final Game game;
	//static {
	//	game = new Game();
	//	game.id = "MiniGolf";
	//	game.name = "MiniGolf";
	//	game.description = "Search for buried treasure";
	//}
	

}

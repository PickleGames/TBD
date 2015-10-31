package com.mygdx.gameStateManager;

import java.util.Stack;

import com.mygdx.game.TBDGame;
import com.mygdx.gameState.CutScene;
import com.mygdx.gameState.CutScene_End;
import com.mygdx.gameState.Ending;
import com.mygdx.gameState.GameState;
import com.mygdx.gameState.MenuState;
import com.mygdx.gameState.PlayState;
import com.mygdx.gameState.SplashScreen;


public class GameStateManager {

	private TBDGame game;
	private Stack<GameState> gameState;
	private int currentState;
	
	public static final int MENU = 0;
	public static final int PLAY = 1;
	public static final int PAUSE = 2;
	public static final int END = 3;
	public static final int SPLASH = 4;
	public static final int CUTSCENE1 = 5;
	public static final int CUTSCENE_END = 6;
	public GameState getCurrentGameState(){
		return gameState.peek();
	}
	public GameStateManager(TBDGame game) {
		this.game = game;
		gameState = new Stack<GameState>();
		
		pushState(SPLASH);
	}

	public TBDGame game(){
		return game;
	}

	public GameState getState(int state){
		if(state == PLAY) {
			currentState = state;
			return new PlayState(this);
		}
		if(state == MENU) {
			currentState = state;
			return new MenuState(this);
		}
		if(state == SPLASH) {
			currentState = state;
			return new SplashScreen(this);
		}
		if(state == CUTSCENE1){
			currentState = state;
			return new CutScene(this);
		}
		if(state == CUTSCENE_END){
			currentState = state;
			return new CutScene_End(this);
		}
		if(state == END){
			currentState = state;
			return new Ending(this);
		}
		return null;
	}
	
	public int getCurrentState(){
		return currentState;
	}

	public void setState(int state){
		popState();
		pushState(state);
	}
	
	public void update(float dt){
		gameState.peek().update(dt);;
	}
	
	public void draw(){
		gameState.peek().render();
	}
	
	public void pushState(int state){
		gameState.push(getState(state));
	}
	
	public void popState(){
		GameState gs = gameState.pop();
		gs.dispose();
	}

	public void dispose() {
		
		
	}

}

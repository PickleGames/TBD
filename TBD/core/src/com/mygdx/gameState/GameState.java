package com.mygdx.gameState;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.TBDGame;
import com.mygdx.gameStateManager.GameStateManager;

public abstract class GameState {
	protected GameStateManager gsm;
	protected TBDGame game;
	
	protected SpriteBatch spriteBatch;
	protected OrthographicCamera cam;
	protected OrthographicCamera hudCam;
	

	
 	protected GameState(GameStateManager gsm){
		this.gsm = gsm;
		
		game = gsm.game();
		spriteBatch = game.getSpriteBatch();
		cam = game.getCam();
		hudCam = game.getHudCam();
		
	}
	

	public abstract void init();
	public abstract void update(float dt);
	public abstract void render();
	public abstract void handleInput();
	public abstract void dispose();
	
	
}

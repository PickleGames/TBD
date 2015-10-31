package com.mygdx.gameState;

import com.mygdx.gameState.levelState.LevelStateManager;
import com.mygdx.gameStateManager.GameStateManager;

public class PlayState extends GameState{
	
//	private BitmapFont font = new BitmapFont();
	private LevelStateManager lsm;
	public static final int PLAY = 2;

	public LevelStateManager getLSM(){
		return lsm;
	}
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {
		lsm = new LevelStateManager(game, gsm);
	}

	@Override
	public void update(float dt) {
//		System.out.println("it update");
		lsm.update(dt);
	}

	@Override
	public void render() {
//		System.out.println("it draw");
		lsm.render();

	}

	@Override
	public void handleInput() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		lsm.dispose();
		
	}
	
}

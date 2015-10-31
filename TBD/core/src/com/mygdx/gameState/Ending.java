package com.mygdx.gameState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.gameStateManager.GameStateManager;
import com.mygdx.jukeBox.MusicBox;

public class Ending extends GameState{

	private Sprite s;
	private float x,y;
	private float timeElapsed;
	public Ending(GameStateManager gsm) {
		super(gsm);
		init();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		MusicBox.play("Ending");
		s = new Sprite(new Texture("Image/Credits.png"));
		s.setSize(cam.viewportWidth, s.getHeight());
		x = s.getX();
		y = -3000;
		
	}
	
	
	@Override
	public void update(float dt) {
		
		timeElapsed += dt;
		
		if(timeElapsed < 70f){
			y+=.8f;
		}
		if(timeElapsed >= 120f || Gdx.input.isKeyPressed(Keys.ESCAPE)){
			MusicBox.stop("Ending");
			gsm.setState(GameStateManager.MENU);
		}
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		spriteBatch.begin();
		spriteBatch.draw(s.getTexture(), x, y);
//		spriteBatch.draw(s.getTexture(), 0,0, TBDGame.WIDTH, TBDGame.HEIGHT);
		spriteBatch.end();
	}

	@Override
	public void dispose() {
		s.getTexture().dispose();
		
	}

	@Override
	public void handleInput() {
	}

}

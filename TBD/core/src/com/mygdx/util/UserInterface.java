package com.mygdx.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.entities.player.Bob;
import com.mygdx.gameState.levelState.LevelState;

public class UserInterface {
	
	private Bob bob;
	private BitmapFont font =  new BitmapFont();
	private BitmapFont ammo =  new BitmapFont();
	private BitmapFont health =  new BitmapFont();
	
	public UserInterface(Bob bob){
		this.bob = bob;
	}
	
	public void update(float dt){
		if(bob.getCurrentGun().getCurrentClip() < 6) ammo.setColor(Color.RED);
		else ammo.setColor(Color.WHITE);
		
		if((int)(bob.getHealth() / bob.getMaxHealth() * 100) < 30 ) health.setColor(Color.RED);
		else health.setColor(Color.WHITE);
		
	}
	
	public void render(SpriteBatch spriteBatch){
		update(Gdx.graphics.getDeltaTime());
//		spriteBatch.draw(texture, LevelState.getCam().position.x - LevelState.getCam().viewportWidth / 2,
//								  LevelState.getCam().position.y - LevelState.getCam().viewportHeight / 2,
//								  500, 100);
		ammo.draw(spriteBatch, String.valueOf("Ammo: " + bob.getCurrentGun().getCurrentClip()),
					LevelState.getCam().position.x - LevelState.getCam().viewportWidth / 2,
					LevelState.getCam().position.y - LevelState.getCam().viewportHeight / 2 + 100);
		font.draw(spriteBatch, "fps: "+ Gdx.graphics.getFramesPerSecond(), 
					LevelState.getCam().position.x -  LevelState.getCam().viewportWidth / 2 , 
					LevelState.getCam().position.y -  LevelState.getCam().viewportHeight / 2 + 20);
		health.draw(spriteBatch, "HP: "+ (int)(bob.getHealth() / bob.getMaxHealth() * 100) + "%",
				LevelState.getCam().position.x -  LevelState.getCam().viewportWidth / 2 , 
				LevelState.getCam().position.y -  LevelState.getCam().viewportHeight / 2 + 50);
//		font.draw(spriteBatch, "Shootable : "+ bob.getCurrentGun().isShootable(),
//				LevelState.getCam().position.x -  LevelState.getCam().viewportWidth / 2 , 
//				LevelState.getCam().position.y -  LevelState.getCam().viewportHeight / 2 + 70);
		
	}
	
	public void dispose(){
		font.dispose();
		ammo.dispose();
		health.dispose();
	}
}

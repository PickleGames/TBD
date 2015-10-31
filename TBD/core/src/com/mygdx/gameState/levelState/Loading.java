package com.mygdx.gameState.levelState;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Loading extends LevelState{

	private float timeElapsed;
	private Sprite s;
	protected Loading(LevelStateManager lsm) {
		super(lsm);
		init();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		s = new Sprite(new Texture("Image/loading.png"));
		s.setSize(cam.viewportWidth, cam.viewportHeight);
		
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		timeElapsed +=dt;
		if(timeElapsed>3){
			lsm.setCurrentLevel(lsm.getCurrentLevel() + 1);
			int nextLevel = lsm.getCurrentLevel();
			lsm.setState(nextLevel);
		}
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		spriteBatch.begin();
		spriteBatch.draw(s.getTexture(), cam.position.x-cam.viewportWidth/2,cam.position.y-cam.viewportHeight/2, cam.viewportWidth, cam.viewportHeight);
		spriteBatch.end();
	}

	@Override
	public void dispose() {
		s.getTexture().dispose();
		
	}

}

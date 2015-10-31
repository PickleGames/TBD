package com.mygdx.gameState.levelState;

import java.util.List;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.entities.enemy.Enemy;
import com.mygdx.entities.player.Bob;
import com.mygdx.game.TBDGame;
import com.mygdx.util.LevelFileReader;
import com.mygdx.util.UserInterface;

public abstract class LevelState {
	protected TBDGame game;
	protected LevelStateManager lsm;
	
	protected SpriteBatch spriteBatch;
	protected static OrthographicCamera cam;
	
	private static TiledMap map;
	protected OrthogonalTiledMapRenderer mapRenderer;
	protected LevelFileReader levelFileReader;
	
	protected List<Enemy> enemyList;
	protected boolean isReset;
	protected boolean isCompleted;
	private static UserInterface UI;
	
	private static Bob bob;
	
	
	protected boolean isPause;
	
	public Bob getBob(){
		return bob;
	}
	
	public void setBob(Bob bob){
		LevelState.bob = bob;
	}
	protected LevelState(LevelStateManager lsm){
		this.lsm = lsm;
		
		game = lsm.game();
		spriteBatch = game.getSpriteBatch();
		cam = game.getCam();
		levelFileReader = new LevelFileReader();

	}
	
	

	//Getter Setter
/////////////////////////////////////////////////////////////////////////

	public List<Enemy> getEnemyList() {
		return enemyList;
	}
	
	public void setEnemyList(List<Enemy> enemyList) {
		this.enemyList = enemyList;
	}

	public static OrthographicCamera getCam(){ return cam; }
	
	public static TiledMap getMap() {
		return map;
	}

	public static void setMap(TiledMap map) {
		LevelState.map = map;
	}
	
	
	public static UserInterface getUI() {
		return UI;
	}

	public void setUI(UserInterface ui){
		LevelState.UI = ui;
	}

	//////////////////////////////////////////////////////////////////////////
	public abstract void init();
	public abstract void update(float dt);
	public abstract void render();
	public abstract void dispose();
	public void restartLevel(){
		lsm.setState(lsm.getCurrentLevel());
	}

}

package com.mygdx.gameState.levelState;

import java.util.Stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.entities.Entity;
import com.mygdx.game.TBDGame;
import com.mygdx.gameStateManager.GameStateManager;

public class LevelStateManager {

//	private LevelState levelState;
	private Stack<LevelState> levelState;
	private TBDGame game;

	GameStateManager gsm;

	private int highScore;
	
	public int getHighScore() {
		return highScore;
	}
	
	public void setHighScore(int killCount) {
		highScore = killCount;
	}

	public LevelState getCurrentState(){
		return levelState.peek();
	}
//	enum GameLevel {
//		LEVEL0 , LEVEL1, LEVEL2;
//	}
//	
//	GameLevel gameLevel = GameLevel.LEVEL0;
	
	public static final int LEVEL1_0 = 0; 
	public static final int LEVEL1_1 = 1;
	public static final int LEVEL1_2 = 2;
	public static final int LEVEL1_3 = 3;
	public static final int LEVEL1_4 = 4;
	public static final int LEVEL1_5 = 5;
	public static final int LEVEL1_6 = 6;
	
	
	public static final int LOADING = 777;
	public static final int ENDING = 666;
	public static final int STORY = 123;
	public static final int HORDE = 321;
	private static int currentLevel = 0;

	public void setCurrentLevel(int level){
		currentLevel = level;
	}
	public int getCurrentLevel(){
		return currentLevel;
	}
	
	public static Stage pauseStage;
	public static Window pauseWindow, deadWindow;
	public static Skin skin;
	
	
	private void newPauseWindow(){
		pauseWindow.padTop(64);
		pauseWindow.padLeft(0);
		pauseWindow.setSize(TBDGame.WIDTH / 2, TBDGame.HEIGHT / 2);
		pauseWindow.setPosition(TBDGame.WIDTH / 2 - pauseWindow.getWidth() / 2, TBDGame.HEIGHT / 2 - pauseWindow.getHeight() / 2);
		pauseWindow.setColor(pauseWindow.getColor().r, 
							  pauseWindow.getColor().g,
							  pauseWindow.getColor().b, 1f);
		
		Table table = new Table(skin);
		TextButton backButton, menuButton;
		backButton = new TextButton("Back", skin, "default");
		menuButton = new TextButton("Menu", skin, "default");
		menuButton.setSize(100,100);
		backButton.setSize(100,100);
		
		menuButton.addListener(new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y) {
					// TODO Auto-generated method stub
					pauseWindow.setVisible(false);
					//levelState.peek().dispose();
					gsm.setState(GameStateManager.MENU);
				}
		});

		backButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				pauseWindow.setVisible(false);
				levelState.peek().isPause = false;
			}
		});
		table.setSize(pauseWindow.getWidth(), pauseWindow.getHeight());
		table.align(Align.center);
		table.add(backButton).expandX().padBottom(100);
		table.row();
		table.add(menuButton);
		table.setColor(table.getColor().r, table.getColor().g, table.getColor().b, 1f);

//		table.debug();
		pauseWindow.add(table);
		
		
	}
	
	private void newDeadWindow(){
		deadWindow.padTop(64);
		deadWindow.padLeft(0);
		deadWindow.setMovable(false);
		deadWindow.setSize(TBDGame.WIDTH / 2, TBDGame.HEIGHT / 2);
		deadWindow.setPosition(TBDGame.WIDTH / 2 - deadWindow.getWidth() / 2, TBDGame.HEIGHT / 2 - deadWindow.getHeight() / 2);
		deadWindow.setColor(deadWindow.getColor().r, 
							deadWindow.getColor().g,
							deadWindow.getColor().b, 1f);
		
		Table table = new Table(skin);
		TextButton continueButton, menuButton;
		continueButton = new TextButton("Continue ?", skin, "default");
		menuButton = new TextButton("Menu", skin, "default");
		menuButton.setSize(100,100);
		continueButton.setSize(100,100);
		
		menuButton.addListener(new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y) {
					// TODO Auto-generated method stub
					pauseWindow.setVisible(false);
					//levelState.peek().dispose();
					gsm.setState(GameStateManager.MENU);
				}
		});

		continueButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				levelState.peek().restartLevel();
				deadWindow.setVisible(false);	
			}
		});
		table.setSize(deadWindow.getWidth(),deadWindow.getHeight());
		table.align(Align.center);
		table.add(continueButton).expandX().padBottom(100);
		table.row();
		table.add(menuButton);
		table.setColor(table.getColor().r, table.getColor().g, table.getColor().b, 1f);

//		table.debug();
		deadWindow.add(table);
		
		
	}
	
	public LevelStateManager(TBDGame game, GameStateManager gsm){
		this.game = game;
		this.gsm = gsm;
		
		levelState = new Stack<LevelState>();
		
		skin = new Skin(Gdx.files.internal("Skin/uiskin.json"));
		pauseStage = new Stage();
		pauseWindow = new Window("Pause", skin);
		pauseWindow.setVisible(false);
		deadWindow = new Window("You Dead!", skin);
		deadWindow.setVisible(false);
		pauseStage.addActor(pauseWindow);
		pauseStage.addActor(deadWindow);
		newPauseWindow();
		newDeadWindow();
		
		Gdx.input.setInputProcessor(pauseStage);
		Entity.setLsm(this);
		
		game.getCam().viewportHeight = TBDGame.HEIGHT / TBDGame.SCALE;
		game.getCam().viewportWidth = TBDGame.WIDTH / TBDGame.SCALE;
		

//		System.out.println(Bob.fixPosition.x);
//		System.out.println(Bob.fixPosition.y);

		
	}
	
	public TBDGame game(){
		return game;
	}
	
	public void update(float dt){
		levelState.peek().update(dt);
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
			LevelStateManager.pauseWindow.setVisible(true);
			levelState.peek().isPause = true;
		}
		if(levelState.peek().getBob().isDead()){
			LevelStateManager.deadWindow.setVisible(true);
			levelState.peek().isPause = true;
		}
	}
	
	public void render(){
		levelState.peek().render();
		pauseStage.draw();
	}
	
	public void dispose(){
		levelState.peek().dispose();
	}
	

	public void setState(int state){
		popState();
		pushState(state);
	}
	
	public void pushState(int state){
		levelState.push(getState(state));
	}
	
	private LevelState getState(int state) {
		if(state == LEVEL1_0){
			return new Level_1_0(this);
		}
		if(state == LEVEL1_1){
			return new Level_1_1(this);
		}
		if(state == LEVEL1_2){
			return new Level_1_2(this);
		}
		if(state == LEVEL1_3){
			return new Level_1_3(this);
		}
		if(state == LOADING){
			return new Loading(this);
		}
//		if(state == ENDING){
//			return new Ending(this);
//		}
		if(state == HORDE){
			return new HordeMode(this);
		}
		return null;
	}

	public void popState(){
		LevelState ls = levelState.pop();
		ls.dispose();
	}


}



package com.mygdx.gameState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.entities.background.Background;
import com.mygdx.game.TBDGame;
import com.mygdx.gameState.levelState.LevelStateManager;
import com.mygdx.gameStateManager.GameStateManager;
import com.mygdx.jukeBox.MusicBox;
import com.mygdx.util.TextBox;

public class MenuState extends GameState {
	// private Texture background;
	private boolean isMoveBack = false;
	private Texture TBD;
	private Background background;
	BitmapFont font = new BitmapFont();

	TextBox play, option, help, exit;
	private static String[] texts = new String[] { "Play", "Option", "Help","Exit" };
	private TextBox[] textBox = new TextBox[texts.length];
	// private boolean[] boxClicked = new boolean[texts.length];
	private String dlc = "DLC required to unlock this option!";
	Stage stage;
	Skin skin;
	Window playWindow, optionWindow, helpWindow;
	Label dlcLabel;

	public MenuState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {
		stage = new Stage(new ScreenViewport(), spriteBatch);
		
		skin = new Skin(Gdx.files.internal("Skin/uiskin.json"));
		dlcLabel = new Label(dlc, skin);
		dlcLabel.setVisible(false);
		dlcLabel.setPosition(TBDGame.WIDTH / 2 - dlcLabel.getTextBounds().width/2, TBDGame.HEIGHT / 2 - dlcLabel.getTextBounds().height/2);
		
		playWindow = new Window("PLAY", skin);
		playWindow.setVisible(false);
		playWindow.setMovable(false);
		optionWindow = new Window("OPTION", skin);
		optionWindow.setVisible(false);
		optionWindow.setMovable(false);
		helpWindow = new Window("HELP", skin);
		helpWindow.setVisible(false);
		helpWindow.setMovable(false);
		
		newPlayWindow();
//		newOptionWindow();
		newHelpWindow();
		
//		playWindow.debug();
//		optionWindow.debug();
//		helpWindow.debug();

//		stage.addActor(optionWindow);
		stage.addActor(playWindow);
		stage.addActor(helpWindow);
		stage.addActor(dlcLabel);
		Gdx.input.setInputProcessor(stage);
		
		
		// background = new Texture("Image/Menu/background.png");
		background = new Background(TBDGame.WIDTH, TBDGame.HEIGHT, 200);
		TBD = new Texture("Image/Menu/TBD.png");

		for (int i = 0, j = 450; i < textBox.length; i++, j -= 100) {
			textBox[i] = new TextBox(texts[i], 150, j);
		}

		MusicBox.play("MenuMusic");
		MusicBox.loop("MenuMusic", true);
	}
	
	private void newPlayWindow(){
		playWindow.padTop(64);
		playWindow.padLeft(0);
		playWindow.setSize(TBDGame.WIDTH / 2, TBDGame.HEIGHT / 2);
		playWindow.setPosition(400, 100);
		playWindow.setColor(playWindow.getColor().r, 
							  playWindow.getColor().g,
							  playWindow.getColor().b, 1f);
		
		Table table = new Table(skin);
		TextButton backButton, storyModeButton, hordeModeButton;
		storyModeButton = new TextButton("Story Mode", skin);
		storyModeButton.setSize(100, 100);
		storyModeButton.pad(0, 0, 0, 0);
		storyModeButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				background.dispose();
				TBD.dispose();
				gsm.setState(GameStateManager.CUTSCENE1);
			}
		});

		hordeModeButton = new TextButton("Horde", skin);
		hordeModeButton.setSize(100, 100);
		hordeModeButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				background.dispose();
				TBD.dispose();
				
				gsm.setState(GameStateManager.PLAY);
				((PlayState)gsm.getCurrentGameState()).getLSM().pushState(LevelStateManager.HORDE);
			}
		});
		backButton = new TextButton("Back", skin, "default");
		backButton.setSize(100,100);

		backButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				textBox[0].setClicked(false);
				playWindow.setVisible(false);
				boxClicked = false;
				isMoveBack = true;
			}
		});
		table.setSize(playWindow.getWidth(), playWindow.getHeight());
		table.align(Align.center);
		table.add(storyModeButton);
		table.getCell(storyModeButton).padRight(100);
		table.add(hordeModeButton);
		table.row();
		table.add(backButton).expandX();
		
		table.setColor(table.getColor().r, table.getColor().g, table.getColor().b, 1f);

//		table.debug();
		playWindow.add(table);
		
		
	}
	//DLC REQUIRED
	private void newOptionWindow(){
		optionWindow.padTop(64);
		optionWindow.padLeft(0);
		optionWindow.setSize(TBDGame.WIDTH / 2, TBDGame.HEIGHT / 2);
		optionWindow.setPosition(400, 100);
		
		optionWindow.setColor(optionWindow.getColor().r, 
							  optionWindow.getColor().g,
							  optionWindow.getColor().b, 1f);
		
		Table table = new Table(skin);
		TextButton backButton;
		backButton = new TextButton("Back", skin, "default");
		backButton.setWidth(100);
		backButton.setHeight(100);
		backButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				textBox[1].setClicked(false);
				optionWindow.setVisible(false);
				boxClicked = false;
				isMoveBack = true;
			}
		});
		table.setSize(optionWindow.getWidth(), optionWindow.getHeight());
		table.align(Align.center);
		table.add(backButton);
		

		table.debug();
		optionWindow.add(table);
		
	}

	private void newHelpWindow(){
		helpWindow.padTop(64);
		helpWindow.padLeft(0);
		helpWindow.setSize(TBDGame.WIDTH / 2, TBDGame.HEIGHT / 2);
		helpWindow.setPosition(400, 100);
		
		helpWindow.setColor(helpWindow.getColor().r, 
							  helpWindow.getColor().g,
							  helpWindow.getColor().b, 1f);
		
		Table table = new Table(skin);
		String text = "Movement: W-A-S-D\n"
				+ 	  "Shooting: Left Mouse\n"
				+	  "Reload  : r\n"
				+ 	  ""
				+ 	  "	             Objective\n"
				+ 	  "Kill all enemies to advance next level.\n"
				+ 	  "Hint: stop shooting to regen.";
		Label help = new Label(text, skin);
		TextButton backButton;
		backButton = new TextButton("Back", skin, "default");
		backButton.setWidth(100);
		backButton.setHeight(100);
		backButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				textBox[2].setClicked(false);
				helpWindow.setVisible(false);
				boxClicked = false;
				isMoveBack = true;
			}
		});
		table.setSize(helpWindow.getWidth(), helpWindow.getHeight());
		table.align(Align.center);
		table.add(help).align(Align.center).padBottom(50);
		table.row();
		table.add(backButton);
		
//		table.debug();
		helpWindow.add(table);
		
	}

	private boolean boxClicked;
	public void moveBack(float dt){
		for (TextBox box : textBox) {
			box.resetPos();
		}
		for(TextBox t : textBox){
			t.updateTimeElapsed(dt);
		}
		
		if(textBox[0].getX() >= 150 && textBox[1].getX() >= 150 && textBox[2].getX() >= 150 && textBox[3].getX() >= 150) isMoveBack= false;
		
		textBox[0].moveBox(150, 0);
		textBox[1].moveBox(150, 0.2f);
		textBox[2].moveBox(150, 0.4f);
		textBox[3].moveBox(150, 0.6f);

	}
	float mouseX, mouseY;
	float timeElapseddlc = 0;
	boolean dlcClicked = false;
	@Override
	public void update(float dt) {
		mouseX = Gdx.input.getX();
		mouseY = TBDGame.HEIGHT - Gdx.input.getY();

		if (boxClicked) {
			for(TextBox t : textBox){
				t.updateTimeElapsed(dt);
			}
			
			if(textBox[0].isClicked()){
				textBox[1].moveBox(TBDGame.WIDTH, 0);
				textBox[2].moveBox(TBDGame.WIDTH, 0.2f);
				textBox[3].moveBox(TBDGame.WIDTH, 0.4f);
			}
			else if(textBox[1].isClicked()){
				textBox[0].moveBox(TBDGame.WIDTH, 0);
				textBox[2].moveBox(TBDGame.WIDTH, 0.2f);
				textBox[3].moveBox(TBDGame.WIDTH, 0.4f);
			}
			else if(textBox[2].isClicked()){
				textBox[0].moveBox(TBDGame.WIDTH, 0);
				textBox[1].moveBox(TBDGame.WIDTH, 0.2f);
				textBox[3].moveBox(TBDGame.WIDTH, 0.4f);
			}
		} else {
			for (TextBox box : textBox) {
				box.update(dt, mouseX, mouseY);
			}
		}

		
		if(isMoveBack) {
			moveBack(dt);
		}

		if (textBox[0].isClicked()) {
			playWindow.setVisible(true);
			boxClicked = true;
		} else if (textBox[1].isClicked()) {
//			optionWindow.setVisible(true);
			dlcLabel.setVisible(true);
			textBox[1].setClicked(false);
			dlcClicked = true;
//			boxClicked = true;
		} else if (textBox[2].isClicked()) {
			helpWindow.setVisible(true);
			boxClicked = true;
		} else if (textBox[3].isClicked()) {
			boxClicked = true;
			Gdx.app.exit();
		}

		if(dlcClicked )timeElapseddlc+=dt;
		if(timeElapseddlc > 1.5f) {
			dlcLabel.setVisible(false);
			timeElapseddlc = 0;
			dlcClicked = false;
		}
		background.update(dt);
	}

	@Override
	public void render() {

		spriteBatch.begin();
			background.render(spriteBatch);
			spriteBatch.draw(TBD, 100, TBDGame.HEIGHT - TBD.getHeight() - 20);
	
			for (TextBox box : textBox) {
				box.render(spriteBatch);
			}
		spriteBatch.end();
		
		stage.draw();
		
		
	}

	@Override
	public void handleInput() {
	}

	@Override
	public void dispose() {
		for (int i = 0; i < textBox.length; i++) {
			TextBox tb = textBox[i];
			tb.dispose();
		}
		stage.dispose();
		background.dispose();
		MusicBox.stop("MenuMusic");
	}

}

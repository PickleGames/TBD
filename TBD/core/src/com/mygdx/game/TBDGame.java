package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.gameStateManager.GameStateManager;
import com.mygdx.jukeBox.MusicBox;
import com.mygdx.jukeBox.SoundBox;

public class TBDGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private OrthographicCamera cam;
	private OrthographicCamera hudCam;
	public static final int SCALE = 2;
	public static final float STEP = 1 / 60f;
//	private float accum;

	public static int HEIGHT;
	public static int WIDTH;

	private GameStateManager gsm;
	


	public void create() {
		HEIGHT = Gdx.graphics.getHeight();
		WIDTH = Gdx.graphics.getWidth();
		
		batch = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, WIDTH, HEIGHT);
		hudCam = new OrthographicCamera();
		hudCam.setToOrtho(false, WIDTH, HEIGHT);
		
		
		MusicBox.loadMusic("Sound/Music/Melody Techno_Checkpoint 5 - Building Up.mp3", "Melody");
		MusicBox.loadMusic("Sound/Music/Screen_Music.mp3", "MenuMusic");
		MusicBox.loadMusic("Sound/Music/YEH.mp3", "YEH");
		
		MusicBox.loadMusic("Sound/Music/ALAH AKBAR.mp3", "Alah");
		MusicBox.loadMusic("Sound/Music/Battle Music Final.mp3", "Alah_Remix");
		MusicBox.loadMusic("Sound/Music/finish ending song.mp3", "Ending");
		MusicBox.loadMusic("Sound/Music/Cutscene.mp3", "Cutscene");
		
		SoundBox.loadSound("Sound/Effect/chewchew.wav", "chew");

		gsm = new GameStateManager(this);


	}


	public void render() {
//		accum += Gdx.graphics.getDeltaTime();
		
//		while(accum >= STEP){
//			accum-= STEP;
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			gsm.update(Gdx.graphics.getDeltaTime());
			gsm.draw();
			//System.out.println(Gdx.graphics.getFramesPerSecond());
			
//		}

	}
	
	public void dispose(){
		batch.dispose();
		gsm.dispose();
		MusicBox.getMusic("Melody").dispose();
		MusicBox.getMusic("Alah").dispose();
		MusicBox.getMusic("Alah_Remix").dispose();
		MusicBox.getMusic("MenuMusic").dispose();
		MusicBox.getMusic("YEH").dispose();
		MusicBox.getMusic("Ending").dispose();
		MusicBox.getMusic("Cutscene").dispose();
	}
	
	public SpriteBatch getSpriteBatch() {
		return batch;
	}

	public OrthographicCamera getCam() {
		return cam;
	}

	public OrthographicCamera getHudCam() {
		return hudCam;
	}


}

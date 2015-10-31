package com.mygdx.gameState;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.TBDGame;
import com.mygdx.gameStateManager.GameStateManager;
import com.mygdx.jukeBox.MusicBox;
import com.mygdx.util.tween.SpriteAccessor;

public class CutScene_End extends GameState{

	private Sprite image1, image2, image3, image4, image5, image6, black;
	private TweenManager tweenManager;
	Texture i1 = new Texture("Image/Cutscenes/end/Scene 1.png");
	Texture i2 = new Texture("Image/Cutscenes/end/Scene 2.png");
	Texture i3 = new Texture("Image/Cutscenes/end/Scene 3.png");
	Texture i4 = new Texture("Image/Cutscenes/end/Scene 4.png");
	Texture i5 = new Texture("Image/Cutscenes/end/Scene 5.png");
	Texture i6 = new Texture("Image/Cutscenes/end/Scene 6.png");
	
	public CutScene_End(GameStateManager gsm) {
		super(gsm);
		init();
		//BRB 
	}

	@Override
	public void init() {
		cam.viewportHeight = TBDGame.HEIGHT ;
		cam.viewportWidth = TBDGame.WIDTH ;
		cam.position.set(TBDGame.WIDTH / 2, TBDGame.HEIGHT / 2, 0);

		cam.update();
		
		tweenManager = new TweenManager();
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());
		


		Texture black1 = new Texture("Image/Cutscenes/black.png");
		
		image1 = new Sprite(i1);
		image2 = new Sprite(i2);
		image3 = new Sprite(i3);
		image4 = new Sprite(i4);
		image5 = new Sprite(i5);
		image6 = new Sprite(i6);

		black = new Sprite(black1);
		
		image1.setSize(TBDGame.WIDTH, TBDGame.HEIGHT);

		image2.setSize(TBDGame.WIDTH, TBDGame.HEIGHT);
		image3.setSize(TBDGame.WIDTH, TBDGame.HEIGHT);
		image4.setSize(TBDGame.WIDTH, TBDGame.HEIGHT);
		image5.setSize(TBDGame.WIDTH, TBDGame.HEIGHT);
		image6.setSize(TBDGame.WIDTH, TBDGame.HEIGHT);
		black.setSize(TBDGame.WIDTH, TBDGame.HEIGHT);
		
		//1
		Tween.set(image1, SpriteAccessor.ALPHA).target(0).start(tweenManager);
		Tween.to(image1, SpriteAccessor.ALPHA, 5).target(1).start(tweenManager);
		Tween.to(image1, SpriteAccessor.ALPHA, 5).target(0).delay(5).start(tweenManager);
		
		//2
		Tween.set(image2, SpriteAccessor.ALPHA).target(0).start(tweenManager);
		Tween.to(image2, SpriteAccessor.ALPHA, 5).target(1).delay(10).start(tweenManager);
		Tween.to(image2, SpriteAccessor.ALPHA, 5).target(0).delay(15).start(tweenManager);
		
		//3
		Tween.set(image3, SpriteAccessor.ALPHA).target(0).start(tweenManager);
		Tween.to(image3, SpriteAccessor.ALPHA, 5).target(1).delay(20).start(tweenManager);
		Tween.to(image3, SpriteAccessor.ALPHA, 5).target(0).delay(25).start(tweenManager);
		
		//4
		Tween.set(image4, SpriteAccessor.ALPHA).target(0).start(tweenManager);
		Tween.to(image4, SpriteAccessor.ALPHA, 5).target(1).delay(30).start(tweenManager);
		Tween.to(image4, SpriteAccessor.ALPHA, 5).target(0).delay(35).start(tweenManager);
		
		//5
		Tween.set(image5, SpriteAccessor.ALPHA).target(0).start(tweenManager);
		Tween.to(image5, SpriteAccessor.ALPHA, 5).target(1).delay(45).start(tweenManager);
		Tween.to(image5, SpriteAccessor.ALPHA, 5).target(0).delay(50).start(tweenManager);
		
		//6
		Tween.set(image6, SpriteAccessor.ALPHA).target(0).start(tweenManager);
		Tween.to(image6, SpriteAccessor.ALPHA, 4).target(1).delay(55).start(tweenManager);
		Tween.to(image6, SpriteAccessor.ALPHA, 4).target(0).delay(59).start(tweenManager);

		
		Tween.set(black, SpriteAccessor.ALPHA).target(0).start(tweenManager);
		Tween.to(black, SpriteAccessor.ALPHA, 4).target(1).delay(62).start(tweenManager);
		Tween.to(black, SpriteAccessor.ALPHA, 4).target(0).delay(66).start(tweenManager);
		
		MusicBox.play("Cutscene");
		MusicBox.loop("Cutscene", true);
		
	}
	
	private float timeElapsed = 0;
	@Override
	public void update(float dt) {
		timeElapsed += dt;
		if(timeElapsed >= 66 || Gdx.input.isKeyPressed(Keys.ESCAPE)){
			MusicBox.stop("Cutscene");
			gsm.setState(GameStateManager.END);
			
		}
		tweenManager.update(dt);
	}

	@Override
	public void render() {

		spriteBatch.setProjectionMatrix(cam.combined);
		cam.update();
		spriteBatch.begin();
		image1.draw(spriteBatch);
		image2.draw(spriteBatch);
		image3.draw(spriteBatch);
		image4.draw(spriteBatch);
		image5.draw(spriteBatch);
		image6.draw(spriteBatch);
		black.draw(spriteBatch);
		spriteBatch.end();
	}

	@Override
	public void handleInput() {
	}

	@Override
	public void dispose() {
		//MusicBox.stop("CutScene");
		 i1.dispose();
		 i2.dispose();
		 i3.dispose();
		 i4.dispose();
		 i5.dispose();
		 i6.dispose();
	}

}

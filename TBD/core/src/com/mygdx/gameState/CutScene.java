package com.mygdx.gameState;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.TBDGame;
import com.mygdx.gameState.levelState.LevelStateManager;
import com.mygdx.gameStateManager.GameStateManager;
import com.mygdx.jukeBox.MusicBox;
import com.mygdx.util.tween.SpriteAccessor;

public class CutScene extends GameState{

	private Sprite image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, black;
	private TweenManager tweenManager;
	

	Texture i1 = new Texture("Image/Cutscenes/start/cut1.png");
	Texture i2 = new Texture("Image/Cutscenes/start/cut2.png");
	Texture i3 = new Texture("Image/Cutscenes/start/cut3.png");
	Texture i4 = new Texture("Image/Cutscenes/start/Scene 1.png");
	Texture i5 = new Texture("Image/Cutscenes/start/Scene 2.png");
	Texture i6 = new Texture("Image/Cutscenes/start/Scene 3.png");
	Texture i7 = new Texture("Image/Cutscenes/start/Scene 4.png");
	Texture i8 = new Texture("Image/Cutscenes/start/Scene 5.png");
	Texture i9 = new Texture("Image/Cutscenes/start/Scene 6.png");
	Texture i10 = new Texture("Image/Cutscenes/start/Scene 7.png");
	Texture black1 = new Texture("Image/Cutscenes/black.png");
	
	public CutScene(GameStateManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {
		tweenManager = new TweenManager();
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());
		
		
		image1 = new Sprite(i1);
		image2 = new Sprite(i2);
		image3 = new Sprite(i3);
		image4 = new Sprite(i4);
		image5 = new Sprite(i5);
		image6 = new Sprite(i6);
		image7 = new Sprite(i7);
		image8 = new Sprite(i8);
		image9 = new Sprite(i9);
		image10 = new Sprite(i10);
		black = new Sprite(black1);
		
		image1.setSize(TBDGame.WIDTH, TBDGame.HEIGHT);
		image2.setSize(TBDGame.WIDTH, TBDGame.HEIGHT);
		image3.setSize(TBDGame.WIDTH, TBDGame.HEIGHT);
		image4.setSize(TBDGame.WIDTH, TBDGame.HEIGHT);
		image5.setSize(TBDGame.WIDTH, TBDGame.HEIGHT);
		image6.setSize(TBDGame.WIDTH, TBDGame.HEIGHT);
		image7.setSize(TBDGame.WIDTH, TBDGame.HEIGHT);
		image8.setSize(TBDGame.WIDTH, TBDGame.HEIGHT);
		image9.setSize(TBDGame.WIDTH, TBDGame.HEIGHT);
		image10.setSize(TBDGame.WIDTH, TBDGame.HEIGHT);
		black.setSize(TBDGame.WIDTH, TBDGame.HEIGHT);
		
		Tween.set(image1, SpriteAccessor.ALPHA).target(0).start(tweenManager);
		Tween.to(image1, SpriteAccessor.ALPHA, 4).target(1).start(tweenManager);
		Tween.to(image1, SpriteAccessor.ALPHA, 4).target(0).delay(4).start(tweenManager);
		

		Tween.set(image2, SpriteAccessor.ALPHA).target(0).start(tweenManager);
		Tween.to(image2, SpriteAccessor.ALPHA, 4).target(1).delay(8).start(tweenManager);
		Tween.to(image2, SpriteAccessor.ALPHA, 4).target(0).delay(12).start(tweenManager);
		
		Tween.set(image3, SpriteAccessor.ALPHA).target(0).start(tweenManager);
		Tween.to(image3, SpriteAccessor.ALPHA, 4).target(1).delay(16).start(tweenManager);
		Tween.to(image3, SpriteAccessor.ALPHA, 4).target(0).delay(20).start(tweenManager);
		
		//1
		Tween.set(image4, SpriteAccessor.ALPHA).target(0).start(tweenManager);
		Tween.to(image4, SpriteAccessor.ALPHA, 4).target(1).delay(24).start(tweenManager);
		Tween.to(image4, SpriteAccessor.ALPHA, 4).target(0).delay(28).start(tweenManager);
		//2
		Tween.set(image5, SpriteAccessor.ALPHA).target(0).start(tweenManager);
		Tween.to(image5, SpriteAccessor.ALPHA, 5).target(1).delay(33).start(tweenManager);
		Tween.to(image5, SpriteAccessor.ALPHA, 10).target(0).delay(43).start(tweenManager);
		//3
		Tween.set(image6, SpriteAccessor.ALPHA).target(0).start(tweenManager);
		Tween.to(image6, SpriteAccessor.ALPHA, 5).target(1).delay(48).start(tweenManager);
		Tween.to(image6, SpriteAccessor.ALPHA, 5).target(0).delay(53).start(tweenManager);
		//4
		Tween.set(image7, SpriteAccessor.ALPHA).target(0).start(tweenManager);
		Tween.to(image7, SpriteAccessor.ALPHA, 4).target(1).delay(57).start(tweenManager);
		Tween.to(image7, SpriteAccessor.ALPHA, 4).target(0).delay(61).start(tweenManager);
		//5
		Tween.set(image8, SpriteAccessor.ALPHA).target(0).start(tweenManager);
		Tween.to(image8, SpriteAccessor.ALPHA, 5).target(1).delay(66).start(tweenManager);
		Tween.to(image8, SpriteAccessor.ALPHA, 5).target(0).delay(71).start(tweenManager);
		//6
		Tween.set(image9, SpriteAccessor.ALPHA).target(0).start(tweenManager);
		Tween.to(image9, SpriteAccessor.ALPHA, 5).target(1).delay(76).start(tweenManager);
		Tween.to(image9, SpriteAccessor.ALPHA, 5).target(0).delay(81).start(tweenManager);
		//7
		Tween.set(image10, SpriteAccessor.ALPHA).target(0).start(tweenManager);
		Tween.to(image10, SpriteAccessor.ALPHA, 4).target(1).delay(85).start(tweenManager);
		Tween.to(image10, SpriteAccessor.ALPHA, 4).target(0).delay(89).start(tweenManager);
		
		Tween.set(black, SpriteAccessor.ALPHA).target(0).start(tweenManager);
		Tween.to(black, SpriteAccessor.ALPHA, 3).target(1).delay(92).start(tweenManager);
		Tween.to(black, SpriteAccessor.ALPHA, 3).target(0).delay(95).start(tweenManager);
		
		MusicBox.play("Cutscene");
		MusicBox.loop("Cutscene", true);
	}

	
	private float timeElapsed = 0;
	@Override
	public void update(float dt) {
		timeElapsed += dt;
		if(timeElapsed >= 97 || Gdx.input.isKeyPressed(Keys.ESCAPE)){

			gsm.setState(GameStateManager.PLAY);
			((PlayState)gsm.getCurrentGameState()).getLSM().pushState(LevelStateManager.LEVEL1_0);
		}
		tweenManager.update(dt);
		
	}

	@Override
	public void render() {
		spriteBatch.begin();
		image1.draw(spriteBatch);
		image2.draw(spriteBatch);
		image3.draw(spriteBatch);
		image4.draw(spriteBatch);
		image5.draw(spriteBatch);
		image6.draw(spriteBatch);
		image7.draw(spriteBatch);
		image8.draw(spriteBatch);
		image9.draw(spriteBatch);
		image10.draw(spriteBatch);
		black.draw(spriteBatch);
		spriteBatch.end();
	}

	@Override
	public void handleInput() {
	}

	@Override
	public void dispose() {
		MusicBox.stop("Cutscene");

		 i1.dispose();
		 i2.dispose();
		 i3.dispose();
		 i4.dispose();
		 i5.dispose();
		 i6.dispose();
		 i7.dispose();
		 i8.dispose();
		 i9.dispose();
		 i10.dispose();
		 black1.dispose();
	}

}

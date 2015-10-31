package com.mygdx.gameState;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.TBDGame;
import com.mygdx.gameStateManager.GameStateManager;
import com.mygdx.util.tween.SpriteAccessor;

public class SplashScreen extends GameState{
	
	private Sprite image1, image2;
	private TweenManager tweenManager;
	Texture lol;
	public SplashScreen(GameStateManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {
		tweenManager = new TweenManager();
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());
		

		Texture i1 = new Texture("Image/Splash/image1.png");
		Texture i2 = new Texture("Image/Splash/companyLogo.PNG");
		image1 = new Sprite(i1);
		image2 = new Sprite(i2);
		
		image1.setSize(TBDGame.WIDTH, TBDGame.HEIGHT);
		image2.setSize(TBDGame.WIDTH, TBDGame.HEIGHT);
		Tween.set(image1, SpriteAccessor.ALPHA).target(0).start(tweenManager);
		Tween.to(image1, SpriteAccessor.ALPHA, 4).target(1).start(tweenManager);
		Tween.to(image1, SpriteAccessor.ALPHA, 4).target(0).delay(4).start(tweenManager);
		

		Tween.set(image2, SpriteAccessor.ALPHA).target(0).start(tweenManager);
		Tween.to(image2, SpriteAccessor.ALPHA, 4).target(1).delay(8).start(tweenManager);
		Tween.to(image2, SpriteAccessor.ALPHA, 4).target(0).delay(12).start(tweenManager);
	}

	
	private float timeElapsed = 0;
	@Override
	public void update(float dt) {
		timeElapsed += dt;
		if(timeElapsed >= 20 || Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
			gsm.setState(GameStateManager.MENU);
		}
		
		tweenManager.update(dt);
		
	}

	@Override
	public void render() {
		spriteBatch.begin();
		image1.draw(spriteBatch);
		image2.draw(spriteBatch);

		spriteBatch.end();
		
	}

	@Override
	public void handleInput() {
	}

	@Override
	public void dispose() {
		image1.getTexture().dispose();
		image2.getTexture().dispose();
	}

}

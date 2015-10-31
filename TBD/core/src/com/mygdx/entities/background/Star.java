package com.mygdx.entities.background;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Star {
	public static Texture star0 = new Texture("Image/Menu/Star/star1.png");
	public static Texture star1 = new Texture("Image/Menu/Star/star2.png");
	public static Texture star2 = new Texture("Image/Menu/Star/star3.png");
	public static Texture star3 = new Texture("Image/Menu/Star/star4.png");
	public static Texture star4 = new Texture("Image/Menu/Star/star5.png");
	public static Texture star5 = new Texture("Image/Menu/Star/star6.png");
	
	private Texture mainStar;
	private Sprite sprite;
	private float A;
	
	private float elapsedTime = 0;
	public float getElapsedTime(){
		return elapsedTime;
	}
	
	public Star(float x, float y, int i){

		mainStar = randomStar(i);
		sprite = new Sprite(mainStar);
		sprite.setX(x);
		sprite.setY(y);
		sprite.setColor(1, 1, 1, A);
	}
	
	public Texture randomStar(int i){
		switch(i){
			case 1: return star0; 
			case 2: return star1;
			case 3: return star2;
			case 4: return star3;
			case 5: return star4;
			case 6: return star5;
			default: return star1;
		}
	}
	public void update(float dt, boolean b){
		elapsedTime += dt;
		setAlpha(b);
	}
	
	public void setAlpha(boolean b){
		if(b){
			A += 0.005f;
			sprite.setAlpha(A);
		}else{
			A -= 0.005f;
			sprite.setAlpha(A);
		}
		
	}
	public void render(SpriteBatch spriteBatch){
//		spriteBatch.draw(mainStar, position.x, position.y);
		sprite.draw(spriteBatch);
	}
	
	public void dispose(){
		star0.dispose();
		star1.dispose();
		star2.dispose();
		star3.dispose();
		star4.dispose();
		star5.dispose();
	}
}

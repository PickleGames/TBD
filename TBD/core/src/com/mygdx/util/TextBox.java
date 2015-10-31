package com.mygdx.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.TBDGame;


public class TextBox {
	private static Texture box;
	private Rectangle rec;
	private BitmapFont font;
	private String m_text;
	private float m_x, m_y;
	private float textPosX, textPosY;
	private boolean isClicked = false;
	

	public float getX() {	
		return m_x;
	}
	
	public boolean isClicked() {
		return isClicked;
	}

	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}

	private float velocityX = 0;
	private static float acceleration = 10f;
	private float timeElapsed = 0;
	
	public void updateTimeElapsed(float dt) {
		timeElapsed += dt;
	}
	
	public void moveBox(float X, float delay){
		if(m_x < X && timeElapsed >= delay){
			velocityX += acceleration;
			m_x += velocityX ;
			rec.x += velocityX ;
			textPosX += velocityX;
		}
		if(m_x >= X){
			m_x = X;
			rec.x = X;
			textPosX = m_x + box.getWidth() / 2 - font.getBounds(m_text).width / 2 ;
			velocityX = 0;
			timeElapsed = 0;
		}
		
//		System.out.println("velociy "+ velocityX);
//		System.out.println("Time "+ timeElapsed);
//		System.out.println();

	}
	
	public TextBox(String text, float x, float y){
		m_text = text;
		m_x = x;
		m_y = y;
		init();
	}
	
	public void init(){
		box = new Texture("Image/Menu/menubox.png");
		rec = new Rectangle(m_x, m_y, box.getWidth(), box.getHeight());

		font = new BitmapFont(Gdx.files.internal("Font/VTSR.fnt"));
		font.setColor(Color.GRAY);

		textPosX = m_x + box.getWidth() / 2 - font.getBounds(m_text).width / 2 ;
		textPosY = font.getCapHeight() + m_y + box.getHeight() / 2 - font.getBounds(m_text).height / 2;

	}
	
	private static final float MAXOFFSET = 40;
	private static final float SPEED = 5;
	private float accum;
	
	public void update(float dt, float mouseX, float mouseY){
		if(rec.contains(mouseX, mouseY)){
			font.setColor(Color.YELLOW);
			updatePos(true);
			if(Gdx.input.isButtonPressed(Buttons.LEFT) && Gdx.input.justTouched()){
				isClicked = true;
				System.out.println("clciked");
			}
		}else{
			font.setColor(Color.GRAY);
			updatePos(false);

		}
		
		
		System.out.println("velociy "+ velocityX);
		System.out.println("Time "+ timeElapsed);
		System.out.println();
	}
	
	public void resetPos() {
		if(m_x >= TBDGame.WIDTH){
			m_x = -200 ;
			rec.x = -200 ;
			textPosX = m_x + box.getWidth() / 2 - font.getBounds(m_text).width / 2 ;
			velocityX = 0;
			timeElapsed = 0;
		}
	}

	
	private void updatePos(boolean contain){
		if(accum + SPEED < MAXOFFSET && contain){
			rec.x += SPEED;
			m_x += SPEED;
			textPosX += SPEED;
			accum += SPEED;
		}else if(accum - SPEED > 0 && !contain){
			rec.x -= SPEED;
			m_x -= SPEED;
			textPosX -= SPEED;
			accum -= SPEED;
		}
	}
	
	
	public void render(SpriteBatch spriteBatch){

		spriteBatch.draw(box, m_x, m_y);
		font.draw(spriteBatch, m_text, textPosX	, textPosY);
//		font.draw(spriteBatch, String.valueOf(isClicked), 0, font.getCapHeight());

	}
	
	public void dispose(){
		font.dispose();
		box.dispose();
	}




}

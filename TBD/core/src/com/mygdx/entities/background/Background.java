package com.mygdx.entities.background;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {
	private int m_width, m_height;
	private Texture background;
	private List<Star> starList;
	private Random randx, randy, randi;
	private int starNum;
	
	public Background(int width, int height, int starNum){
		m_width = width;
		m_height = height;
		this.starNum = starNum;
		
		init();
	}
	
	public void init(){
		background = new Texture("Image/Menu/background1.png");
		starList = new ArrayList<Star>();
		randx = new Random();
		randy = new Random();
		randi = new Random();
		starList.add(new Star(randx.nextInt(m_width), randy.nextInt(m_height), randi.nextInt(5)));
		starList.add(new Star(randx.nextInt(m_width), randy.nextInt(m_height), randi.nextInt(5)));
		starList.add(new Star(randx.nextInt(m_width), randy.nextInt(m_height), randi.nextInt(5)));
		starList.add(new Star(randx.nextInt(m_width), randy.nextInt(m_height), randi.nextInt(5)));
	}
	
	public void update(float dt){
		for(int i = 0; i < starList.size(); i++){
			Star s = starList.get(i);
			if(s.getElapsedTime() < 3f){
				s.update(dt, true);
			}else if(s.getElapsedTime() > 3f && s.getElapsedTime() < 5f){
				s.update(dt, false);
			}else {
				starList.remove(s);
			}
			
		}
		
		if(starList.size() < starNum){
			starList.add(new Star(randx.nextInt(m_width), randy.nextInt(m_height), randi.nextInt(5)));
		}
	}
	
	public void render(SpriteBatch spriteBatch){
		spriteBatch.draw(background, 0 , 0, m_width, m_height);
		for(Star s : starList){
			s.render(spriteBatch);
		}
	}
	
	public void dispose(){
		for(Star s : starList){
			s.dispose();
		}
		background.dispose();

	}
}

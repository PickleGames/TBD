package com.mygdx.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;


public class Boundary {
	public boolean UP, DOWN, LEFT, RIGHT;
	public int m_width, m_height;

	public Boundary(int width, int height) {
		m_width = width;
		m_height = height;
	}

	public void update(Texture img, Vector2 position) {
		if (position.x < 0) { //LEFT
			LEFT = true;
			position.x=0;
		} else
			LEFT = false;
		if (position.y < 0) { //DOWN
			DOWN = true;
			position.y = 0;
		} else
			DOWN = false;
		if (position.x + img.getWidth() > m_width) { //RIGHT
			RIGHT = true;
			position.x = (m_width - img.getWidth());
		} else
			RIGHT = false;
		if (position.y + img.getHeight() > m_height) { //TOP
			UP = true;
			position.y = (m_height - img.getHeight());
		} else{
			UP = false;
		}
	}

}

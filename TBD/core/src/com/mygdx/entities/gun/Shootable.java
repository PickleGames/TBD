package com.mygdx.entities.gun;

import com.badlogic.gdx.math.Vector2;


public interface Shootable {

	abstract void shoot(Vector2 target);
	abstract void reload(float dt);
}

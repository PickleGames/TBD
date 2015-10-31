package com.mygdx.entities.enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entities.Entity;
import com.mygdx.entities.MovableEntity;
import com.mygdx.entities.gun.Gun;
import com.mygdx.entities.gun.Shootable;
import com.mygdx.game.TBDGame;

public abstract class Enemy extends MovableEntity implements Shootable {

	private float shootRadius = 5;
	private float followRadius = 10;
	protected boolean isAlerted = false;
	private Random rand = new Random();
	private int x1 = rand.nextInt(TBDGame.WIDTH), y1 = rand
			.nextInt(TBDGame.HEIGHT);

	private static List<Entity> targetList = new ArrayList<Entity>();
	protected List<Gun> gun = new ArrayList<Gun>();

	// list { null, bob }
	public static void setTarget(Entity bob) {
		Enemy.targetList.add(bob);
	}

	public float getShootRadius() {
		return shootRadius;
	}

	public void setShootRadius(float shootRadius) {
		this.shootRadius = shootRadius;
	}

	public float getFollowRadius() {
		return followRadius;
	}

	public void setFollowRadius(float followRadius) {
		this.followRadius = followRadius;
	}

	public static void removeTarget() {
		Enemy.targetList.remove(0);
	}

	public List<Entity> getEnemy() {
		return Enemy.targetList;
	}

	public Enemy(Vector2 position) {
		super(position);

	}

	@Override
	public abstract void init();

	@Override
	public abstract void update(float dt);

	// abstract void update2(float dt){
	//
	// }

	@Override
	public abstract void render(SpriteBatch spriteBatch);

	@Override
	public void dispose() {
	}

	public void shoot(Vector2 position, float dt) {

	}

	public boolean isInRadius(Vector2 position) {
		boolean isInRadius = Math.sqrt(Math.pow(originPosition.x - position.x,
				2) + Math.pow(originPosition.y - position.y, 2)) <= shootRadius * 32;

		return isInRadius;
	}

	public boolean isInFollowRadius(Vector2 position) {
		boolean isInRadius = Math.sqrt(Math.pow(originPosition.x - position.x,
				2) + Math.pow(originPosition.y - position.y, 2)) <= followRadius * 32;

		return isInRadius;
	}

	public void patrol(float dt) {

		if (isCollidedMap()) {
			randomizePosition(rand);
		}

		velocity.x = baseSpeed;
		velocity.y = baseSpeed;

		float rotation = (float) Math.atan2(y1 - positionScaled.y, 
											x1- positionScaled.x);
		
		this.rotation = (float) Math.toDegrees(rotation) + 90f;
		float velocityX = (float) Math.cos(rotation);
		float velocityY = (float) Math.sin(rotation);

		positionScaled.add(velocityX, velocityY);

	}

	public void randomizePosition(Random rand) {
		x1 = rand.nextInt(TBDGame.WIDTH);
		y1 = rand.nextInt(TBDGame.HEIGHT);
		//System.out.println("X: " + x1 + "Y: " + y1);
	}

	public void moveUp() {
		positionScaled.add(0, baseSpeed);
	}

	public void moveDown() {
		positionScaled.add(0, -baseSpeed);
	}

	public void follow2(Vector2 position) {
		isAlerted = true;
		velocity.x = baseSpeed;
		velocity.y = baseSpeed;
		float rotation = (float) Math.atan2(position.y - originPosition.y,
											position.x - originPosition.x);
		this.rotation = (float) Math.toDegrees(rotation) + 90f;
		
		float velocityX = (float) Math.cos(rotation);
		float velocityY = (float) Math.sin(rotation);

		positionScaled.add(velocityX, velocityY);
	}

}

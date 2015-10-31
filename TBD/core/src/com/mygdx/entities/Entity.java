package com.mygdx.entities;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.gameState.levelState.LevelState;
import com.mygdx.gameState.levelState.LevelStateManager;

public abstract class Entity {
	protected Vector2 positionScaled;
	protected Vector2 originPosition;
	protected Vector2 scale;
	protected Vector2 dimension; 
	protected Polygon polygon;

	
	protected boolean isDestructable;
	protected boolean isDead;
	protected float health;
	protected float rotation;
	protected float maxHealth;
	protected boolean isHit;
	
	
	public static LevelState currentLevel;
	private static LevelStateManager lsm;
	
	public static LevelStateManager getLsm() {
		return lsm;
	}

	public static void setLsm(LevelStateManager lsm) {
		Entity.lsm = lsm;
	}

	public static LevelState getCurrentLevel() {
		return currentLevel;
	}

	public static void setCurrentLevel(LevelState currentLevel) {
		Entity.currentLevel = currentLevel;
	}

	public Polygon getPolygon() {
		return polygon;
	}

	public void setPolygon(Polygon polygon) {
		this.polygon = polygon;
	}
	
	public Vector2 getDimension() {
		return dimension;
	}

	public void setDimension(Vector2 dimension) {
		this.dimension = dimension;
	}

	public Vector2 getPositionScaled() {
		return positionScaled;
	}

	public void setPositionScaled(Vector2 positionScaled) {
		this.positionScaled = positionScaled;
	}
	
	public Vector2 getOriginPosition() {
		return originPosition;
	}

	public void setOriginPosition(Vector2 originPosition) {
		this.originPosition = originPosition;
	}
	
	public boolean isDestructable() {
		return isDestructable;
	}

	public void setDestructable(boolean isDestructable) {
		this.isDestructable = isDestructable;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}


	public float getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(float maxHealth) {
		this.maxHealth = maxHealth;
	}

	public Vector2 getScale() {
		return scale;
	}

	public void setScale(Vector2 scale) {
		this.scale = scale;
	}

	
//	public Rectangle getRectangle() {
//		return rectangle;
//	}
//		
//	public void setRectangle(Rectangle rectangle) {
//		this.rectangle = rectangle;
//	}

	
	public float getRotation() {
		return rotation;
	}

	public boolean isHit() {
		return isHit;
	}

	public void setHit(boolean isHit) {
		this.isHit = isHit;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public Entity(Vector2 position){
		this.positionScaled = position;
		isDead = false;
	}
	
	public Entity(Vector2 position, Vector2 scale){
		this.positionScaled = position;
		this.scale = scale;

	}
	
	public abstract void init();
	public abstract void update(float dt);
	public abstract void render(SpriteBatch spriteBatch);
	public abstract void dispose();
	
	
	
}

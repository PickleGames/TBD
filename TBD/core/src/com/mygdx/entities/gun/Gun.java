package com.mygdx.entities.gun;

import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entities.Entity;
import com.mygdx.entities.MovableEntity;
import com.mygdx.entities.enemy.Enemy;
import com.mygdx.entities.gun.bullet.Bullet;

public abstract class Gun extends Entity implements Shootable{

	protected List<Bullet> bullet;
	protected int currentClip;
	protected boolean isShootable;
	protected boolean isReloadPressed;
	protected boolean isShootPressed;
	protected float timeElapsed_Shoot;
	protected float timeElapsed_Reload;
	protected float RELOAD_TIME;
	protected float SHOOT_TIME;
	protected int BULLET_CAP;
	protected MovableEntity player;
	
	protected Iterator<Bullet> bulIterator;

	public Gun(Vector2 position) {
		super(position);

	}
	public void updateGunPosition(){
	
	}
	
	public void update(float dt) {
		originPosition = new Vector2(positionScaled.x + dimension.x / 2 , 
				 					positionScaled.y + dimension.y / 2);
		timeElapsed_Shoot += dt;
		
		if(timeElapsed_Shoot >= 3f){
			 isShootPressed = false;
		}
		
		if(isReloadPressed){
			timeElapsed_Reload+=dt;
		}
		
		if(timeElapsed_Reload >= RELOAD_TIME){
			currentClip = BULLET_CAP;
			timeElapsed_Reload = 0;
			isReloadPressed = false;
		}
		
		if(!player.isShooting())
			rotation = player.getRotation();	
	}
	
	public void updateBulletPlayer(float dt) {
		bulIterator = bullet.iterator();		
		while(bulIterator.hasNext()){
			Bullet bul = bulIterator.next();
			bul.update(dt);
			
			boolean isHit = false;
			for(Enemy e : getCurrentLevel().getEnemyList()){
				if(Intersector.overlapConvexPolygons(bul.getPolygon(), e.getPolygon())){
					isHit = true;
					e.setHealth(e.getHealth() - Bullet.damage);
					
					bulIterator.remove();
					Bullet.bulletCount--;
					break;
				}
			}
			
			if((bul.isCollidedMap() || bul.getTimeElapsed() > 2) && !isHit){
				bulIterator.remove();
				Bullet.bulletCount--;
			}
			
		}
	}


	public void updateBulletEnemy(float dt) {
		bulIterator = bullet.iterator();		
		while(bulIterator.hasNext()){
			Bullet bul = bulIterator.next();
			bul.update(dt);
			

			if(Intersector.overlapConvexPolygons(bul.getPolygon(), getCurrentLevel().getBob().getPolygon())){		
				getCurrentLevel().getBob().setHealth(getCurrentLevel().getBob().getHealth() - Bullet.damage);
				bulIterator.remove();
				Bullet.bulletCount--;
				getCurrentLevel().getBob().setHit(true);
				continue;
			}else if(bul.isCollidedMap() || bul.getTimeElapsed() > 2){
				bulIterator.remove();
				Bullet.bulletCount--;
				continue;
			}else{
				
			}
		}
	}	
	

	public void reload(float dt) {
		isReloadPressed = true;
	}

	 
	
	public List<Bullet> getBullet() {
		return bullet;
	}

	public void setBullet(List<Bullet> bullet) {
		this.bullet = bullet;
	}

	public int getCurrentClip() {
		return currentClip;
	}

	public void setCurrentClip(int currentClip) {
		this.currentClip = currentClip;
	}
	
	public boolean isShootable() {
		return isShootable;
	}

	public void setShootable(boolean isShootable) {
		this.isShootable = isShootable;
	}


	public boolean isReloadPressed() {
		return isReloadPressed;
	}

	public void setReloadPressed(boolean isReloadPressed) {
		this.isReloadPressed = isReloadPressed;
	}

	public boolean isShootPressed() {
		return isShootPressed;
	}

	public void setShootPressed(boolean isShootPressed) {
		this.isShootPressed = isShootPressed;
	}

	public float getTimeElapsed_Shoot() {
		return timeElapsed_Shoot;
	}

	public void setTimeElapsed_Shoot(float timeElapsed_Shoot) {
		this.timeElapsed_Shoot = timeElapsed_Shoot;
	}

	public float getTimeElapsed_Reload() {
		return timeElapsed_Reload;
	}

	public void setTimeElapsed_Reload(float timeElapsed_Reload) {
		this.timeElapsed_Reload = timeElapsed_Reload;
	}
	
	
	
	
}

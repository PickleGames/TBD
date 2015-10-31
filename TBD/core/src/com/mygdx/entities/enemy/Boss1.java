package com.mygdx.entities.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entities.gun.Gun;
import com.mygdx.entities.gun.Pistol;

public class Boss1 extends Enemy{
	public static Texture texture = new Texture("Image/Character/Enemy/Boss 1.png");
	public static TextureRegion textureRegion = new TextureRegion(texture);
	
	public Boss1(Vector2 position) {
		super(position);
		init();
		// TODO Auto-generated constructor stub
	}

	public void init(){
		scale = new Vector2(2f, 2f);	
		dimension = new Vector2(textureRegion.getRegionWidth() * scale.x, textureRegion.getRegionHeight() * scale.y);
		originPosition = new Vector2(positionScaled.x + dimension.x / 2 , 
				 					 positionScaled.y + dimension.y / 2);

		positionScaled = new Vector2(originPosition.x - dimension.x / 2, 
							 		 originPosition.y - dimension.y / 2);

		
		velocity = new Vector2(baseSpeed *.75f, baseSpeed* .75f);
		polygon = new Polygon(new float[]{5,10,
				 12,10,
				 12,30,
				 dimension.x-15, 30,
				 dimension.x-15, 10,
				 dimension.x-8,10,
				 dimension.x-8,dimension.y-10,
				 5,dimension.y-10});
		weaponPos = new Vector2[2];
		
		weaponPos[0] = new Vector2( originPosition.x - (float)(dimension.x / 2 * Math.cos(Math.toRadians(rotation))), 
							  		originPosition.y - (float)(dimension.x / 2 * Math.sin(Math.toRadians(rotation))) );
		weaponPos[1] = new Vector2( originPosition.x  + (float)(dimension.x / 2 * Math.cos(Math.toRadians(rotation))), 
				  					originPosition.y  + (float)(dimension.x / 2 * Math.sin(Math.toRadians(rotation))) );
		
		gun.add(new Pistol(weaponPos[0], this));
		gun.add(new Pistol(weaponPos[1], this));
		setHealth(100);
	}
	
	public void update(float dt){
		if(!isDead){
			
			polygon.setOrigin(dimension.x/2, dimension.y /2);
			polygon.setPosition(positionScaled.x, positionScaled.y);
			polygon.setRotation(rotation);
			weaponPos[0] = new Vector2( originPosition.x - (float)(dimension.x / 2 * Math.cos(Math.toRadians(rotation))), 
									 originPosition.y - (float)(dimension.x / 2 * Math.sin(Math.toRadians(rotation))) );
			weaponPos[1] = new Vector2( originPosition.x + (float)(dimension.x / 2 * Math.cos(Math.toRadians(rotation))), 
					 originPosition.y + (float)(dimension.x / 2 * Math.sin(Math.toRadians(rotation))) );
			originPosition = new Vector2(positionScaled.x + dimension.x / 2 , 
										 positionScaled.y + dimension.y / 2);
			gun.get(0).setPositionScaled(weaponPos[0]);
			gun.get(1).setPositionScaled(weaponPos[1]);
			
			setOldPosition(new Vector2(positionScaled.x,positionScaled.y));
			if(!isInRadius(getEnemy().get(0).getPositionScaled()) && isInFollowRadius(getEnemy().get(0).getPositionScaled())){				
				follow2(getEnemy().get(0).getPositionScaled());
			}
			if(!isInRadius(getEnemy().get(0).getPositionScaled()) && !isInFollowRadius(getEnemy().get(0).getPositionScaled())&& !isAlerted){
				//patrol(dt);
			}
			checkMapCollision(dt, polygon);
			
			if(isInRadius(getEnemy().get(0).getPositionScaled())){
				shoot(getEnemy().get(0).getPositionScaled());
			}else isShooting = false;
			
			for(Gun g: gun){
				g.update(dt);
				g.updateBulletEnemy(dt);
				if(g.getCurrentClip() <= 0) reload(dt); 
			}
			
			
		}
		
		if(health <= 0) isDead = true;
	}

	@Override
	public void shoot(Vector2 target) {
		isShooting = true;
		for(Gun g: gun){
			rotation = (float) Math.toDegrees((Math.atan2(target.y - originPosition.y, 
											   target.x - originPosition.x ))) + 90f;
			g.setRotation(rotation);
			g.shoot(target);
		}		
	}

	@Override
	public void reload(float dt) {
		for(Gun g: gun){
			g.reload(dt);
		}
	}
	
	public void render(SpriteBatch spriteBatch){
		if(!isDead){
			spriteBatch.draw(textureRegion, positionScaled.x, positionScaled.y,
							 dimension.x / 2, dimension.y / 2, 
							 dimension.x, dimension.y, 1, 1, rotation);
			
			for(Gun g : gun){
				g.render(spriteBatch);
			}
		}
	}

	public void dispose(){
		texture.dispose();
	}
	

}

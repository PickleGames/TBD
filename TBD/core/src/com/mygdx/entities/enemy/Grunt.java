package com.mygdx.entities.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entities.gun.Gun;
import com.mygdx.entities.gun.Pistol;



public class Grunt extends Enemy{
	private static Texture texture = new Texture("Image/Character/Enemy/grunt1.png");
	private static TextureRegion textureRegion = new TextureRegion(texture);
	
//	private float timeElapsed;
//	private Random rand;
	
	public Grunt(Vector2 position) {
		super(position);
		init();
	}
	
	public void init(){
		scale = new Vector2(2f, 2f);	
		dimension = new Vector2(textureRegion.getRegionWidth() * scale.x, textureRegion.getRegionHeight() * scale.y);
		originPosition = new Vector2(positionScaled.x + dimension.x / 2 , 
				 					 positionScaled.y + dimension.y / 2);

		positionScaled = new Vector2(originPosition.x - dimension.x / 2, 
							 		 originPosition.y - dimension.y / 2);

		
		velocity = new Vector2(baseSpeed , baseSpeed);
		polygon = new Polygon(new float[]{0,0,
				 dimension.x,0,
				 dimension.x,dimension.y,
				 0,dimension.y});
		weaponPos = new Vector2[1];
		weaponPos[0] = new Vector2(originPosition.x - (float)(5 * scale.x * Math.cos(Math.toRadians(rotation))), 
							  	   originPosition.y - (float)(5 * scale.y * Math.sin(Math.toRadians(rotation)))  );
		gun.add(new Pistol(weaponPos[0], this));
		setHealth(100);
	}
	
	public void update(float dt){
//		timeElapsed+=dt;
//		rand = new Random();
		
		polygon.setRotation(rotation);
		if(!isDead){
			polygon.setOrigin(dimension.x/2, dimension.y /2);
			polygon.setPosition(positionScaled.x, positionScaled.y);
			weaponPos[0] = new Vector2( originPosition.x - (float)(5 * scale.x * Math.cos(Math.toRadians(rotation))), 
									 	originPosition.y - (float)(5 * scale.y  * Math.sin(Math.toRadians(rotation))) );
			gun.get(0).setPositionScaled(weaponPos[0]);
			originPosition = new Vector2(positionScaled.x + dimension.x / 2 , 
										 positionScaled.y + dimension.y / 2);
			
			setOldPosition(new Vector2(positionScaled.x,positionScaled.y));
			if(!isInRadius(getEnemy().get(0).getPositionScaled()) && isInFollowRadius(getEnemy().get(0).getPositionScaled())){				
				follow2(getEnemy().get(0).getPositionScaled());
			}
			if(!isInRadius(getEnemy().get(0).getPositionScaled()) && !isInFollowRadius(getEnemy().get(0).getPositionScaled())&& !isAlerted){
				//patrol(dt);
			}
			checkMapCollision(dt, polygon);
		
			//System.out.println(rotation);
			
			if(isInRadius(getEnemy().get(0).getPositionScaled())){
				shoot(getEnemy().get(0).getPositionScaled());
			}
			
			for(Gun g: gun){
				g.update(dt);
				g.updateBulletEnemy(dt);
				if(g.getCurrentClip() <= 0) reload(dt); 
			}
			
			
		}
		
		if(health <= 0) isDead = true;
	}
	
	public void update2(float dt){
		
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

	@Override
	public void shoot(Vector2 target) {
		rotation = (float) Math.toDegrees((Math.atan2(target.y - originPosition.y, 
													  target.x - originPosition.x ))) + 90f;

		for(Gun g: gun){
			g.shoot(target);
		}		
	}

	@Override
	public void reload(float dt) {
		for(Gun g: gun){
			g.reload(dt);
		}
	}

}

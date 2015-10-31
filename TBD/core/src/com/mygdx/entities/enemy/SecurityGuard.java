package com.mygdx.entities.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entities.gun.Gun;
import com.mygdx.entities.gun.Pistol;

public class SecurityGuard extends Enemy{
	private static Texture texture;
	private static TextureRegion textureRegion;
	private static TextureRegion[] SGRegion;
	private static TextureRegion currentSG;
	Animation SGAnimate;
	
	float time = 0f;
	private boolean isMove = false;
	
	public SecurityGuard(Vector2 position) {
		super(position);
		init();
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

	@Override
	public void init() {
		setHealth(250);
		setMaxHealth(250);
		
		texture = new Texture("Image/Character/Enemy/Security_Guard_Walk_Anime.png");
		textureRegion = new TextureRegion(new Texture("Image/Character/Enemy/Security_Guard_normal.png"));

		TextureRegion[][] tmp = TextureRegion.split(texture,
				texture.getWidth() / 2, texture.getHeight());
		SGRegion = new TextureRegion[2];
		int index = 0;

		
		for (int i = 0; i < tmp.length; i++) {
			for (int j = 0; j < tmp[i].length; j++) {
				SGRegion[index++] = tmp[i][j];
			}
		}

		scale = new Vector2(.75f, .75f);	
		dimension = new Vector2(textureRegion.getRegionWidth() * scale.x, textureRegion.getRegionHeight() * scale.y);

		originPosition = new Vector2(positionScaled.x + dimension.x / 2 , 
				 					 positionScaled.y + dimension.y / 2);
		positionScaled = new Vector2(originPosition.x - dimension.x / 2, 
									 originPosition.y - dimension.y / 2);
		weaponPos = new Vector2[1];
		weaponPos[0] = new Vector2( originPosition.x - (float)(8 * Math.cos(rotation)), 
				  			  		originPosition.y - (float)(8 * Math.sin(rotation)) );
		polygon = new Polygon(new float[]{0,0,
										 dimension.x,0,
										 dimension.x,dimension.y,
										 0,dimension.y});
		
		SGAnimate = new Animation(1 / 5f, SGRegion);
		SGAnimate.setPlayMode(Animation.PlayMode.LOOP);
		
		velocity = new Vector2(baseSpeed * 1.5f, baseSpeed* 1.5f);
		
		gun.add(new Pistol(weaponPos[0], this));
	}

	@Override
	public void update(float dt) {
		
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
				isMove = true;
			}
			if(!isInRadius(getEnemy().get(0).getPositionScaled()) && !isInFollowRadius(getEnemy().get(0).getPositionScaled())&& !isAlerted){
				//patrol(dt);
				isMove = true;
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

	@Override
	public void render(SpriteBatch spriteBatch) {
		if(!isDead){
			if (isMove) {
				time += Gdx.graphics.getDeltaTime();
				currentSG = SGAnimate.getKeyFrame(time);
				spriteBatch.draw(currentSG, positionScaled.x, positionScaled.y,
								 dimension.x / 2 , dimension.y / 2,
								 dimension.x ,dimension.y,
								 1, 1, rotation);
							
			} else {
				spriteBatch.draw(textureRegion, positionScaled.x, positionScaled.y,
								 dimension.x / 2 , dimension.y / 2,
								 dimension.x ,dimension.y,
								 1, 1, rotation);
				
			}
			
			for(Gun g: gun){
				g.render(spriteBatch);
			}
		}
	}
	
	public void dispose(){
		texture.dispose();
		textureRegion.getTexture().dispose();
	}
}

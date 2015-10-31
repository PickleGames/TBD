package com.mygdx.entities.gun.bullet;

import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entities.MovableEntity;


public abstract class Bullet extends MovableEntity{
//	private BitmapFont font = new BitmapFont();
	public static int bulletCount = 0;
	public static final int BULLET_ONSCREEN_CAP = 100;
	
	TextureRegion region;
	protected Texture texture;
	protected TextureRegion textureRegion;

	private float elapsed;
	public static float damage = 10;
	
	public float getTimeElapsed(){ return elapsed ;}
	
	public Bullet(Vector2 position, float degree){
		super(new Vector2(position));
		bulletCount++;
		rotation = degree;
		init();
	}
	
	public  void init(){
		scale = new Vector2(1f, 1f);	
		dimension = new Vector2(textureRegion.getRegionWidth() * scale.x, textureRegion.getRegionHeight() * scale.y);
		originPosition = new Vector2(positionScaled.x + dimension.x / 2 , 
									 positionScaled.y + dimension.y / 2);
		positionScaled = new Vector2(originPosition.x - dimension.x / 2, 
				 					 originPosition.y - dimension.y / 2);

		dimension = new Vector2(textureRegion.getRegionWidth() * scale.x, textureRegion.getRegionHeight() * scale.y);
		region = new TextureRegion(texture);
		
		velocity = new Vector2(baseSpeed * 2f, baseSpeed * 2f);

		polygon = new Polygon(new float[]{0,0,
				 dimension.x,0,
				 dimension.x,dimension.y,
				 0,dimension.y});

	}
	
	public  void update(float dt){
		elapsed += dt;
		
		setOldPosition(positionScaled);
		update_bullet(dt);
		checkMapCollision(dt, polygon);
		
//		setRectangle(new Rectangle(positionScaled.x, positionScaled.y, texture.getWidth() * scale.x,texture.getHeight()* scale.y));
		
		polygon.setOrigin(dimension.x / 2, dimension.y / 2);
		polygon.setPosition(positionScaled.x, positionScaled.y);
		polygon.setRotation(rotation);
		
	}
	
	public void update_bullet_lol(){
		positionScaled.x += Math.cos((positionScaled.x + velocity.x)* rotation);
		positionScaled.y += Math.sin((positionScaled.y + velocity.y)* rotation);
		
	}
	
	
	public void update_bullet(float dt){
		Vector2 bulletVelocity = new Vector2();
		
		bulletVelocity.x = (float) (Math.cos((Math.toRadians(rotation))) );
		bulletVelocity.y = (float) (Math.sin((Math.toRadians(rotation))) );
		
//		System.out.println("Bullet Vel: "+ bulletVelocity);
		
		positionScaled.x += bulletVelocity.x * velocity.x * 1;
		positionScaled.y += bulletVelocity.y * velocity.y * 1;
		
		originPosition.add(bulletVelocity.x * velocity.x, 
						   bulletVelocity.y * velocity.y);
		positionScaled.add(bulletVelocity.x * velocity.x, bulletVelocity.y * velocity.y);
		
	}
	
	public void render(SpriteBatch spriteBatch){
		spriteBatch.draw(region,positionScaled.x,positionScaled.y,
						 region.getRegionWidth()/2,region.getRegionHeight()/2,
						 texture.getWidth(),texture.getHeight(),
						 scale.x,scale.y, rotation + 90f);
		//font.draw(spriteBatch, "("+ originpositionScaled.x+", "+originpositionScaled.y+")", originpositionScaled.x, originpositionScaled.y);
		
	}
	
	public  void dispose(){
		//texture.dispose();
	}
	
}

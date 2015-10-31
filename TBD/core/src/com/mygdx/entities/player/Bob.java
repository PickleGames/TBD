package com.mygdx.entities.player;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.entities.MovableEntity;
import com.mygdx.entities.gun.Gun;
import com.mygdx.entities.gun.Pistol;
import com.mygdx.gameState.levelState.LevelState;


public class Bob extends MovableEntity{

	private BitmapFont font = new BitmapFont();

	
	private Texture texture;
	private TextureRegion textureRegion;
	private TextureRegion[] bobRegion;
	private TextureRegion currentBob;
	Animation bobAnimate;

	float time = 0f;
	private boolean isMove = false;
	private boolean isHitLast = false;

//	private List<Bullet> bulletList = new ArrayList<Bullet>();
	private List<Gun> gun = new ArrayList<Gun>();
	private Gun currentGun;
	

	public Gun getCurrentGun(){
		return currentGun;
	}
	
	public Bob(Vector2 position) {
		super(position);
		init();
	}
	
	public void init() {
		this.setTileMapCollisionLayer(LevelState.getMap());
		font.setColor(Color.BLUE);
		
		setHealth(1000);
		setMaxHealth(1000);
		
		texture = new Texture("Image/Character/main/mainChar_nogun_spriteSheet.png");
		textureRegion = new TextureRegion(new Texture("Image/Character/main/mainChar-nogun.png"));

		TextureRegion[][] tmp = TextureRegion.split(texture,
				texture.getWidth() / 2, 15);
		bobRegion = new TextureRegion[2];
		int index = 0;

		
		for (int i = 0; i < tmp.length; i++) {
			for (int j = 0; j < tmp[i].length; j++) {
				bobRegion[index++] = tmp[i][j];
			}
		}

		scale = new Vector2(1f, 1f);	
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
		
		bobAnimate = new Animation(1 / 5f, bobRegion);
		bobAnimate.setPlayMode(Animation.PlayMode.LOOP);
		
		velocity = new Vector2(0,0);
		
		gun.add(new Pistol(weaponPos[0], this));
		currentGun = gun.get(0);
//		OH YEAH 360 BABY!!!
//		for(int i=0;i<= 360 ; i++){
//			bulletList.add(new Bullet(new Vector2(0,0), i));
//		}
		
	}

	Vector3 mousePos3 = new Vector3(0,0,0);
	
	public void update(float dt) {

		weaponPos[0] = new Vector2( originPosition.x - (float)(8 * Math.cos(Math.toRadians(rotation))), 
							  originPosition.y - (float)(8 * Math.sin(Math.toRadians(rotation))) );
		gun.get(0).setPositionScaled(weaponPos[0]);
		polygon.setOrigin(dimension.x/2, dimension.y/2);
		polygon.setPosition(positionScaled.x, positionScaled.y);
		polygon.setVertices(new float[]{0,0,
										 dimension.x,0,
										 dimension.x,dimension.y,
										 0,dimension.y});
		polygon.setRotation(rotation);
		originPosition = new Vector2(positionScaled.x + dimension.x / 2 , 
									 positionScaled.y + dimension.y / 2);
		originPosition.add(velocity.x, velocity.y);
//		positionScaled.add(velocity.x, velocity.y);
		
//		System.out.println("Fix position: "+ fixPosition);
		mousePos3.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		LevelState.getCam().unproject(mousePos3);
		rotation = (float) Math.toDegrees((Math.atan2(mousePos3.y - (originPosition.y) 
													, mousePos3.x - (originPosition.x) ))) + 90f;
		
		
		
		
		
		//Movement
		
//		if(!isCollisionY_T() &&  Gdx.input.isKeyPressed(Keys.W)){
//			velocity.y = baseSpeed;
//		}else if(!isCollisionY_D() && Gdx.input.isKeyPressed(Keys.S)){
//			velocity.y = -baseSpeed;
//		}else velocity.y = 0;
//		
//		if(!isCollisionX_R() && Gdx.input.isKeyPressed(Keys.D)){
//			velocity.x = baseSpeed;
//		}else if(!isCollisionX_L() && Gdx.input.isKeyPressed(Keys.A)){
//			velocity.x = -baseSpeed;
//		}else velocity.x = 0;


		if(!isDead){
	
			shoot(new Vector2(mousePos3.x, mousePos3.y));
			reload(dt);
			move1();

			currentGun.update(dt);
			currentGun.updateBulletPlayer(dt);

			
			
			//Gdx.input.setInputProcessor(this);
	//////////////////////////////////////////////////////////////////////////
			
	////////////////////////////////////////////////////////////////////////
			if(velocity.x == 0 && velocity.y == 0) isMove = false;
			else isMove = true;
			
	
			setOldPosition(new Vector2(positionScaled.x,positionScaled.y));
			
			positionScaled.x += velocity.x;
			positionScaled.y += velocity.y;
			
			checkMapCollision(dt, polygon);
			
	///////////////////////////////////////////////////////////////
			

		}

		
		
		if(isHit && !isHitLast){
			Timer.schedule(new Task() {
				@Override
				public void run() {
					isHit = false;
				}
			}, 3);
		}
		
		if(!currentGun.isShootPressed() && !isHit && !isHitLast) regen();
		
		
		if(health <= 0)isDead = true;
		
		isHitLast = isHit;
	}
	
	public void regen(){
		if(health < maxHealth) health += 1;
		
	}
	public void addGun(Gun gun){
		this.gun.add(gun);
	}
	
	public void move1(){
		if(Gdx.input.isKeyPressed(Keys.W)){
			velocity.y = baseSpeed;
		}else if( Gdx.input.isKeyPressed(Keys.S)){
			velocity.y = -baseSpeed;
		}else velocity.y = 0;
		
		if(Gdx.input.isKeyPressed(Keys.D)){
			velocity.x = baseSpeed;
		}else if( Gdx.input.isKeyPressed(Keys.A)){
			velocity.x = -baseSpeed;
		}else velocity.x = 0;
	}
	
	public void shoot(Vector2 target){	
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			currentGun.shoot(target);
			isShooting = true;
		}else if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
			
		}else isShooting = false;
		

	}
	
	public void reload(float dt){
		if(Gdx.input.isKeyPressed(Keys.R)){
			for(Gun g: gun){
				g.reload(dt);
			}
		}
	}
	
	public void render(SpriteBatch spriteBatch) {
		if(!isDead){
			if (isMove) {
				time += Gdx.graphics.getDeltaTime();
				currentBob = bobAnimate.getKeyFrame(time);
				spriteBatch.draw(currentBob, positionScaled.x, positionScaled.y,
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
//			for(Bullet bul : bulletList){
//				bul.render(spriteBatch);
//			}
		}


//		font.draw(spriteBatch, "Texture: "+ texture.getWidth()+", "+texture.getHeight(), position.x, position.y);
//		font.draw(spriteBatch, "Origin("+ originPosition.x+", "+originPosition.y+")", originPosition.x, originPosition.y);
//		font.draw(spriteBatch, "Scaled("+ positionScaled.x+", "+positionScaled.y+")", positionScaled.x, positionScaled.y);
//		font.draw(spriteBatch, "MousePos("+ mousePos3.x+", "+ mousePos3.y+")", 
//											mousePos3.x,mousePos3.y);

	}
	
	public void reset(){
		isDead = false;
		setHealth(200);
	}
	
	public void dispose() {
		texture.dispose();
	}

	
}
package com.mygdx.entities.gun;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entities.MovableEntity;
import com.mygdx.entities.gun.bullet.Bullet;
import com.mygdx.entities.gun.bullet.HeroBullet;
import com.mygdx.jukeBox.SoundBox;

public class Pistol extends Gun{
	private static Texture texture = new Texture("Image/Gun/Pistol.png");
	private static TextureRegion textureRegion = new TextureRegion(texture);

	public Pistol(Vector2 position, MovableEntity player) {
		super(position);
		this.player = player;

	}

	{
		isShootable = true;
		bullet = new ArrayList<Bullet>();
		bulIterator = bullet.iterator();
		BULLET_CAP = 15;
		RELOAD_TIME = 1;
		SHOOT_TIME = .2f;
		currentClip = BULLET_CAP;
		init();
	}
	
	@Override
	public void init() {
		dimension = new Vector2(texture.getWidth(), texture.getHeight());
		originPosition = new Vector2(positionScaled.x + dimension.x / 2, positionScaled.y + dimension.y / 2);
		

	}
	
	public void shoot(Vector2 target) {
		if(!isReloadPressed && currentClip > 0 && timeElapsed_Shoot >= SHOOT_TIME){
			rotation = (float) Math.toDegrees((Math.atan2(target.y - originPosition.y, 
					   						   			  target.x - originPosition.x ))) + 90f;

			bullet.add(new HeroBullet(originPosition, rotation - 90f));
//			SoundBox.play("chew");
			SoundBox.setVolume("chew", .1f);
			currentClip--;	
			timeElapsed_Shoot = 0;
			isShootPressed = true;
		}

	}
	
	public void shootSpecial(){
		if(!isReloadPressed && currentClip > 0 && timeElapsed_Shoot >= SHOOT_TIME){
			for(int i=0;i<= 360 ; i+=36){
				bullet.add(new HeroBullet(originPosition, i));
			}
			
	//		SoundBox.play("chew");
			SoundBox.setVolume("chew", .1f);
			currentClip--;	
			timeElapsed_Shoot = 0;
			isShootPressed = true;
		}
	}
	
	@Override
	public void render(SpriteBatch spriteBatch) {
		spriteBatch.draw(textureRegion, 
				 positionScaled.x - dimension.x / 2, positionScaled.y - dimension.y / 2,
				 dimension.x / 2, dimension.y / 2, 
				 dimension.x, dimension.y, 1, 1, rotation);
		
		for(Bullet bul : bullet){
			bul.render(spriteBatch);
		}
	}

	@Override
	public void dispose() {
	}




}

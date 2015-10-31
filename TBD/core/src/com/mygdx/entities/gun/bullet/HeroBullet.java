package com.mygdx.entities.gun.bullet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class HeroBullet extends Bullet{
	

	
	public HeroBullet(Vector2 position, float degree) {
		super(position, degree);


	}
	
	public  void init(){
		texture =  new Texture("Image/Gun/Bullet/heroBullet.png");
		textureRegion = new TextureRegion(texture);
		
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
	

}

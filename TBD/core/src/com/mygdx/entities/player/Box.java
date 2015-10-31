package com.mygdx.entities.player;

//import java.util.List;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entities.MovableEntity;
import com.mygdx.game.TBDGame;
import com.mygdx.gameState.levelState.LevelState;
import com.mygdx.util.Boundary;


public class Box extends MovableEntity{
	private Texture texture;
	private TextureRegion textureRegion;
	public final int SPEED = 5;
	public Boundary screenBoundary;
	
	public Box(Vector2 position){
		super(position);
		init();
	}
	
	public void init() {
		texture = new Texture("Image/Character/main/player.png");
		textureRegion = new TextureRegion(texture);
		
		scale = new Vector2(.5f, .5f);	
		dimension = new Vector2(textureRegion.getRegionWidth() * scale.x, textureRegion.getRegionHeight() * scale.y);
//		dimension = new Vector2(20 * scale.x, 20 * scale.y);
//		originPosition = new Vector2(positionScaled.x + textureRegion.getRegionWidth() / 2, 
//									 positionScaled.y + textureRegion.getRegionHeight() / 2);
		originPosition = new Vector2(positionScaled.x + dimension.x / 2 , 
									 positionScaled.y + dimension.y / 2);
		positionScaled = new Vector2(originPosition.x - dimension.x / 2, 
									 originPosition.y - dimension.y / 2);

		velocity = new Vector2(SPEED, SPEED);
		screenBoundary = new Boundary(TBDGame.WIDTH, TBDGame.HEIGHT);
		this.setTileMapCollisionLayer(LevelState.getMap());
	}

	@Override
	public void update(float dt) {
		positionScaled.x += velocity.x;
		positionScaled.y += velocity.y;
		
		screenBoundary.update(texture, positionScaled);

		if (screenBoundary.LEFT || screenBoundary.RIGHT) {
			velocity.x = -velocity.x;
		}
		if (screenBoundary.UP || screenBoundary.DOWN) {
			velocity.y = -velocity.y;
		}
//		rectangle.setPosition(new Vector2(positionScaled.x,positionScaled.y));
		
		//checkMapCollision(dt);
		if(isCollisionX()) velocity.x = -velocity.x;
		if(isCollisionY()) velocity.y = -velocity.y;
		//LOGrec();
	}


	@Override
	public void render(SpriteBatch spriteBatch) {
		spriteBatch.draw(textureRegion, positionScaled.x, positionScaled.y);
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}

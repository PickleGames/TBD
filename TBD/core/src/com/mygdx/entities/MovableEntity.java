package com.mygdx.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

//import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class MovableEntity extends Entity {
	protected Vector2 velocity;
	protected static float baseSpeed = 2;
	protected Vector2[] weaponPos;
	static TiledMapTileLayer collisionLayer;

	private Vector2 oldPos;
	protected boolean isShooting;
	
	
	public boolean isShooting() {
		return isShooting;
	}

	public void setShooting(boolean isJustShot) {
		this.isShooting = isJustShot;
	}

	public void setTileMapCollisionLayer(TiledMap tileMap) {
		collisionLayer = (TiledMapTileLayer) tileMap.getLayers().get(5);
		
	}

	public Vector2[] getWeaponPosition() {
		return weaponPos;
	}

	public void setOldPosition(Vector2 oldPos) {
		this.oldPos = oldPos;
	}

	public TiledMapTileLayer getTiledMapCollisionLayer() {
		return collisionLayer;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public MovableEntity(Vector2 position) {
		super(position);
	}

	// collision
	private boolean collidedMap = false;
	private boolean collisionX_L = false, collisionX_R = false,
			collisionY_T = false, collisionY_D = false;
	private boolean collisionX = false, collisionY = false;

	public boolean isCollidedMap() {
		return collidedMap;
	}

	// GOOD ONE
	public void checkMapCollision(float dt, Polygon entityPolygon) {
		// System.out.println("COLLIded call");
		// System.out.println("Velocity: "+ velocity);
		// System.out.println("BOb Position: " + position);

		// System.out.println("Old pos: "+ oldPos);
		// System.out.println();

		// if(!isCollisionX_L() && velocity.x < 0)
		// position.x += velocity.x * 1;
		// if(!isCollisionX_R() && velocity.x > 0)
		// position.x += velocity.x * 1;

		if (velocity.x < 0) {
			collisionX_R = false;
			collisionX_L = isCollidedLeft(entityPolygon);
		} else if (velocity.x > 0) {
			collisionX_L = false;
			collisionX_R = isCollidedRight(entityPolygon);

		}

		// react to x-axis collision
		if (collisionX_R) {
			// System.out.println("COLLIDED X Right");
			positionScaled.x = oldPos.x;
			velocity.x = 0;
			collisionX = true;
		}
		if (collisionX_L) {
			// System.out.println("COLLIDED X Left");
			positionScaled.x = oldPos.x;
			velocity.x = 0;

			collidedMap = true;
			collisionX = true;
		}

		// move on y-axis

		if (velocity.y < 0) {
			collisionY_T = false;
			collisionY_D = isCollidedBottom(entityPolygon);

		} else if (velocity.y > 0) {
			collisionY_D = false;
			collisionY_T = isCollidedTop(entityPolygon);
		}

		// react to y-axis collision
		if (collisionY_T) {
			// System.out.println("COLLIDED Y Top");
			positionScaled.y = oldPos.y;
			velocity.y = 0;
			collisionY = true;
		}
		if (collisionY_D) {
			// System.out.println("COLLIDED Y Down");
			positionScaled.y = oldPos.y;
			velocity.y = 0;
			collisionY = true;
		}

		if (collisionY_D || collisionY_T || collisionX_L || collisionX_R)
			collidedMap = true;
		else
			collidedMap = false;

	}


	public boolean isCellBlocked(float x, float y) {
		Cell cell = collisionLayer.getCell(
				(int) (x / collisionLayer.getTileWidth()),
				(int) (y / collisionLayer.getTileHeight()));
		
		System.out.println(collisionLayer.getProperties().containsKey("Blocked"));
//		return collisionLayer.getProperties().containsKey("Blocked");
		return cell != null
				&& cell.getTile().getProperties().containsKey("Blocked");
	}

	ShapeRenderer shaperenderer = new ShapeRenderer();

	public boolean isCollidedTop(Polygon entityPolygon) {
		for (float step = 0; step <= dimension.x; step += dimension.x / 2) {
			if (isCellBlocked(positionScaled.x + step, positionScaled.y
					+ dimension.y)) {
				float posX = (int) ((positionScaled.x + step) / collisionLayer
						.getTileWidth()) * 32;
				float posY = (int) ((positionScaled.y + dimension.y) / collisionLayer
						.getTileWidth()) * 32;
				Polygon blockedPolygon = new Polygon(new float[] { 0, 0, 32, 0,
						32, 32, 0, 32 });
				blockedPolygon.setOrigin(32 / 2, 32 / 2);
				blockedPolygon.setPosition((int) posX, (int) posY);
				
				if (Intersector.overlapConvexPolygons(blockedPolygon,
						entityPolygon)) {

					return true;
				}
			}
		}
		return false;
	}

	public boolean isCollidedBottom(Polygon entityPolygon) {
		for (float step = 0; step <= dimension.x; step += dimension.x / 2) {
			if (isCellBlocked(positionScaled.x + step, positionScaled.y)) {
				float posX = (int) ((positionScaled.x + step) / collisionLayer
						.getTileWidth()) * 32;
				float posY = (int) ((positionScaled.y) / collisionLayer
						.getTileWidth()) * 32;
				Polygon blockedPolygon = new Polygon(new float[] { 0, 0, 32, 0,
						32, 32, 0, 32 });
				blockedPolygon.setOrigin(32 / 2, 32 / 2);
				blockedPolygon.setPosition((int) posX, (int) posY);
				
				if (Intersector.overlapConvexPolygons(blockedPolygon,
						entityPolygon)) {

					return true;
				}
			}
		}
		return false;
	}

	public boolean isCollidedRight(Polygon entityPolygon) {
		for (float step = 0; step <= dimension.y; step += dimension.y / 2) {
			if (isCellBlocked(positionScaled.x + dimension.x, positionScaled.y
					+ step)) {
				float posX = (int) ((positionScaled.x + dimension.x) / collisionLayer
						.getTileWidth()) * 32;
				float posY = (int) ((positionScaled.y + step) / collisionLayer
						.getTileWidth()) * 32;
				Polygon blockedPolygon = new Polygon(new float[] { 0, 0, 32, 0,
						32, 32, 0, 32 });
				blockedPolygon.setOrigin(32 / 2, 32 / 2);
				blockedPolygon.setPosition((int) posX, (int) posY);
				
				if (Intersector.overlapConvexPolygons(blockedPolygon,
						entityPolygon)) {

					return true;
				}
			}
		}
		return false;
	}

	public boolean isCollidedLeft(Polygon entityPolygon) {
		for (float step = 0; step <= dimension.y; step += dimension.y / 2) {
			if (isCellBlocked(positionScaled.x, positionScaled.y + step)) {
				float posX = (int) ((positionScaled.x) / collisionLayer
						.getTileWidth()) * 32;
				float posY = (int) ((positionScaled.y + step) / collisionLayer
						.getTileWidth()) * 32;
				Polygon blockedPolygon = new Polygon(new float[] { 0, 0, 32, 0,
						32, 32, 0, 32 });
				blockedPolygon.setOrigin(32 / 2, 32 / 2);
				blockedPolygon.setPosition((int) posX, (int) posY);
				
				if (Intersector.overlapConvexPolygons(blockedPolygon,
						entityPolygon)) {

					return true;
				}
			}
		}
		return false;
	}

	public boolean isCollisionX_L() {
		return collisionX_L;
	}

	public boolean isCollisionX_R() {
		return collisionX_R;
	}

	public boolean isCollisionY_T() {
		return collisionY_T;
	}

	public boolean isCollisionY_D() {
		return collisionY_D;
	}

	public boolean isCollisionX() {
		return collisionX;
	}

	public boolean isCollisionY() {
		return collisionY;
	}
}

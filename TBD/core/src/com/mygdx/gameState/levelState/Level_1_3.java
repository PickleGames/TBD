package com.mygdx.gameState.levelState;


import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.entities.Entity;
import com.mygdx.entities.enemy.Boss1;
import com.mygdx.entities.enemy.BossFinal;
import com.mygdx.entities.enemy.Enemy;
import com.mygdx.entities.enemy.Grunt;
import com.mygdx.entities.enemy.SecurityGuard;
import com.mygdx.entities.player.Bob;
import com.mygdx.gameStateManager.GameStateManager;
import com.mygdx.jukeBox.MusicBox;
import com.mygdx.util.UserInterface;

public class Level_1_3 extends LevelState{
	ShapeRenderer shaperenderer = new ShapeRenderer();
//	private BitmapFont font = new BitmapFont();
	private Bob bob;


	private String levelDataDir = "/levelData/Level0/Level_3_data.xml";
	private String enemyDataDir = "/levelData/Level0/EnemyData_Level3.xml";

	
	public Level_1_3(LevelStateManager lsm) {
		super(lsm);
		levelFileReader.readLevelData(levelDataDir);
		levelFileReader.readEnemyData((enemyDataDir));
		init();
	}
	
	
	@Override
	public void init() {
		lsm.setCurrentLevel(LevelStateManager.LEVEL1_3);
		
		MusicBox.play("Alah");
		MusicBox.loop("Alah", true);
		setMap(new TmxMapLoader().load("map/level_4.tmx"));
		mapRenderer = new OrthogonalTiledMapRenderer(getMap());
		
		Entity.setCurrentLevel(this);
		

//		enemyList = new Enemy[levelFileReader.getEnemyNum()];		
		enemyList = new ArrayList<Enemy>();
		
		bob = new Bob(new Vector2(640, 32));
		
		setBob(bob);
		setUI(new UserInterface(bob));

		//boss = new Boss1(new Vector2(100, 100));

		//enemyList.add(boss);

		for(int i = 0; i < levelFileReader.getEnemyPosition().length; i++){
			Vector3 tempVec = levelFileReader.getEnemyPosition()[i];
			if(tempVec.z == 1){
				enemyList.add(new Grunt(new Vector2(tempVec.x, tempVec.y)));
			}else if(tempVec.z == 2){
				enemyList.add(new Boss1(new Vector2(tempVec.x, tempVec.y)));
			}else if(tempVec.z == 3){
				enemyList.add(new SecurityGuard(new Vector2(tempVec.x, tempVec.y)));
			}else if(tempVec.z == 4){
				enemyList.add(new BossFinal(new Vector2(tempVec.x, tempVec.y)));
			}
			
		}
		
		System.out.println(enemyList);
		Enemy.setTarget(bob);
		
		

	}

	@Override
	public void update(float dt) {
		if(!isPause){
			bob.update(dt);

	
			for(int i = 0; i<enemyList.size();i++){
				Enemy e = enemyList.get(i);
				e.update(dt);
				if(e.isDead()){
					enemyList.remove(e);
					i--;
				}
			}

//			if(bob.isDead()){
//				lsm.setState(LevelStateManager.LEVEL1_0);
			//}else 
			if(enemyList.size() == 0 && !bob.isDead()){
				isCompleted = true;
			}
			//isCompleted = true;
			if(isCompleted){
//				lsm.setState(LevelStateManager.LOADING);

				System.out.println(cam.viewportWidth);
				System.out.println(cam.viewportHeight);
				lsm.gsm.setState(GameStateManager.CUTSCENE_END);
			}
		}

	}

	@Override
	public void render() {


		cam.unproject(new Vector3(bob.getPositionScaled(),0));
		cam.position.lerp(new Vector3(bob.getOriginPosition(), 0), 0.5f);

		mapRenderer.setView(cam);
		mapRenderer.render();
		
//		shaperenderer.setProjectionMatrix(cam.combined);
//		shaperenderer.begin(ShapeType.Line);
//		shaperenderer.setColor(Color.RED);
//		shaperenderer.polygon(bob.getPolygon().getTransformedVertices());
//		shaperenderer.polygon(boss.getPolygon().getTransformedVertices());
//		shaperenderer.polygon(grunt.getPolygon().getTransformedVertices());

//		for(Enemy e : enemyList){
//			for(Bullet bullet : e.bulList){
//				shaperenderer.polygon(bullet.getPolygon().getTransformedVertices());
//			}
//		}
		
//		shaperenderer.end();

		
		if(Gdx.input.isKeyPressed(Keys.X)) {
			cam.viewportHeight+=9;
			cam.viewportWidth+=16;
		}
		if(Gdx.input.isKeyPressed(Keys.Z)) {
			cam.viewportHeight-=9;
			cam.viewportWidth-=16;
		}
		cam.update();
		spriteBatch.setProjectionMatrix(cam.combined);
		
//		System.out.println(cam.viewportWidth);
//		System.out.println(cam.viewportHeight);
		
		spriteBatch.begin();
			for(Enemy enemy : enemyList){
				enemy.render(spriteBatch);;
			}	
			//boss.render(spriteBatch);

			bob.render(spriteBatch);

			
			getUI().render(spriteBatch);
			
		

		spriteBatch.end();
	}


	@Override
	public void dispose() {
		// TODO Auto-generated method stub

		bob = null;
		if(isReset){
			for(Enemy e:enemyList){
				e.dispose();
			}
		}
		enemyList.removeAll(enemyList);
		Enemy.removeTarget();
		getMap().dispose();
		MusicBox.stop("Alah");

	}

}

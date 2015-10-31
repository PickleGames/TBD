package com.mygdx.gameState.levelState;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.entities.Entity;
import com.mygdx.entities.enemy.Boss1;
import com.mygdx.entities.enemy.Enemy;
import com.mygdx.entities.enemy.Grunt;
import com.mygdx.entities.enemy.SecurityGuard;
import com.mygdx.entities.player.Bob;
import com.mygdx.game.TBDGame;
import com.mygdx.jukeBox.MusicBox;
import com.mygdx.util.UserInterface;

public class HordeMode extends LevelState {
//	ShapeRenderer shaperenderer = new ShapeRenderer();
	private BitmapFont font = new BitmapFont();
	private Bob bob;
	private float timeElapsed;
	private Random rand;
	private int killCount;
	private float countDown =10;
	private int Wave =5;
	private final int MAX_ENEMY = 15;

	Random randE = new Random();
	public HordeMode(LevelStateManager lsm) {
		super(lsm);
		init();
	}

	@Override
	public void init() {
		// lsm.setCurrentLevel(LevelStateManager.LEVEL1_0);
		lsm.setCurrentLevel(LevelStateManager.HORDE);
		MusicBox.play("Alah_Remix");
		MusicBox.loop("Alah_Remix", true);
		setMap(new TmxMapLoader().load("map/horde_lvl.tmx"));
		mapRenderer = new OrthogonalTiledMapRenderer(getMap());

		Entity.setCurrentLevel(this);

		rand = new Random();

		enemyList = new ArrayList<Enemy>();

		bob = new Bob(new Vector2(500, 500));

		setBob(bob);
		setUI(new UserInterface(bob));

		System.out.println(enemyList);
		Enemy.setTarget(bob);

	}

	@Override
	public void update(float dt) {
		if (!isPause) {
			timeElapsed += dt;
			bob.update(dt);
			if (timeElapsed > 1 && enemyList.size() - Wave < MAX_ENEMY) {
				countDown--;
				timeElapsed = 0;
			}
			if(countDown == 0 && enemyList.size() - Wave < MAX_ENEMY ){
				countDown = 10;
				for(int i = 0; i < Wave; i++){
					int r = randE.nextInt(4);
					if(r == 0){
						enemyList.add(new Grunt(new Vector2(rand
								.nextInt(TBDGame.WIDTH), rand
								.nextInt(TBDGame.HEIGHT))));
					}
					else if(r == 1){
						enemyList.add(new Boss1(new Vector2(rand
								.nextInt(TBDGame.WIDTH), rand
								.nextInt(TBDGame.HEIGHT))));
					}
					else if(r == 2){
						enemyList.add(new SecurityGuard(new Vector2(rand
								.nextInt(TBDGame.WIDTH), rand
								.nextInt(TBDGame.HEIGHT))));
					}
				}
				Wave+=5;
			
			}
			if (enemyList.size() > 0) {
				for(int i = 0; i<enemyList.size();i++){
					Enemy e = enemyList.get(i);
					e.update(dt);
					if(e.isDead()){
						enemyList.remove(e);
						i--;
						killCount++;
					}
				}
			}

			if (enemyList.size() == 0 && !bob.isDead()) {
				isCompleted = true;
			}
			

		}
		if(killCount>lsm.getHighScore()){
			lsm.setHighScore(killCount);
		}

	}

	@Override
	public void render() {

		cam.unproject(new Vector3(bob.getPositionScaled(), 0));
		cam.position.lerp(new Vector3(bob.getOriginPosition(), 0), 0.5f);

		mapRenderer.setView(cam);
		mapRenderer.render();

//		shaperenderer.setProjectionMatrix(cam.combined);
//		shaperenderer.begin(ShapeType.Line);
//		shaperenderer.setColor(Color.RED);
//		shaperenderer.polygon(bob.getPolygon().getTransformedVertices());

		/*
		 * for (Enemy e : enemyList) { for (Bullet bullet : e.bulList) {
		 * shaperenderer.polygon(bullet.getPolygon() .getTransformedVertices());
		 * } }
		 */

		//shaperenderer.end();

		if (Gdx.input.isKeyPressed(Keys.X)) {
			cam.viewportHeight += 9;
			cam.viewportWidth += 16;
		}
		if (Gdx.input.isKeyPressed(Keys.Z)) {
			cam.viewportHeight -= 9;
			cam.viewportWidth -= 16;
		}
		cam.update();
		spriteBatch.setProjectionMatrix(cam.combined);

		// System.out.println(cam.viewportWidth);
		// System.out.println(cam.viewportHeight);

		spriteBatch.begin();
		if (enemyList.size() > 0) {
			for (Enemy enemy : enemyList) {
				enemy.render(spriteBatch);
				;
			}
		}
		// boss.render(spriteBatch);
		bob.render(spriteBatch);
		font.draw(spriteBatch, "HORDE", 0, 0);
		font.draw(spriteBatch,"NEXT WAVE IN: "+countDown,LevelState.getCam().position.x -55, 
				LevelState.getCam().position.y +  LevelState.getCam().viewportHeight / 2 -45);
		font.draw(spriteBatch,"KILLS: "+killCount,LevelState.getCam().position.x -25, 
				LevelState.getCam().position.y +  LevelState.getCam().viewportHeight / 2 -30);
		font.draw(spriteBatch,"HIGHSCORE: "+ lsm.getHighScore(),LevelState.getCam().position.x -45, 
				LevelState.getCam().position.y +  LevelState.getCam().viewportHeight / 2 -10);

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
		MusicBox.stop("Alah_Remix");
	}
}

package com.mygdx.util;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.badlogic.gdx.math.Vector3;

public class LevelFileReader {
	private String mapDir = "";
	private int level;
	private int enemyNum;
	private Vector3[] enemyPosition;
	public boolean isReadSuccessed;
	
	public LevelFileReader(){
		
	}
	
	public LevelFileReader(String dir){

	}
	

	
	public void readLevelData(String dir){
		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(LevelFileReader.class.getResourceAsStream(dir));
			doc.getDocumentElement().normalize();
			
			NodeList rootNodes = doc.getElementsByTagName("Level");
			Node rootNode = rootNodes.item(0);
			Element rootElement = (Element)rootNode;
			
			Node levelNumberNode = rootElement.getElementsByTagName("LevelNumber").item(0);
			Element levelNumber = (Element)levelNumberNode;
			this.level = Integer.parseInt(levelNumber.getTextContent());
			System.out.println(levelNumber.getTextContent());
			
			Node mapDirNode = rootElement.getElementsByTagName("MapDir").item(0);
			Element mapDir = (Element)mapDirNode;
			this.mapDir = mapDir.getTextContent();
			System.out.println(mapDir.getTextContent());
			
			Node enemyNumberNode = rootElement.getElementsByTagName("EnemyNumber").item(0);
			Element enemyNumber = (Element)enemyNumberNode;
//			this.enemyNum = Integer.parseInt(enemyNumber.getTextContent());
//			this.enemyPosition = new Vector2[enemyNum];
			System.out.println(enemyNumber.getTextContent());
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void readEnemyData(String dir){
		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(LevelFileReader.class.getResourceAsStream(dir));
//			System.out.println("LOL1");
			doc.getDocumentElement().normalize();
//			System.out.println("LOL2");
			NodeList rootNodes = doc.getElementsByTagName("Enemies");
			Node rootNode = rootNodes.item(0);
			Element rootElement = (Element)rootNode;
			
			NodeList enemyList = rootElement.getElementsByTagName("Enemy");
			this.enemyNum = enemyList.getLength();
//			this.enemyNum = 1;
			this.enemyPosition = new Vector3[enemyNum];
			for(int i = 0; i < enemyPosition.length; i++){
				Node enemy = enemyList.item(i);
				Element simpleEnemy = (Element)enemy;
				float Xpos = Float.parseFloat(simpleEnemy.getAttribute("positionX")) * 32;
				float Ypos = Float.parseFloat(simpleEnemy.getAttribute("positionY")) * 32;
				float type = Float.parseFloat(simpleEnemy.getAttribute("type"));
				enemyPosition[i] = new Vector3(Xpos, Ypos, type);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String getMapDir() {
		return mapDir;
	}

	public int getLevel() {
		return level;
	}

	public int getEnemyNum() {
		return enemyNum;
	}

	public Vector3[] getEnemyPosition() {
		return enemyPosition;
	}

	public boolean isReadSuccessed() {
		return isReadSuccessed;
	}

	public void readData(){

	}
	
	
}

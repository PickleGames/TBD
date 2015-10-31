package com.mygdx.jukeBox;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;


public class SoundBox {
	private static HashMap<String, Sound> sounds;
	
	static {
		sounds = new HashMap<String, Sound>();
	}
	
	public static void loadSound(String path, String name){
		Sound music = Gdx.audio.newSound(Gdx.files.internal(path));
		sounds.put(name, music);
	}
	
	public static void setVolume(String name, float v){
		long id = sounds.get(name).play();
		sounds.get(name).setVolume(id, v);
	}
	
	public static void play(String name){
		sounds.get(name).play();
	}
	
	public static void stop(String name){
		sounds.get(name).stop();
	}
	
	public static void loop(String name){
		sounds.get(name).loop();
	}
}

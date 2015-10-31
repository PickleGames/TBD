package com.mygdx.jukeBox;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;


public class MusicBox {
	private static HashMap<String, Music> musics;
	public static Music getMusic(String name){
		return musics.get(name);
	}
	static {
		musics = new HashMap<String, Music>();
	}
	
	public static void loadMusic(String path, String name){
		Music music = Gdx.audio.newMusic(Gdx.files.internal(path));
		musics.put(name, music);
		
	}
	

	public static void setVolume(String name, float v){
		musics.get(name).setVolume(v);
	}
	
	public static void play(String name){
		musics.get(name).play();
	}
	
	public static void stop(String name){
		musics.get(name).stop();
	}
	
	public static void loop(String name, boolean loop){
		musics.get(name).setLooping(loop);
	}
	

}

package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GDXGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img;
        private World theWorld;
	private Music music;
	@Override
	public void create () {
		batch = new SpriteBatch();
		
                theWorld = new World();
                music = Gdx.audio.newMusic(Gdx.files.getFileHandle("music.mp3", FileType.Internal));
                music.setVolume(0.5f);
                music.play();
                music.setLooping(true);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.5f, 0.6f, 0.75f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                
                //pause (ou pas) la musique 
                if (Gdx.input.isKeyPressed(Keys.P)) {
                    music.pause();
                }
                if (Gdx.input.isKeyPressed(Keys.O)){
                    music.play();
                }
                theWorld.draw();
                theWorld.move(1, 1);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
                
	}
}

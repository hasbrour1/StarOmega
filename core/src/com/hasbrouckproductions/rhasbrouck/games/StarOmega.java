package com.hasbrouckproductions.rhasbrouck.games;

/*

	Main Class
	Loads settings and starts game

 */

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StarOmega extends Game {
	public SpriteBatch batch;
	
	@Override
	public void create () {

		batch = new SpriteBatch();

		//load settings and assets
		Assets.load();
		Settings.load();

		setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}

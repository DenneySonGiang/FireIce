package com.denney.fireice;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.denney.fireice.Screens.MenuScreen;
import com.denney.fireice.Screens.PlayScreen;



/**
 * Created by Denney Giang on 2/28/2016.
 */

public class FireIce extends Game {
	//Variables
	public static final int V_WIDTH = 176;
	public static final int V_HEIGHT = 400;
	public static final float PPM = 100;
	public SpriteBatch batch;

	//Constructor for basic structure of game
	@Override
	public void create() {
		batch = new SpriteBatch();
		setScreen(new MenuScreen(this));
	}

	//Rendering frames
	@Override
	public void render() {
		super.render();
	}
}
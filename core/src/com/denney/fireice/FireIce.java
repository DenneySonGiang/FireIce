package com.denney.fireice;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.denney.fireice.Screens.MenuScreen;



/**
 * Created by Denney Giang on 2/28/2016.
 */

public class FireIce extends Game {
	//Variables used for referencing with other classes
	public static final int V_WIDTH = 176;
	public static final int V_HEIGHT = 400;
	public static final float PPM = 100;
	public String mapName;
	public SpriteBatch batch;

	//Constructor for the game (initializes game variable and tells the game to launch a
	//Specific screen to start the game
	@Override
	public void create() {
		batch = new SpriteBatch();
		setScreen(new MenuScreen(this));
		mapName = null;
	}

	//Rendering frames
	@Override
	public void render() {
		super.render();
	}
}
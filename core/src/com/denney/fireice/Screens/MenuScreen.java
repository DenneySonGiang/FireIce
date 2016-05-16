package com.denney.fireice.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.denney.fireice.FireIce;

import java.util.logging.FileHandler;


/**
 * Created by Denney Giang on 4/14/2016.
 */
public class MenuScreen implements Screen {
    private Viewport viewport;
    private Stage stage;
    private Game game;

    public MenuScreen(Game game) {
        this.game = game;
        viewport = new FitViewport(FireIce.V_WIDTH, FireIce.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((FireIce) game).batch);

        Label.LabelStyle redFont = new Label.LabelStyle(new BitmapFont(), Color.RED);
        Label.LabelStyle whiteFont = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        Label.LabelStyle blueFont = new Label.LabelStyle(new BitmapFont(), Color.CYAN);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        Label fireLabel = new Label("Fire ", redFont);
        fireLabel.setFontScale(2);
        Label andLabel = new Label("& ", whiteFont);
        andLabel.setFontScale(2);
        Label iceLabel = new Label("Ice", blueFont);
        iceLabel.setFontScale(2);
        table.add(fireLabel);
        table.add(andLabel);
        table.add(iceLabel);
        table.padTop(25);

        stage.addActor(table);
    }

    public void handleInput(float dt) {
        if(Gdx.input.justTouched()) {
            this.dispose();
            game.setScreen(new PlayScreen((FireIce) game));
        }
    }

    public void update(float dt) {
        handleInput(dt);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}

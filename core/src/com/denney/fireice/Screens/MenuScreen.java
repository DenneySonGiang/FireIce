package com.denney.fireice.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.denney.fireice.FireIce;


/**
 * Created by Denney Giang on 4/14/2016.
 */
public class MenuScreen implements Screen {
    private FireIce fireIce;
    private Viewport viewport;
    private Stage stage;
    private Game game;

    private TextureAtlas buttonAtlas;
    private TextButton.TextButtonStyle buttonStyle;
    private TextButton button1, button2, button3;
    private Skin skin;

    public MenuScreen(Game game) {
        this.game = game;
        fireIce = (FireIce)game;
        viewport = new FitViewport(FireIce.V_WIDTH, FireIce.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((FireIce) game).batch);

        Label.LabelStyle redFont = new Label.LabelStyle(new BitmapFont(), Color.RED);
        Label.LabelStyle whiteFont = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        Label.LabelStyle blueFont = new Label.LabelStyle(new BitmapFont(), Color.CYAN);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        final Label fireLabel = new Label("Fire ", redFont);
        fireLabel.setFontScale(1.5f);
        Label andLabel = new Label("& ", whiteFont);
        andLabel.setFontScale(1.5f);
        Label iceLabel = new Label("Ice", blueFont);
        iceLabel.setFontScale(1.5f);
        Label authorLabel = new Label("Denney Giang", whiteFont);
        authorLabel.setFontScale(.75f);
        table.padTop(25);
        table.add(fireLabel);
        table.add(andLabel);
        table.add(iceLabel);
        table.row();
        table.add(authorLabel).colspan(4);
        //Add buttons to menu
        table.row();
        skin = new Skin();
        buttonAtlas = new TextureAtlas("button.pack");
        skin.addRegions(buttonAtlas);
        buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.up = skin.getDrawable("button");
        buttonStyle.down = skin.getDrawable("buttonpressed");
        buttonStyle.font = new BitmapFont();
        button1 = new TextButton("Level 1", buttonStyle);
        table.add(button1).width(50).height(50).colspan(4).center();
        //Input detection for button, when clicked, set the designated map level
        button1.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                fireIce.mapName = "level1.tmx";
                return true;
            }
        });
        table.row();
        button2 = new TextButton("Level 2", buttonStyle);
        table.add(button2).width(50).height(50).colspan(4).center();
        button2.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                fireIce.mapName = "level2.tmx";
                return true;
            }
        });
        table.row();
        button3 = new TextButton("Level 3", buttonStyle);
        table.add(button3).width(50).height(50).colspan(4).center();
        button3.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                fireIce.mapName = "level3.tmx";
                return true;
            }
        });
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    //Input detection, if screen is touched then set the playscreen
    public void handleInput(float dt) {
        if (fireIce.mapName != null) {
            if (Gdx.input.justTouched()) {
                this.dispose();
                game.setScreen(new PlayScreen((FireIce) game));
            }
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

    //Disposes of any objects that are no longer needed to reduce memory usage & storage
    @Override
    public void dispose() {
        stage.dispose();
    }
}

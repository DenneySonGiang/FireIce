package com.denney.fireice.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.denney.fireice.FireIce;
import com.denney.fireice.Sprites.Fire;
import com.denney.fireice.Sprites.Ice;
import com.denney.fireice.Tools.B2WorldCreator;
import com.denney.fireice.Tools.WorldContactListener;

/**
 * Created by Denney Giang on 3/7/2016.
 */

public class PlayScreen implements Screen {
    //Variables
    private FireIce game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private Fire fire;
    private Ice ice;
    //Box2d variables that will detect collision
    private World world;
    private Box2DDebugRenderer b2dr;

    //Constructor
    public PlayScreen(FireIce game) {
        this.game = game;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(FireIce.V_WIDTH / FireIce.PPM, FireIce.V_HEIGHT / FireIce.PPM, gamecam);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(game.mapName);
        renderer = new OrthogonalTiledMapRenderer(map, 1 / FireIce.PPM);
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
        world = new World(new Vector2(0, 0), true);
        fire = new Fire(world);
        ice = new Ice(world);
        b2dr = new Box2DDebugRenderer();
        new B2WorldCreator(world, map);
        world.setContactListener(new WorldContactListener(game));
    }

    @Override
    public void show() {

    }

    //Input Detection, touching left or right of screen & then apply velocity to that direction
    public void handleInput(float dt) {
        if (Gdx.input.getX() > gamePort.getScreenWidth() / 2 && Gdx.input.isTouched() && fire.b2bodyFire.getPosition().x < 162 / FireIce.PPM) {
            fire.b2bodyFire.setLinearVelocity(2, 1);
            ice.b2bodyIce.setLinearVelocity(-2, 1);
        }
        if (Gdx.input.getX() < gamePort.getScreenWidth() / 2 && Gdx.input.isTouched() && fire.b2bodyFire.getPosition().x > 10 / FireIce.PPM) {
            fire.b2bodyFire.setLinearVelocity(-2, 1);
            ice.b2bodyIce.setLinearVelocity(2, 1);
        }
    }

    //Update each frame, and any other variables that need to change per frame
    public void update(float dt) {
        handleInput(dt);
        gamecam.position.y = (fire.b2bodyFire.getPosition().y) + 100 / FireIce.PPM;
        world.step(1 / 60f, 6, 2);
        fire.update(dt);
        ice.update(dt);
        gamecam.update();
        renderer.setView(gamecam);
        fire.b2bodyFire.setLinearVelocity(0, 1);
        ice.b2bodyIce.setLinearVelocity(0,1);
    }

    //Renders the app, configures app to draw/create objects and configures camera
    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
        b2dr.render(world, gamecam.combined);
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        fire.draw(game.batch);
        ice.draw(game.batch);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();

    }
}

package com.denney.fireice.Tools;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.denney.fireice.FireIce;
import com.denney.fireice.Screens.PlayScreen;
import com.denney.fireice.Screens.MenuScreen;



/**
 * Created by Denney Giang on 4/5/2016.
 */
public class WorldContactListener implements ContactListener {
    private Game game;
    public WorldContactListener(Game game) {
        this.game = game;
    }

    //Collision detection between the player & the world objects. It can collide with walls which
    //will reset the level, or collide with the end zone which will reset to the main menu
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        if (fixA.getUserData() == "fire" && fixB.getUserData() == "wall" || fixA.getUserData() == "wall" && fixB.getUserData() == "fire") {
            game.setScreen(new PlayScreen((FireIce) game));
        }
        if (fixA.getUserData() == "ice" && fixB.getUserData() == "wall" || fixA.getUserData() == "wall" && fixB.getUserData() == "ice") {
            game.setScreen(new PlayScreen((FireIce) game));
        }
        if (fixA.getUserData() == "fire" && fixB.getUserData() == "gameover" || fixA.getUserData() == "gameover" && fixB.getUserData() == "fire") {
            game.setScreen(new MenuScreen(game));
        }
        if (fixA.getUserData() == "ice" && fixB.getUserData() == "gameover" || fixA.getUserData() == "gameover" && fixB.getUserData() == "ice") {
            game.setScreen(new MenuScreen(game));
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}

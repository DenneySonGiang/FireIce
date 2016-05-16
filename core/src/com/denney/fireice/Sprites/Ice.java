package com.denney.fireice.Sprites;

/**
 * Created by Denney Giang on 3/30/2016.
 */

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.denney.fireice.FireIce;

//Initialize Variables
public class Ice extends Sprite {
    public World worldIce;
    public Body b2bodyIce;
    private final TextureRegion iceTexture = new TextureRegion(new Texture("blue.png"));

    //Constructor
    public Ice(World world) {
        this.worldIce = world;
        defineIce();
        setBounds(0, 0, 16 / FireIce.PPM, 16 / FireIce.PPM);
        setRegion(iceTexture);
    }

    //Updates the position relative to time
    public void update(float dt) {
        setPosition(b2bodyIce.getPosition().x - getWidth() / 2, b2bodyIce.getPosition().y - getHeight() / 2);
    }

    //Almost similar to a constructor, it builds the object using the Body class and
    //makes sure its collision properties and identification are correctly implemented.
    //Additionally, it sets its shape, size, and position inside the game world
    public void defineIce() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(88 / FireIce.PPM, 16 / FireIce.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2bodyIce = worldIce.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(7 / FireIce.PPM);
        fdef.shape = shape;
        b2bodyIce.createFixture(fdef);
        b2bodyIce.createFixture(fdef).setUserData("ice");
    }
}

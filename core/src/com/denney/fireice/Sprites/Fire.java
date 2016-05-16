package com.denney.fireice.Sprites;

/**
 * Created by Denney Giang on 3/30/2016.
 */

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.denney.fireice.FireIce;

public class Fire extends Sprite{
    public World worldFire;
    public Body b2bodyFire;
    private final TextureRegion fireTexture = new TextureRegion(new Texture("red.png"));

    public Fire(World world) {
        this.worldFire = world;
        defineFire();
        setBounds(0, 0, 16 / FireIce.PPM, 16 / FireIce.PPM);
        setRegion(fireTexture);
    }

    public void update(float dt) {
        setPosition(b2bodyFire.getPosition().x - getWidth() / 2, b2bodyFire.getPosition().y - getHeight() / 2);
        System.out.print(b2bodyFire.getWorldCenter());
    }

    public void defineFire() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(44 / FireIce.PPM, 16 / FireIce.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2bodyFire = worldFire.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(7 / FireIce.PPM);
        fdef.shape = shape;
        b2bodyFire.createFixture(fdef);
        b2bodyFire.createFixture(fdef).setUserData("fire");

    }
}

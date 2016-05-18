package com.denney.fireice.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.denney.fireice.FireIce;

/**
 * Created by Denney Giang on 3/14/2016.
 */
//Creating the game world & map and identifying the hitboxes of the objects in the game world.
//Also positions the game world onto the screen
public class B2WorldCreator {
    public B2WorldCreator(World world, TiledMap map) {
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / FireIce.PPM, (rect.getY() + rect.getHeight() / 2) / FireIce.PPM);
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth() / 2 / FireIce.PPM, rect.getHeight() / 2 / FireIce.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
            body.createFixture(fdef).setUserData("wall");
        }
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / FireIce.PPM, (rect.getY() + rect.getHeight() / 2) / FireIce.PPM);
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth() / 2 / FireIce.PPM, rect.getHeight() / 2 / FireIce.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
            body.createFixture(fdef).setUserData("gameover");
        }
    }
}

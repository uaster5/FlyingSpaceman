package com.games.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.games.game.FlyingSpaceman;

import java.util.Random;

/**
 * Created by Роман on 28.11.2017.
 */

public class Asteroid {
    public static final int AST_WIDTH = 100;
    private Texture asteroid;
    private Vector2 astPos;
    private Random rand;
    private Rectangle bounds;


    public Texture getAsteroid() {
        return asteroid;
    }

    public Vector2 getAstPos() {
        return astPos;
    }

    public Asteroid(float x){
        asteroid = new Texture("asteroid.png");
    rand = new Random();
        astPos= new Vector2(x, rand.nextInt(FlyingSpaceman.HEIGHT-asteroid.getHeight()));
        bounds = new Rectangle(astPos.x, astPos.y, asteroid.getWidth(), asteroid.getHeight());

    }
    public void reposition(float x){
        astPos.set(x, rand.nextInt(FlyingSpaceman.HEIGHT-asteroid.getHeight()));
        bounds.setPosition(astPos);

    }
    public boolean collides(Rectangle player){
        return player.overlaps(bounds);
    }

    public void dispose() {
        asteroid.dispose();
    }
}

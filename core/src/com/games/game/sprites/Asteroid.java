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
    public static final int AST_WIDTH = 100;    //ширина астероїда
    private Texture asteroid;
    private Vector2 astPos;
    private Random rand;
    private Rectangle bounds;   //прямокутник для колізій


    public Texture getAsteroid() {
        return asteroid;
    }

    public Vector2 getAstPos() {
        return astPos;
    }

    public Asteroid(float x) {
        asteroid = new Texture("asteroid.png");
        rand = new Random();
        astPos = new Vector2(x, rand.nextInt(FlyingSpaceman.HEIGHT - asteroid.getHeight()));
        bounds = new Rectangle(astPos.x + 10, astPos.y + 30, asteroid.getWidth() - 30, asteroid.getHeight() - 50);      //створення прямоктника на основі астероїда для
                                                                                                                                            //для перевірки на колізії

    }
    public void reposition(float x){                                                            //зміна по ширині
        astPos.set(x, rand.nextInt(FlyingSpaceman.HEIGHT-asteroid.getHeight()));
        bounds.setPosition(astPos);

    }


    public boolean collides(Rectangle player){
        return player.overlaps(bounds);
    }           //перевірка на колізії

    public void dispose() {
        asteroid.dispose();
    }   //очистити
}

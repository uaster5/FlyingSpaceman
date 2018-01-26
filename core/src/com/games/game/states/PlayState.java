package com.games.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Array;
import com.games.game.FlyingSpaceman;
import com.games.game.sprites.Asteroid;
import com.games.game.sprites.Ship;

import java.util.Date;

/**
 * Created by Роман on 28.11.2017.
 */

public class PlayState extends State {
    private static final int SPACING=200;   //відстань між астероїдами
    private static final int ASTEROID_COUNT = 10;

    private Ship ship;
    private Texture bg;
    private Array<Asteroid> asteroids;
    public static long score;
    public Date d1;
    public float ym=0;
    public PlayState(GameStateManager gsm) {
        super(gsm);
        score=0;
        ship = new Ship(0,FlyingSpaceman.HEIGHT/2);
        camera.setToOrtho(false,FlyingSpaceman.WIDTH , FlyingSpaceman.HEIGHT );
        bg = new Texture("bg.png");
        asteroids= new Array<Asteroid>();
        for (int i = 0; i < ASTEROID_COUNT; i++){
            asteroids.add(new Asteroid(i*(SPACING)+FlyingSpaceman.WIDTH/2));
        }

    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched())
            ship.jump();


    }

    @Override
    public void update(float dt) {
        handleInput();
        ship.update(dt+ym);

        camera.position.x = ship.getPosition().x + 400;             //відбувається рух
            if (score>10)
            ym+=0.00001;



     //   d1.setTime(0);
        for (int i = 0; i < asteroids.size; i++)
        {
            Asteroid asteroid = asteroids.get(i);
            if (camera.position.x - (camera.viewportWidth / 2) > asteroid.getAstPos().x + asteroid.getAsteroid().getWidth()){
              //  asteroid.reposition(asteroid.getAstPos().x + ((Asteroid.AST_WIDTH + SPACING) * ASTEROID_COUNT));
                asteroid.reposition(asteroid.getAstPos().x + FlyingSpaceman.WIDTH+Asteroid.AST_WIDTH+SPACING);
                score++;

            }

            if (asteroid.collides(ship.getBound())) {           //перевірка на колізію
                gsm.set(new GameOver(gsm));
            }

     //       break;
            }
        camera.update();
        }







    @Override
    public void render(SpriteBatch sb) {                    //промальовка об'єктів
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg, camera.position.x - (camera.viewportWidth / 2), 0);
        sb.draw(ship.getShip(), ship.getPosition().x, ship.getPosition().y);
        FlyingSpaceman.font.draw(sb,score+"",camera.position.x,480);
        for (Asteroid asteroid : asteroids) {
            sb.draw(asteroid.getAsteroid(), asteroid.getAstPos().x, asteroid.getAstPos().y);
        }
        sb.end();

    }

    @Override
    public void dispose() { //очитска пам'яті
        bg.dispose();
        ship.dispose();

        for (Asteroid asteroid : asteroids)
            asteroid.dispose();

    }
}

package com.games.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.games.game.FlyingSpaceman;
import com.games.game.sprites.Asteroid;
import com.games.game.sprites.Ship;

/**
 * Created by Роман on 28.11.2017.
 */

public class PlayState extends State {
    private static final int SPACING=125;
    private static final int ASTEROID_COUNT = 10;

    private Ship ship;
    private Texture bg;
    private Array<Asteroid> asteroids;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        ship = new Ship(50,0);
        camera.setToOrtho(false, FlyingSpaceman.WIDTH / 2, FlyingSpaceman.HEIGHT / 2);
        bg = new Texture("bg.png");
        asteroids= new Array<Asteroid>();
        for (int i = 0; i < ASTEROID_COUNT; i++){
            asteroids.add(new Asteroid(i*(SPACING + Asteroid.AST_WIDTH)));
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
        ship.update(dt);
        camera.position.x = ship.getPosition().x + 80;

        for (int i = 0; i < asteroids.size; i++){
            Asteroid asteroid = asteroids.get(i);
            if (camera.position.x - (camera.viewportWidth / 2) > asteroid.getAstPos().x + asteroid.getAsteroid().getWidth()){
                asteroid.reposition(asteroid.getAstPos().x + ((Asteroid.AST_WIDTH + SPACING) * ASTEROID_COUNT));

            }
            if (asteroid.collides(ship.getBound()))
                gsm.set(new GameOver(gsm));
            }
        camera.update();
        }







    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg, camera.position.x - (camera.viewportWidth / 2), 0);
        sb.draw(ship.getShip(), ship.getPosition().x, ship.getPosition().y);
        for (Asteroid asteroid : asteroids) {
            sb.draw(asteroid.getAsteroid(), asteroid.getAstPos().x, asteroid.getAstPos().y);
        }
        sb.end();

    }

    @Override
    public void dispose() {
        bg.dispose();
        ship.dispose();

        for (Asteroid asteroid : asteroids)
            asteroid.dispose();
    }
}

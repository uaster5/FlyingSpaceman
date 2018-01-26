package com.games.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.games.game.FlyingSpaceman;


/**
 * Created by Роман on 28.11.2017.
 */

public class GameOver extends State {
    private Texture background;
    private Texture gameOver;


    public GameOver(GameStateManager gsm) {
        super(gsm);

        camera.setToOrtho(false, FlyingSpaceman.WIDTH / 2, FlyingSpaceman.HEIGHT / 2);
        background = new Texture("bg.png");
        gameOver = new Texture("go.png");



    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }

    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(gameOver, camera.position.x - gameOver.getWidth() / 2, camera.position.y);
        FlyingSpaceman.font.draw(sb,"Your score is: "+PlayState.score,camera.position.x-100, 2*camera.position.y/3); //виведення рахунку
        sb.end();


    }

    @Override
    public void dispose() {
        background.dispose();
        gameOver.dispose();

    }
}

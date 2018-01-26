package com.games.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.games.game.FlyingSpaceman;

/**
 * Created by Роман on 28.11.2017.
 */

public class MenuState extends State {
    private Texture background;
    private Texture playBtn;

    private final String FONT_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";
    public MenuState(GameStateManager gsm) {
        super(gsm);



        camera.setToOrtho(false, FlyingSpaceman.WIDTH / 2, FlyingSpaceman.HEIGHT / 2);
        background = new Texture("bg.png");
        playBtn = new Texture("play.png");
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
        sb.draw(playBtn, (camera.position.x+playBtn.getWidth())/2, camera.position.y/2);
          FlyingSpaceman.font.draw(sb,"Flying Spaceman",80,170);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
     playBtn.dispose();

    }
}

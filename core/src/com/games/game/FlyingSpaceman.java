package com.games.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.games.game.states.GameStateManager;
import com.games.game.states.MenuState;

public class FlyingSpaceman extends ApplicationAdapter {	//тут підключається основні обєкти такі як музика шрифт і т д
	public static final int WIDTH=800;
	public static final int HEIGHT=480;
	public static final String TITLE="Flying Spaceman";
	private GameStateManager gsm;
	private SpriteBatch batch;
	private Music music;
	public  static BitmapFont font;
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator( Gdx.files.internal("bauhaus.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 32;
		parameter.color=Color.YELLOW;

		font = generator.generateFont(parameter);
		generator.dispose();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	//очищення рендера
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	public static void createFonts(BitmapFont font,int size) {			//створення авторського шрифта

		FileHandle fontFile = Gdx.files.internal("BAUHS93.TTF");
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = size;
		parameter.color.add(Color.YELLOW);

		font = generator.generateFont(parameter);

		generator.dispose();
	}
	@Override
	public void dispose () {
		super.dispose();
		music.dispose();

	}
}

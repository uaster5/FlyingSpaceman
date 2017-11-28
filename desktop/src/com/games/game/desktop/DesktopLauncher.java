package com.games.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.games.game.FlyingSpaceman;

public class DesktopLauncher {
	public static void main (String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=FlyingSpaceman.WIDTH;
		config.height=FlyingSpaceman.HEIGHT;
		config.title=FlyingSpaceman.TITLE;

		new LwjglApplication(new FlyingSpaceman(), config);
	}
}

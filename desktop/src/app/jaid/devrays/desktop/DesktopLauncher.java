package app.jaid.devrays.desktop;

import app.jaid.devrays.DevraysGame;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main(String[] args)
	{
		if (args.length > 0)
		{
		}

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS = 60;
		config.backgroundFPS = 15;
		config.title = "Devrays";
		config.useGL30 = true;
		config.addIcon("textures/logo32.png", FileType.Internal);
		new LwjglApplication(new DevraysGame(), config);
	}
}

package app.jaid.devrays.desktop;

import app.jaid.devrays.DevraysGame;
import app.jaid.jtil.JTil;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main(String[] args) {

		System.out.println(JTil.explode(args, ", "));

		if (args.length > 0) {
		}

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new DevraysGame(), config);
	}
}

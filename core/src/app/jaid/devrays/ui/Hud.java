package app.jaid.devrays.ui;

import app.jaid.devrays.io.Media;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Hud extends Table {

	private static final Console	console	= new Console();
	public static final Skin		gdxSkin	= (Skin) Media.get("skins/gdx.json");

	public static Console getConsole()
	{
		return console;
	}

	public Hud() {
		super(gdxSkin);
		gdxSkin.add("default", new BitmapFont(), BitmapFont.class);
		setFillParent(true);
		setTransform(false);
		debug();

		addActor(console);
	}

}

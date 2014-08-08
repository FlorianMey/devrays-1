package app.jaid.devrays.ui;

import app.jaid.devrays.Core;
import app.jaid.devrays.debug.StatsTracker;
import app.jaid.devrays.io.Media;
import app.jaid.devrays.screen.DevraysScreen;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.*;

/**
 * Main GDX Widget that fills the whole screen. Every {@link DevraysScreen} can add additional widgets to this base HUD
 * in its constructor.
 *
 * @author jaid
 */
public class Hud extends Table {

	public static final Skin gdxSkin = (Skin) Media.get("skins/gdx.json");
	private static Hud instance;
	public static final Skin oldSkin = (Skin) Media.get("skins/old/jaidskin.json");

	public static Hud get()
	{
		if (instance == null)
			instance = new Hud();

		return instance;
	}

	public static Console getConsole()
	{
		return get().console;
	}

	private Cell<?> arsenalBoxCell;
	private final Console console = new Console();

	public Hud()
	{
		super(gdxSkin);
		gdxSkin.add("default", new BitmapFont(), BitmapFont.class);
		setFillParent(true);
		setTransform(false);

		top().left();
		add(StatsTracker.get()).left();
		row();
		add(console).expandY().bottom().left();
		arsenalBoxCell = add().expandX().bottom().right().pad(10);
		console.hide();

		Core.getHudStage().addActor(this);
	}

	public Cell<?> getArsenalBoxCell()
	{
		return arsenalBoxCell;
	}
}

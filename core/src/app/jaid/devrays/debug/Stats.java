package app.jaid.devrays.debug;

import app.jaid.devrays.Core;
import app.jaid.devrays.DevraysGame;
import app.jaid.devrays.input.InputCore;
import app.jaid.devrays.screen.ingame.IngameScreen;
import app.jaid.jtil.JTil;
import app.jaid.jtil.JTime;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.badlogic.gdx.utils.OrderedMap;

public class Stats {

	private static final OrderedMap<String, Stat> stats = new OrderedMap<String, Stat>();

	public static void render()
	{
		int height = Core.screenHeight;
		for (Stat stat : stats.values())
		{
			stat.render(25, height);
			height -= 15;
		}
	}

	public static void track(String name, Object value)
	{
		if (stats.containsKey(name))
		{
			Log.warn("Can't add stat " + name + " twice.");
			return;
		}

		stats.put(name, new Stat(name, value));
	}
}
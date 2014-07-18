package app.jaid.devrays.debug;

import app.jaid.devrays.Core;

import com.badlogic.gdx.utils.OrderedMap;

public class Stats {

	/**
	 * Set of {@link Stat} instances that gets rendered every frame in a screen corner.
	 */
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
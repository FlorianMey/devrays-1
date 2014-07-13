package app.jaid.devrays.debug;

import app.jaid.devrays.Core;
import app.jaid.devrays.input.InputCore;
import app.jaid.jtil.JTime;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.badlogic.gdx.utils.OrderedMap;

public class Stats {

	private static final OrderedMap<String, Stat>	stats	= new OrderedMap<String, Stat>(20);

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
		stats.put(name, new Stat(name, value));
	}

	public static void trackCommons()
	{
		track("Surface", "<" + Core.screenWidth + ", " + Core.screenHeight + "> (" + (float) Core.screenWidth / Core.screenHeight + ":1)");
		track("Cursor", "<" + InputCore.getCursorX() + ", " + InputCore.getCursorY() + ">");
		track("World Cursor", InputCore.getWorldCursor().toString(2));
		track("FPS", Gdx.graphics.getFramesPerSecond());
		track("Texture Bindings", GLProfiler.textureBindings);
		track("Draw Calls", GLProfiler.drawCalls);
		track("Vertices", (int) GLProfiler.vertexCount.value);
		track("World Viewport", "<" + Core.getCamera().viewportWidth + ", " + Core.getCamera().viewportHeight + "> (" + Core.getCamera().viewportWidth / Core.getCamera().viewportHeight + ":1)");
		track("World Camera", "<" + Core.getCamera().position.x + ", " + Core.getCamera().position.y + ">");
		track("HUD Viewport", "<" + Core.getHudCamera().viewportWidth + ", " + Core.getHudCamera().viewportHeight + ">");
		track("HUD Camera", "<" + Core.getHudCamera().position.x + ", " + Core.getHudCamera().position.y + ">");
		track("Runtime", JTime.formatUnits("{m}:{s}", Core.getRuntime()));
	}
}
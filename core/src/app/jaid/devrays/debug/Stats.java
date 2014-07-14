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
		track("RAM Usage", JTil.formatBytes(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
		track("Surface", "<" + Core.screenWidth + ", " + Core.screenHeight + "> (" + (float) Core.screenWidth / Core.screenHeight + ":1)");
		track("Cursor", "<" + InputCore.getCursorX() + ", " + InputCore.getCursorY() + ">");
		track("FPS", Gdx.graphics.getFramesPerSecond());
		track("Delta Peak", (int) (Core.getDeltaPeak() * 1000) + " ms");
		track("Bindings/Calls", GLProfiler.textureBindings + ", " + GLProfiler.drawCalls);
		track("Vertices", (int) GLProfiler.vertexCount.value);
		track("HUD Viewport", "<" + Core.getHudCamera().viewportWidth + ", " + Core.getHudCamera().viewportHeight + ">");
		track("HUD Camera", "<" + Core.getHudCamera().position.x + ", " + Core.getHudCamera().position.y + ">");
		track("Runtime", JTime.formatUnits("{m}:{s}", Core.getRuntime()));

		if (DevraysGame.getDevraysScreen() instanceof IngameScreen)
		{
			track("World Cursor", InputCore.getWorldCursor().toString(2));
			track("World Viewport", "<" + Core.getCamera().viewportWidth + ", " + Core.getCamera().viewportHeight + "> (" + Core.getCamera().viewportWidth / Core.getCamera().viewportHeight + ":1)");
			track("World Camera", "<" + Core.getCamera().position.x + ", " + Core.getCamera().position.y + ">");
			track("Entities", IngameScreen.getEnvironment().getMobs().size + "M, " + IngameScreen.getEnvironment().getBullets().size + "B");
		}
	}
}
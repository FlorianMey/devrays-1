package app.jaid.devrays.debug;

import app.jaid.devrays.Core;
import app.jaid.devrays.input.InputCore;
import app.jaid.devrays.mobs.Player;
import app.jaid.devrays.screen.ingame.Environment;
import app.jaid.jtil.JTil;
import app.jaid.jtil.JTime;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.badlogic.gdx.utils.Array;

/**
 * Set of stats that can be either displayed once with command /get or tracked permanently with command /track. Each
 * Enum item returns a String in {@link #getValue}. This method polls states and values from other static method
 * providers, such as Runtime.getRuntime() for memory stats and GLProfiler for OpenGL counters.
 *
 * @author jaid
 */
public enum CoreStat {

	BINDINGS("Texture Bindings"), CALLS("GL Calls"), CURSOR("Cursor Position"), CURSOR_STATE("Cursor State"), DELTA("Smoothed Delta"), DELTA_PEAK("Delta Peak"), DRAWS("Draw Calls"), ENTITIES("Entities Count"), FPS("FPS"), MOVEMENT(
			"Player Movement"), POSITION("Player Position"), RAM("Available RAM"), RAM_USAGE("Used RAM"), RAW_DELTA("Delta"), RUNTIME("Runtime"), SIZE("Window Size"), SPEED("Speed Factor"), VERTICES("Drawn Vertices"), VIEWPORT(
					"World Viewport"), WORLD_CURSOR("World Cursor");

	public static boolean contains(String name)
	{
		for (CoreStat stat : values())
			if (stat.name().equals(name.toUpperCase()))
				return true;

		return false;
	}

	public static CoreStat getByName(String name)
	{
		return valueOf(name.toUpperCase());
	}

	private String name;

	private CoreStat(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public String getValue()
	{
		try
		{
			switch (this)
			{
				case BINDINGS:
					return String.valueOf(GLProfiler.textureBindings);

				case CALLS:
					return String.valueOf(GLProfiler.calls);

				case CURSOR:
					return "<" + InputCore.getCursorX() + ", " + InputCore.getCursorY() + ">";

				case CURSOR_STATE:
					Array<String> activeStates = new Array<String>(3);
					if (Gdx.input.isButtonPressed(Input.Buttons.LEFT))
						activeStates.add("Left");
					if (Gdx.input.isButtonPressed(Input.Buttons.MIDDLE))
						activeStates.add("Middle");
					if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT))
						activeStates.add("Right");
					return activeStates.size > 0 ? JTil.explode(activeStates.toArray(String.class), " + ") : "none";

				case DELTA:
					return String.valueOf(Gdx.graphics.getRawDeltaTime());

				case DELTA_PEAK:
					return String.valueOf((int) (Core.getDeltaPeak() * 1000));

				case DRAWS:
					return String.valueOf(GLProfiler.drawCalls);

				case ENTITIES:
					return Environment.get().getMobs().size + " mobs, " + Environment.get().getBullets().size + " bullets";

				case FPS:
					return String.valueOf(Gdx.graphics.getFramesPerSecond());

				case MOVEMENT:
					Player player = Environment.get().getPlayer();
					return "<" + player.getVelocity() + ", " + player.getMovementAngle() + ">";

				case POSITION:
					return Environment.get().getPlayer().getPosition().toString(4);

				case RAM:
					return String.valueOf(JTil.formatBytes(Runtime.getRuntime().totalMemory()));

				case RAM_USAGE:
					return JTil.formatBytes(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());

				case RAW_DELTA:
					return String.valueOf(Gdx.graphics.getRawDeltaTime());

				case RUNTIME:
					return JTime.formatUnits("{m} minutes {s} seconds", Core.getRuntime());

				case SIZE:
					return "<" + Core.screenWidth + ", " + Core.screenHeight + "> (" + JTil.formatDouble((float) Core.screenWidth / Core.screenHeight, 2) + ":1)";

				case SPEED:
					return String.valueOf(Core.speed);

				case VERTICES:
					return String.valueOf(GLProfiler.vertexCount.latest);

				case VIEWPORT:
					return "<" + Core.getCamera().viewportWidth + ", " + Core.getCamera().viewportHeight + "> (" + JTil.formatDouble(Core.getCamera().viewportWidth / Core.getCamera().viewportHeight, 2) + ":1), shifted by <"
							+ Core.getCamera().position.x + ", " + Core.getCamera().position.y + ">";

				case WORLD_CURSOR:
					return InputCore.getWorldCursor().toString(4);
			}
		} catch (Exception e)
		{
			return "?";
		}

		return "unknown";
	}

	@Override
	public String toString()
	{
		return name() + " (" + name + ")";
	}

}

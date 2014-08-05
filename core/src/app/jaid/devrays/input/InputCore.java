package app.jaid.devrays.input;

import app.jaid.devrays.Core;
import app.jaid.devrays.geo.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

/**
 * Utils for polling inputs. Also collects and caches input results in {@link #update} method which is called every
 * frame.
 *
 * @author jaid
 */
public class InputCore {

	private static int cursorX, cursorY;
	private static Point worldCursor = new Point();

	public static int getCursorX()
	{
		return cursorX;
	}

	public static int getCursorY()
	{
		return cursorY;
	}

	public static int getCursorYInverted()
	{
		return Core.screenHeight - cursorY;
	}

	public static Point getWorldCursor()
	{
		return worldCursor;
	}

	public static boolean isCtrlPressed()
	{
		return Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) || Gdx.input.isKeyPressed(Keys.CONTROL_RIGHT);
	}

	public static void update()
	{
		cursorX = Gdx.input.getX();
		cursorY = Gdx.input.getY();
		worldCursor.set(Point.screenPointToWorldPoint(InputCore.getCursorX(), InputCore.getCursorY()));
	}
}

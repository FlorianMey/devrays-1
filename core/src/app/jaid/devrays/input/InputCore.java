package app.jaid.devrays.input;

import app.jaid.devrays.Core;
import app.jaid.devrays.geo.Point;

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

	public static void updateCursor(int x, int y)
	{
		cursorX = x;
		cursorY = y;
		worldCursor.set(Point.screenPointToWorldPoint(InputCore.getCursorX(), InputCore.getCursorYInverted()));
	}
}

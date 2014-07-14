package app.jaid.devrays.graphics;

import app.jaid.devrays.Core;
import app.jaid.devrays.geo.Point;
import app.jaid.devrays.io.Media;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Drawer {

	public static final ShapeRenderer	shapeRenderer	= new ShapeRenderer();

	public static void drawRect(float x, float y, float width, float height, Color color)
	{
		shapeRenderer.setColor(color);
		shapeRenderer.rect(x, y, width, height);
	}

	public static void drawRect(Rectangle rect, Color color)
	{
		drawRect(rect.x, rect.y, rect.width, rect.height, color);
	}

	public static void drawTextOnScreen(String text, int x, int y)
	{
		Media.play.draw(Core.getBatch(), text, x, y);
	}

	public static void drawTextOnWorld(String text, float x, float y)
	{
		Point screenPoint = Point.worldPointToScreenPoint(x, y);
		Media.play.draw(Core.getBatch(), text, screenPoint.x, screenPoint.y);
	}

	public static void drawTextOnWorld(String text, Point position)
	{
		drawTextOnWorld(text, position.x, position.y);
	}

	public static ShapeRenderer getShapeRenderer()
	{
		return shapeRenderer;
	}
}

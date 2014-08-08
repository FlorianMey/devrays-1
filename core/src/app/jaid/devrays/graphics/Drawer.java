package app.jaid.devrays.graphics;

import app.jaid.devrays.Core;
import app.jaid.devrays.debug.Log;
import app.jaid.devrays.geo.Point;
import app.jaid.devrays.geo.Rect;
import app.jaid.devrays.io.Media;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Static methods for drawing objects. Should be rather called than using own implementations.
 *
 * @author jaid
 */
public class Drawer {

	private static FrameBuffer hudFbo;
	private static final ShapeRenderer shapeRenderer = new ShapeRenderer();

	public static void drawPointOnWorld(Point point, Color color)
	{
		drawRectOnScreen(Rect.fromCenter(Point.worldPointToScreenPoint(point), 2), color);
	}

	public static void drawRect(float x, float y, float width, float height, Color color)
	{
		shapeRenderer.setColor(color);
		shapeRenderer.rect(x, y, width, height);
	}

	public static void drawRectOnScreen(Rectangle rect, Color color)
	{
		// Log.debug("Draw rect " + rect + " with color " + color + ".");
		boolean changedColor = false;
		Color previousColor = shapeRenderer.getColor();

		if (previousColor.equals(color))
		{
			shapeRenderer.setColor(color);
			changedColor = true;
		}

		drawRect(rect.x, rect.y, rect.width, rect.height, color);

		if (changedColor)
			shapeRenderer.setColor(previousColor);
	}

	public static void drawSprite(TextureRegion texture, Point position)
	{
		drawSprite(texture, position, texture.getRegionWidth() / 16f, texture.getRegionHeight() / 16f);
	}

	public static void drawSprite(TextureRegion texture, Point position, float width, float height)
	{
		drawSprite(texture, position, width, height, null);
	}

	public static void drawSprite(TextureRegion texture, Point position, float width, float height, Color color)
	{
		if (texture == null)
		{
			Log.warn("Tried to render a sprite with texture null.");
			return;
		}

		boolean changedColor = false;
		Color previousColor = Core.getBatch().getColor();

		if (previousColor.equals(color))
		{
			Core.getBatch().setColor(color);
			changedColor = true;
		}

		Core.getBatch().draw(texture, position.x, position.y, width, height);

		if (changedColor)
			Core.getBatch().setColor(previousColor);
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

	public static FrameBuffer getHudFbo()
	{
		return hudFbo;
	}

	public static ShapeRenderer getShapeRenderer()
	{
		return shapeRenderer;
	}

	public static void resize()
	{
		Drawer.getShapeRenderer().setProjectionMatrix(Core.getHudCamera().combined);
		hudFbo = new FrameBuffer(Format.RGBA8888, Core.screenWidth, Core.screenHeight, false);
	}
}

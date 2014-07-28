package app.jaid.devrays.geo;

import app.jaid.devrays.graphics.Drawer;
import app.jaid.devrays.physics.Colliding;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

/**
 * LibGDX Rectangle with Devrays specific extension methods.
 *
 * @author jaid
 */
public class Rect extends Rectangle implements Colliding {

	private static final long serialVersionUID = 1L;

	public static Rectangle fromCenter(Point point, int radius)
	{
		return new Rect(point.x - radius, point.y - radius, radius * 2, radius * 2);
	}

	public static Rectangle worldRectToScreenRect(Rect worldRect)
	{
		return new Rect(Point.worldPointToScreenPoint(worldRect.getLowerPoint()), Point.worldPointToScreenPoint(worldRect.getHigherPoint()));
	}

	public Rect(float x, float y, float width, float height)
	{
		super(x, y, width, height);
	}

	public Rect(Point corner, float width, float height)
	{
		this(corner.x, corner.y, width, height);
	}

	public Rect(Point corner1, Point corner2)
	{
		this(corner1.x, corner1.y, corner2.x - corner1.x, corner2.y - corner1.y);
	}

	@Override
	public boolean collidesWith(Colliding other)
	{
		return false;
	}

	public Point getHigherPoint()
	{
		return new Point(x + width, y + height);
	}

	public Point getLowerPoint()
	{
		return new Point(x, y);
	}

	@Override
	public void renderWorldBounds(Color color)
	{
		Drawer.getShapeRenderer().set(ShapeType.Line);
		Drawer.drawRectOnScreen(worldRectToScreenRect(this), color);
	}
}

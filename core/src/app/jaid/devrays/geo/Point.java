package app.jaid.devrays.geo;

import app.jaid.devrays.Core;
import app.jaid.devrays.physics.Colliding;
import app.jaid.jtil.JTil;

import com.badlogic.gdx.graphics.Camera;

public class Point implements Colliding {

	public static final Point	neutral	= new Point(0, 0);

	public static Point midOf(float x, float y, float width, float height)
	{
		return new Point(x + width / 2, y + height / 2);
	}

	public static Point midOf(Rect rect)
	{
		return new Point(rect.x + rect.width / 2, rect.y + rect.height / 2);
	}

	public static Point screenPointToWorldPoint(int screenX, int screenY)
	{
		Camera camera = Core.getCamera();
		float worldX = JTil.keepBetween(0, screenX, Core.screenWidth) / (Core.screenWidth / camera.viewportWidth) + camera.position.x - camera.viewportWidth / 2;
		float worldY = JTil.keepBetween(0, screenY, Core.screenHeight) / (Core.screenHeight / camera.viewportHeight) + camera.position.y - camera.viewportHeight / 2;
		return new Point(worldX, worldY);
	}

	public static Point worldPointToScreenPoint(float worldX, float worldY)
	{
		Camera camera = Core.getCamera();
		float screenX = (worldX - camera.position.x + camera.viewportWidth / 2) * (Core.screenWidth / camera.viewportWidth);
		float screenY = (worldY - camera.position.y + camera.viewportHeight / 2) * (Core.screenHeight / camera.viewportHeight);
		return new Point(screenX, screenY);
	}

	public static Point worldPointToScreenPoint(Point worldPoint)
	{
		return worldPointToScreenPoint(worldPoint.x, worldPoint.y);
	}

	public float	x, y;

	public Point() {
	}

	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Point(Point base) {
		x = base.x;
		y = base.y;
	}

	public Point add(float addX, float addY)
	{
		return new Point(x + addX, y + addY);
	}

	public Angle angleTo(Point origin)
	{
		return Angle.fromRadians(Math.atan2(origin.x - x, origin.y - y));
	}

	@Override
	public boolean collidesWith(Colliding other)
	{
		if (other instanceof Point)
		{
			Point otherPoint = (Point) other;

			return x == otherPoint.x && y == otherPoint.y;
		}

		return false;
	}

	public float distanceTo(Point origin)
	{
		return (float) Math.sqrt(Math.pow(origin.y - y, 2) + Math.pow(origin.x - x, 2));
	}

	@Override
	public boolean equals(Object object) // Overridden, Object.equals() seemed to not work properly here
	{
		if (!(object instanceof Point))
			return false;

		Point point = (Point) object;

		if (x == point.x && y == point.y)
			return true;

		return false;
	}

	public void move(Angle direction, float distance)
	{
		x -= Math.sin(direction.getRadians()) * distance;
		y -= Math.cos(direction.getRadians()) * distance;
	}

	public void moveTo(Point origin, float distance)
	{
		if (distance > 0 && distanceTo(origin) < distance)
			set(origin);
		else
			move(origin.angleTo(this), distance);
	}

	public void set(float newX, float newY)
	{
		x = newX;
		y = newY;
	}

	public void set(Point update)
	{
		set(update.x, update.y);
	}

	public void setRotation(Point origin, Angle angle, float distance)
	{
		set(origin);
		move(angle, distance);
	}

	public Point snapToGrid(float steps)
	{
		float invert = 1f / steps;
		return new Point(Math.round(x * (int) invert) / invert, Math.round(y * (int) invert) / invert);
	}

	public Point subtract(float subtractX, float subtractY)
	{
		return new Point(x - subtractX, y - subtractY);
	}

	@Override
	public String toString()
	{
		return "<" + x + ", " + y + ">";
	}

	public String toString(int commaDigits)
	{
		return "<" + JTil.trimCommaDigits(x, commaDigits) + ", " + JTil.trimCommaDigits(y, commaDigits) + ">";
	}

}
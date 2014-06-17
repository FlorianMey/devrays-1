package app.jaid.devrays.math;

import java.awt.Rectangle;

import app.jaid.devrays.Core;

import com.badlogic.gdx.graphics.Camera;

public class Point {

	public static final Point	neutral	= new Point(0, 0);

	public static Point fromScreenPoint(int x, int y, Camera camera)
	{
		return new Point(Math.min(Math.max(0, x), Core.screenWidth) / (Core.screenWidth / camera.viewportWidth) + camera.position.x - camera.viewportWidth / 2, Math.min(Math.max(0, y), Core.screenHeight)
				/ (Core.screenHeight / camera.viewportHeight) + camera.position.y - camera.viewportHeight / 2);
	}

	public static Point midOf(float posX, float posY, float width, float height)
	{
		return new Point(posX + width / 2, posY + height / 2);
	}

	public static Point midOf(Rectangle rect)
	{
		return new Point(rect.x + rect.width / 2, rect.y + rect.height / 2);
	}

	public float	x, y;

	public Point() {
	}

	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Angle angleTo(Point origin)
	{
		return Angle.fromRadians(Math.atan2(origin.x - x, origin.y - y));
	}

	public Point applyToGrid(float steps)
	{
		float invert = 1f / steps;
		return new Point(Math.round(x * (int) invert) / invert, Math.round(y * (int) invert) / invert);
	}

	public float distanceTo(Point origin)
	{
		return (float) Math.sqrt(Math.pow(origin.y - y, 2) + Math.pow(origin.x - x, 2));
	}

	@Override
	public boolean equals(Object object)
	{
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

	@Override
	public String toString()
	{
		return "@ <" + x + ", " + y + ">";
	}

}
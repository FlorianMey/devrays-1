package app.jaid.devrays.entity;

import app.jaid.devrays.Core;
import app.jaid.devrays.math.Angle;
import app.jaid.devrays.math.Point;
import app.jaid.jtil.JTil;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Mob implements Entity {

	private Angle	angle;
	private Point	position;
	private float	speed;
	public Sprite	sprite;

	public Mob(Point position) {
		this.position = position;
		angle = Angle.fromRadians(0);
	}

	public float getBraking()
	{
		return 0.15f;
	}

	@Override
	public Point getPosition()
	{
		return position;
	}

	public float getSpeed()
	{
		return 0.5f * 5;
	}

	public float getSteering()
	{
		return 0.5f;
	}

	public void moveByVelocity()
	{
		position.move(angle, speed);
		speed = JTil.normalize(speed, 5 * getBraking() * Core.delta);
	}

	public void push(Angle direction, float power)
	{
		if (speed == 0)
			angle.setRadians(direction.getRadians());
		else
			angle = angle.moveTo(direction, angle.getShortestRotateDirection(direction) * getSteering() * power * Core.delta * 10);

		speed = Math.max(speed, power);
	}

	@Override
	public void render()
	{
		sprite.draw(Core.batch);
	}

	@Override
	public boolean update()
	{
		if (getBraking() != 1)
			moveByVelocity();

		sprite.setPosition(position.x, position.y); // TODO Rather update lazier (only when needed)

		return true;
	}
}

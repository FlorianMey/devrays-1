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
		return 0.5f * 15;
	}

	@Override
	public Point getPosition()
	{
		return position;
	}

	public float getSpeed()
	{
		return 0.5f * 20;
	}

	public float getSteering()
	{
		return 0.5f;
	}

	public void moveByVelocity()
	{
		position.move(angle, speed * Core.delta);
		speed = JTil.normalize(speed, 5 * getBraking() * Core.delta);
	}

	public void push(Angle direction, float power)
	{
		if (speed == 0 || getSteering() == 1)
			angle.setRadians(direction.getRadians());
		else
			angle = angle.moveTo(direction, angle.getShortestRotateDirection(direction) * getSteering() * power * Core.delta * 5);

		speed = Math.max(speed, power);
	}

	@Override
	public void render()
	{
		Core.getBatch().draw(sprite.getTexture(), position.x, position.y, 2, 1);
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

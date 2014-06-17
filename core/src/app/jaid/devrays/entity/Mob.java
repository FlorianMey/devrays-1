package app.jaid.devrays.entity;

import app.jaid.devrays.Core;
import app.jaid.devrays.math.Point;
import app.jaid.jtil.JTil;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Mob implements Entity {

	private Point	position;
	private float	speed, angle;
	public Sprite	sprite;

	public Mob(Point position) {
		this.position = position;
	}

	@Override
	public Point getPosition()
	{
		return position;
	}

	public float getVelocityResistance()
	{
		return 1f;
	}

	public void moveByVelocity()
	{
		float movement = speed * Core.delta;
		position.move(angle, speed);
		speed = JTil.normalize(speed, 5 * getVelocityResistance() * Core.delta);
	}

	public void push(float angle, float power)
	{
		speed = Math.max(speed, power);
		this.angle = angle;
	}

	@Override
	public void render()
	{
		sprite.draw(Core.batch);
	}

	@Override
	public boolean update()
	{
		if (getVelocityResistance() != 1)
			moveByVelocity();

		sprite.setPosition(position.x, position.y); // TODO Rather update lazier
		System.out.println(2);

		return true;
	}
}

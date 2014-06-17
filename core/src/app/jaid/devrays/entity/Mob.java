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
	}

	@Override
	public Point getPosition()
	{
		return position;
	}

	public float getVelocityResistance()
	{
		return 0.5f;
	}

	public void moveByVelocity()
	{
		// float movement = speed * Core.delta;
		position.move(angle, speed);
		speed = JTil.normalize(speed, 5 * getVelocityResistance() * Core.delta);
	}

	public void push(float angle, float power)
	{
		/*
		 * if (speed == 0 || JGeo.angleDifference(this.angle, angle) > 140) this.angle = angle; else { float angleInfluence = power / speed; // this.angle = this.angle * (1 - angleInfluence) + angle * angleInfluence; // TODO Getting average
		 * of two angles doesn't work yet since angles try to keep in bounds this.angle = JGeo.mergeAngles(this.angle, angle, 0.5f); }
		 */

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
		if (getVelocityResistance() != 1)
			moveByVelocity();

		sprite.setPosition(position.x, position.y); // TODO Rather update lazier (only when needed)

		return true;
	}
}

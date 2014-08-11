package app.jaid.devrays.entity;

import app.jaid.devrays.Core;
import app.jaid.devrays.debug.Log;
import app.jaid.devrays.geo.Angle;
import app.jaid.devrays.geo.Point;
import app.jaid.devrays.items.Weapon;
import app.jaid.devrays.physics.Colliding;
import app.jaid.jtil.JTil;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Main living {@link Entity}. Has a weapon to shot {@link Bullet} objects and can be hit by bullets.
 *
 * @author jaid
 */
public abstract class Mob implements Entity {

	protected static final float BRAKING_FACTOR = 15;
	protected Angle angle = new Angle();
	protected Point centerPosition = new Point(); // Cache
	protected int healthPoints, maxHealthPoints;
	private boolean isPushing;
	private float lifetime;
	protected Point position, wayPoint;
	protected int selectedWeapon;
	protected Team team;
	protected TextureRegion texture;
	protected float velocity;

	public Mob(Point position)
	{
		this.position = position;
	}

	public Mob(Point position, Team team)
	{
		this(position);
		this.team = team;
	}

	public void damage(int damage)
	{
		healthPoints -= damage;
	}

	public abstract void die();

	public abstract float getBraking();

	public Point getBulletSpawnLocation()
	{
		return getCenterPosition();
	}

	@Override
	public Point getCenterPosition()
	{
		return centerPosition;
	}

	@Override
	public float getHeight()
	{
		return texture.getRegionHeight() / 16;
	}

	@Override
	public abstract Colliding getHitbox();

	public int getHP()
	{
		return healthPoints;
	}

	@Override
	public float getLifetime()
	{
		return lifetime;
	}

	public int getMaxHP()
	{
		return maxHealthPoints;
	}

	public Angle getMovementAngle()
	{
		return angle;
	};

	@Override
	public abstract String getName();

	@Override
	public Point getPosition()
	{
		return position;
	};

	public abstract float getSpeed();

	public abstract Entity getTarget();

	@Override
	public Team getTeam()
	{
		return team != null ? team : Team.OTHER;
	}

	public float getVelocity()
	{
		return velocity;
	}

	public abstract Weapon getWeapon();

	@Override
	public float getWidth()
	{
		return texture.getRegionWidth() / 16;
	}

	public void heal(int amount)
	{
		healthPoints = Math.min(maxHealthPoints, healthPoints + amount);
	}

	@Override
	public boolean hit(Bullet bullet)
	{
		push(bullet.getAngle(), 150);
		return true;
	}

	private void moveByVelocity()
	{
		position.move(angle, velocity * Core.delta);

		if (!isPushing)
			if (getBraking() == 1)
				velocity = 0;
			else
				velocity = JTil.normalize(velocity, 5 * getBraking() * Core.delta);
	}

	public void push(Angle direction, float power)
	{

		if (power > velocity * 1.5f)
		{
			angle.set(direction);
			velocity = power;
		}
		else
		{
			float directionDifference = angle.getRadiansDifferenceNormalTo(direction);
			velocity -= power * directionDifference;
		}
	}

	public void push(Point target, float power)
	{
		push(centerPosition.angleTo(wayPoint), power);

		if (centerPosition.distanceTo(wayPoint) < power * Core.delta)
		{
			Log.debug("Mob " + getClass().getSimpleName() + " finished waypoint movement. (Distance " + centerPosition.distanceTo(wayPoint) + " < Speed " + power * Core.delta + ").");
			teleportCenter(wayPoint);
			wayPoint = null;
		}
	}

	@Override
	public void render()
	{
		Core.getBatch().draw(texture, position.x, position.y, getWidth(), getHeight());
	}

	@Override
	public abstract void renderShapes();

	@Override
	public abstract void renderText();

	public void teleport(Point newPosition)
	{
		position.set(newPosition);
	}

	public void teleportCenter(Point newPosition)
	{
		position.set(newPosition.subtract(getWidth() / 2, getHeight() / 2));
	}

	@Override
	public final boolean update()
	{
		if (getWeapon() != null)
			getWeapon().update();

		if (healthPoints <= 0)
		{
			die();
			return false;
		}

		isPushing = false;
		if (wayPoint != null)
			push(wayPoint, getSpeed());

		moveByVelocity();
		updateCenter();

		lifetime += Core.delta;
		return updatePersonal();
	}

	public void updateCenter()
	{
		centerPosition.set(position.x + getWidth() / 2, position.y + getHeight() / 2);
	}

	public abstract boolean updatePersonal();
}
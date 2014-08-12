package app.jaid.devrays.entity;

import app.jaid.devrays.Core;
import app.jaid.devrays.debug.Log;
import app.jaid.devrays.geo.Angle;
import app.jaid.devrays.geo.Point;
import app.jaid.devrays.items.Weapon;
import app.jaid.devrays.physics.Colliding;
import app.jaid.devrays.screen.ingame.Environment;

import com.badlogic.gdx.graphics.Color;

/**
 * Bullets that get shot by attacking {@link Mob} instances. May hit other mobs (depends on relation between its team
 * and the hit mob's team) or walls. The bullet's behaviour mostly depends on the {@link Weapon} it got shot from.
 *
 * @author jaid
 */
public class Bullet implements Entity {

	public static Bullet add(Weapon weapon)
	{
		Bullet bullet = new Bullet(weapon);
		Environment.get().getBullets().add(bullet);
		return bullet;
	}

	private Angle angle;
	private final Point centerPosition = new Point();
	private boolean isDead;
	private float lifetime;
	private Point position;
	protected float speed;
	private Weapon weapon;

	public Bullet(Weapon weapon)
	{
		angle = weapon.getShootAngle();
		position = new Point(weapon.getOwner().getBulletSpawnLocation());
		this.weapon = weapon;
		speed = weapon.getDescriptor().rollSpeed();
	}

	public Angle getAngle()
	{
		return angle;
	}

	@Override
	public Point getCenterPosition()
	{
		return centerPosition;
	}

	@Override
	public float getHeight()
	{
		return weapon.getDescriptor().getBulletHeight();
	}

	@Override
	public Colliding getHitbox()
	{
		return getCenterPosition();
	}

	@Override
	public float getLifetime()
	{
		return lifetime;
	}

	@Override
	public String getName()
	{
		return "Bullet from " + getOwner().getName() + " / " + weapon.getName();
	}

	public Mob getOwner()
	{
		return weapon.getOwner();
	}

	@Override
	public Point getPosition()
	{
		return position;
	}

	@Override
	public Team getTeam()
	{
		return getOwner().getTeam();
	}

	public Weapon getWeapon()
	{
		return weapon;
	}

	@Override
	public float getWidth()
	{
		return weapon.getDescriptor().getBulletWidth();
	}

	@Override
	public boolean hit(Bullet bullet)
	{
		isDead = true;
		return true;
	}

	@Override
	public void render()
	{
		Core.getBatch().setColor(weapon.getDescriptor().getBulletColor());
		Core.getBatch().draw(weapon.getSprite(), position.x, position.y, getWidth(), getHeight());
		Core.getBatch().setColor(Color.WHITE);
	}

	@Override
	public void renderShapes()
	{
	}

	@Override
	public void renderText()
	{
		// Drawer.drawTextOnWorld(getHitbox().toString(), getPosition());
	}

	@SuppressWarnings("unchecked")
	@Override
	public final boolean update()
	{
		updatePersonal();
		updateCenter();

		for (Entity collidingEntity : Environment.get().getCollisions(this, Environment.get().getMobs()))
			if (this != collidingEntity && getTeam().canAttack(collidingEntity.getTeam()))
			{
				Log.debug(getName() + " (Hitbox " + getHitbox() + ") collides with " + collidingEntity.getName() + " (Hitbox " + collidingEntity.getHitbox() + ").");
				isDead = collidingEntity.hit(this);
			}

		lifetime += Core.delta;
		return !isDead;
	}

	public void updateCenter()
	{
		centerPosition.set(position.x + getWidth() / 2, position.y + getHeight() / 2);
	}

	public boolean updatePersonal()
	{
		position.move(angle, speed * Core.delta);
		return true;
	}
}

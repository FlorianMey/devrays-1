package app.jaid.devrays.items;

import app.jaid.devrays.debug.Log;
import app.jaid.devrays.entity.Bullet;
import app.jaid.devrays.entity.Mob;
import app.jaid.devrays.etc.Scheduler;
import app.jaid.devrays.geo.Angle;
import app.jaid.devrays.mobs.Ship;
import app.jaid.jtil.JRand;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Describes a weapon based on a static based weapon and player related customizations. Available weapons for a
 * {@link Ship} are listed in its {@link Equipment}.
 *
 * @author jaid
 */
public abstract class Weapon {

	public static Weapon create(WeaponDescriptor descriptor, Mob owner)
	{
		try
		{
			Weapon weapon = (Weapon) descriptor.getType().getDeclaredConstructors()[0].newInstance(descriptor, owner);
			return weapon;

		} catch (Exception e)
		{
			Log.exception("Could not add weapon.", e);
			return null;
		}
	}

	public static float frequencyToShootsPerMinute(float shootFrequency)
	{
		return 60 / shootFrequency;
	}

	public static float shootsPerMinuteToFrequency(float shootsPerMinute)
	{
		return 60 / shootsPerMinute;
	}

	protected float charge;
	protected WeaponDescriptor descriptor;
	protected Mob owner;
	protected Scheduler scheduler;
	protected TextureRegion sprite;

	public Weapon(WeaponDescriptor descriptor, Mob owner)
	{
		this.descriptor = descriptor;
		this.owner = owner;
		sprite = descriptor.getBulletSprite();
		scheduler = new Scheduler(descriptor.getShootFrequency());
	}

	public WeaponDescriptor getDescriptor()
	{
		return descriptor;
	}

	public String getName()
	{
		return descriptor.getName() + " LV.1";
	}

	public Mob getOwner()
	{
		return owner;
	}

	public Angle getShootAngle()
	{
		return owner.getTeam().isGood() ? Angle.ANGLE_EAST : Angle.ANGLE_WEST;
	}

	public TextureRegion getSprite()
	{
		return sprite;
	}

	protected abstract void shoot(Bullet bullet);

	public boolean tryToShoot()
	{
		if (scheduler.request())
		{
			shoot(Bullet.add(this));

			if (descriptor.getShootsPerMinuteVariation() != 0)
				scheduler.changeFrequency(Weapon.shootsPerMinuteToFrequency(JRand.vary(descriptor.getShootsPerMinute(), descriptor.getShootsPerMinuteVariation())));

			return true;
		}

		return false;
	}

	public void update()
	{
		scheduler.update();
	}
}

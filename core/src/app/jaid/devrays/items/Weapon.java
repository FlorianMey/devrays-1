package app.jaid.devrays.items;

import app.jaid.devrays.entity.Bullet;
import app.jaid.devrays.entity.Mob;
import app.jaid.devrays.etc.Scheduler;
import app.jaid.devrays.mobs.Ship;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Describes a weapon based on a static based weapon and player related customizations. Available weapons for a
 * {@link Ship} are listed in its {@link Equipment}.
 *
 * @author jaid
 */
public class Weapon {

	public static float frequencyToShootsPerMinute(float shootFrequency)
	{
		return 60 / shootFrequency;
	}

	public static float shootsPerMinuteToFrequency(float shootsPerMinute)
	{
		return 1 / (shootsPerMinute / 60);
	}

	protected float charge;
	protected WeaponDescriptor descriptor;
	protected Mob owner;
	protected Scheduler scheduler;
	protected TextureRegion sprite;

	public Weapon(WeaponDescriptor descriptor)
	{
		this.descriptor = descriptor;
		sprite = descriptor.getBulletSprite();
		scheduler = new Scheduler(descriptor.getShootFrequency());
	}

	public boolean canShoot()
	{
		return charge >= getShootFrequency();
	}

	public Color getBulletColor()
	{
		return descriptor.getBulletColor();
	}

	public float getBulletHeight()
	{
		return descriptor.getBulletHeight();
	}

	public float getBulletSpeed()
	{
		return descriptor.getBulletSpeed();
	}

	public float getBulletSpeedVariation()
	{
		return descriptor.getBulletSpeedVariation();
	}

	public float getBulletWidth()
	{
		return descriptor.getBulletWidth();
	}

	public String getName()
	{
		return getSimpleName() + " LV.1";
	}

	public Mob getOwner()
	{
		return owner;
	}

	public float getShootFrequency()
	{
		return descriptor.getShootFrequency();
	}

	public float getShootsPerMinute()
	{
		return frequencyToShootsPerMinute(getShootFrequency());
	}

	public String getSimpleName()
	{
		return descriptor.getName();
	}

	public TextureRegion getSprite()
	{
		return sprite;
	}

	public void setOwner(Mob owner)
	{
		this.owner = owner;
	}

	public boolean tryToShoot()
	{
		if (scheduler.request())
		{
			Bullet.add(this);
			return true;
		}

		return false;
	}

	public void update()
	{
		scheduler.update();
	}
}

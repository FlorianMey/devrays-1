package app.jaid.devrays.items;

import app.jaid.devrays.mobs.Ship;

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

	private WeaponDescriptor descriptor;

	private TextureRegion sprite;

	public Weapon(WeaponDescriptor descriptor)
	{
		this.descriptor = descriptor;
		sprite = descriptor.getBulletSprite();
	}

	public float getBulletSpeed()
	{
		return descriptor.getBulletSpeed();
	}

	public String getName()
	{
		return getSimpleName() + " LV.1";
	}

	public float getShootFrequency()
	{
		return descriptor.getFrequency();
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
}

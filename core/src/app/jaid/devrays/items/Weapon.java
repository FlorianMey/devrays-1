package app.jaid.devrays.items;

import app.jaid.devrays.mobs.Ship;

/**
 * Describes a weapon based on a static based weapon and player related customizations. Available weapons for a
 * {@link Ship} are listed in its {@link Equipment}.
 *
 * @author jaid
 */
public class Weapon {

	public static final Weapon DEFAULT = new Weapon(12f, 0.2f);

	public static int frequencyToShootsPerMinute(float shootFrequency)
	{
		return Math.round(60 / shootFrequency);
	}

	float bulletSpeed;
	float shootFrequency;

	public Weapon(float bulletSpeed, float shootFrequency) {
		this.bulletSpeed = bulletSpeed;
		this.shootFrequency = shootFrequency;
	}

	public float getBulletSpeed()
	{
		return bulletSpeed;
	}

	public float getShootFrequency()
	{
		return shootFrequency;
	}

	public float getShootsPerMinute()
	{
		return frequencyToShootsPerMinute(shootFrequency);
	}

}

package app.jaid.devrays.items;

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

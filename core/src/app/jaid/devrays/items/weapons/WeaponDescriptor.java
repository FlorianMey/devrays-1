package app.jaid.devrays.items.weapons;

import app.jaid.devrays.debug.Log;
import app.jaid.devrays.io.Media;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Static profile that describes a weapon. Gets fetched from weapons.json in {@link Media#loadJsonArray}.
 *
 * @author jaid
 */
public class WeaponDescriptor {

	private static Array<WeaponDescriptor> weapons = Media.loadJsonArray(WeaponDescriptor.class, Gdx.files.internal("meta/weapons.json"));

	public static Array<WeaponDescriptor> getAll()
	{
		return weapons;
	}

	public static WeaponDescriptor getById(String id)
	{
		for (WeaponDescriptor descriptor : getAll())
			if (id.equals(descriptor.id))
				return descriptor;

		Log.warn("WeaponDescriptor with ID " + id + " not found.");
		return null;
	}

	private Color bulletColor;

	private String bulletColorHex;

	private float bulletSpeed, bulletSpeedVariation;
	private String bulletSprite;
	private float bulletWidth, bulletHeight;
	private float damage, damageVariation;
	private String id;
	private String name;
	private float shootsPerMinute, shootsPerMinuteVariation;
	private String type;

	public Color getBulletColor()
	{
		if (bulletColorHex == null)
			return Color.WHITE;

		if (bulletColor == null)
			bulletColor = Color.valueOf(bulletColorHex);

		return bulletColor;
	}

	public float getBulletHeight()
	{
		return bulletHeight;
	}

	public float getBulletSpeed()
	{
		return bulletSpeed;
	}

	public float getBulletSpeedVariation()
	{
		return bulletSpeedVariation;
	}

	public TextureRegion getBulletSprite()
	{
		return Media.getSprite(bulletSprite);
	}

	public float getBulletWidth()
	{
		return bulletWidth;
	}

	public float getDamage()
	{
		return damage;
	}

	public float getDamageVariation()
	{
		return damageVariation;
	}

	public String getName()
	{
		return name;
	}

	public float getShootFrequency()
	{
		return Weapon.shootsPerMinuteToFrequency(shootsPerMinute);
	}

	public float getShootFrequencyVariation()
	{
		return Weapon.shootsPerMinuteToFrequency(shootsPerMinuteVariation);
	}

	public float getShootsPerMinute()
	{
		return shootsPerMinute;
	}

	public float getShootsPerMinuteVariation()
	{
		return shootsPerMinuteVariation;
	}

	public Class<?> getType()
	{
		String className = type != null ? type : "DefaultWeapon";

		try
		{
			if (className.contains("."))
				return Class.forName(className);
			else
				return Class.forName("app.jaid.devrays.items.weapons." + className);
		} catch (ClassNotFoundException e)
		{
			return null;
		}
	}

}

package app.jaid.devrays.items;

import app.jaid.devrays.io.Media;

import com.badlogic.gdx.Gdx;
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

	private float bulletSpeed;
	private String bulletSprite;
	private float shootsPerMinute;

	public float getBulletSpeed()
	{
		return bulletSpeed;
	}

	public TextureRegion getBulletSprite()
	{
		return Media.getSprite(bulletSprite);
	}

	public float getFrequency()
	{
		return Weapon.shootsPerMinuteToFrequency(shootsPerMinute);
	}

	public float getShootsPerMinute()
	{
		return shootsPerMinute;
	}

}

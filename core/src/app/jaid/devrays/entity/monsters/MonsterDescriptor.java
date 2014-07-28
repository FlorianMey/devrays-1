package app.jaid.devrays.entity.monsters;

import app.jaid.devrays.debug.Log;
import app.jaid.devrays.entity.Team;
import app.jaid.devrays.io.Media;
import app.jaid.devrays.items.weapons.Weapon;
import app.jaid.devrays.items.weapons.WeaponDescriptor;
import app.jaid.jtil.JTil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class MonsterDescriptor {

	private static Array<MonsterDescriptor> monsters = Media.loadJsonArray(MonsterDescriptor.class, Gdx.files.internal("meta/monsters.json"));

	public static Array<MonsterDescriptor> getAll()
	{
		return monsters;
	}

	public static MonsterDescriptor getById(String id)
	{
		for (MonsterDescriptor descriptor : getAll())
			if (id.equals(descriptor.id))
				return descriptor;

		Log.warn("MonsterDescriptor with ID " + id + " not found.");
		return null;
	}

	private float braking;
	private float height;
	private int hp;
	private String id;
	private String name;
	private float speed;
	private String sprite;
	private String team;
	private String type;

	private String[] weapons;

	private float width;

	public float getBraking()
	{
		return braking;
	}

	public float getHeight()
	{
		return height;
	}

	public int getHp()
	{
		return hp;
	}

	private String getIdCapitalized()
	{
		return JTil.capitalizeStrictly(id);
	}

	public String getName()
	{
		return name != null ? name : getIdCapitalized();
	}

	public float getSpeed()
	{
		return speed;
	}

	public TextureRegion getSprite()
	{
		return Media.getSprite(sprite != null ? sprite : id);
	}

	public Team getTeam()
	{
		return team != null ? Team.valueOf(team) : Team.ENEMIES_MAIN;
	}

	public Class<?> getType()
	{
		String className = type != null ? type : getIdCapitalized();

		try
		{
			if (className.contains("."))
				return Class.forName(className);
			else
				return Class.forName("app.jaid.devrays.entity.monsters." + className);
		} catch (ClassNotFoundException e)
		{
			return null;
		}
	}

	public Weapon[] getWeaponInstances(Monster monster)
	{
		if (weapons == null || weapons.length == 0)
			return null;

		Weapon[] weaponInstances = new Weapon[weapons.length];

		for (int i = 0; i != weapons.length; i++)
			weaponInstances[i] = Weapon.create(WeaponDescriptor.getById(weapons[i]), monster);

		return weaponInstances;
	}

	public WeaponDescriptor[] getWeapons()
	{
		WeaponDescriptor[] weaponDescriptors = new WeaponDescriptor[weapons.length];

		for (int i = 0; i != weapons.length; i++)
			weaponDescriptors[i] = WeaponDescriptor.getById(weapons[i]);

		return weaponDescriptors;
	}

	public float getWidth()
	{
		return width;
	}

}

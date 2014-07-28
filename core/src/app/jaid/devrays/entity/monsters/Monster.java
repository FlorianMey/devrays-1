package app.jaid.devrays.entity.monsters;

import app.jaid.devrays.debug.Log;
import app.jaid.devrays.entity.*;
import app.jaid.devrays.geo.Point;
import app.jaid.devrays.graphics.Drawer;
import app.jaid.devrays.items.Weapon;
import app.jaid.devrays.physics.Colliding;
import app.jaid.devrays.screen.ingame.Environment;

public abstract class Monster extends Mob {

	public static Monster create(Point position, MonsterDescriptor descriptor)
	{
		return create(position, descriptor, descriptor.getTeam());
	}

	public static Monster create(Point position, MonsterDescriptor descriptor, Team team)
	{
		try
		{
			Monster monster = (Monster) descriptor.getType().getDeclaredConstructors()[0].newInstance(position, descriptor.getTeam());
			monster.team = team;
			monster.descriptor = descriptor;
			monster.texture = descriptor.getSprite();
			monster.healthPoints = descriptor.getHp();
			monster.speed = descriptor.getSpeed();
			monster.weapons = descriptor.getWeaponInstances(monster);
			return monster;
		} catch (Exception e)
		{
			Log.exception("Could not spawn monster of type <" + descriptor.getType() + "> at " + position + ".", e);
			return null;
		}
	}

	protected MonsterDescriptor descriptor;
	protected float speed;
	private Weapon[] weapons;

	protected Monster(Point position, Team team)
	{
		super(position, team);
	}

	public abstract void act();

	@Override
	public void die()
	{
	}

	@Override
	public float getBraking()
	{
		return descriptor.getBraking();
	}

	@Override
	public float getHeight()
	{
		if (descriptor.getHeight() != 0)
			return descriptor.getHeight();

		return super.getHeight();
	}

	@Override
	public Colliding getHitbox()
	{
		return null;
	}

	@Override
	public String getName()
	{
		return descriptor.getName() + " from Team " + getTeam();
	}

	@Override
	public float getSpeed()
	{
		return speed;
	}

	@Override
	public Entity getTarget()
	{
		return Environment.get().getPlayer();
	}

	@Override
	public Weapon getWeapon()
	{
		return weapons[selectedWeapon];
	}

	public Weapon[] getWeapons()
	{
		return weapons;
	}

	@Override
	public float getWidth()
	{
		if (descriptor.getWidth() != 0)
			return descriptor.getWidth();

		return super.getWidth();
	}

	@Override
	public void render()
	{
		Drawer.drawSprite(texture, position, getWidth(), getHeight());
	}

	@Override
	public abstract void renderShapes();

	@Override
	public abstract void renderText();

	@Override
	public boolean update()
	{
		act();
		return super.update();
	}

}

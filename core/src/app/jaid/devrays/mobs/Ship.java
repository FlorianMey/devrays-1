package app.jaid.devrays.mobs;

import app.jaid.devrays.entity.Mob;
import app.jaid.devrays.entity.Team;
import app.jaid.devrays.geo.Point;
import app.jaid.devrays.graphics.Drawer;
import app.jaid.devrays.items.Inventory;
import app.jaid.devrays.items.Weapon;
import app.jaid.devrays.physics.Colliding;

import com.badlogic.gdx.graphics.Color;

/**
 * Special kind of mobs whose instances are either controlled by a human (see {@link Player}) or are NPCs who have the
 * same conditions as players.
 *
 * @author jaid
 */
public class Ship extends Mob {

	private Inventory inventory = new Inventory();
	private int selectedWeapon = 0;
	protected float shootLoad;

	public Ship(Point position)
	{
		super(position);
	}

	public Ship(Point position, Team team)
	{
		super(position, team);
	}

	@Override
	public void die()
	{

	}

	@Override
	public float getBraking()
	{
		return 0.5f * BRAKING_FACTOR;
	}

	public Weapon getCurrentWeapon()
	{
		return inventory.equipment.weapons[selectedWeapon];
	}

	@Override
	public Colliding getHitbox()
	{
		return null;
	}

	@Override
	public String getName()
	{
		return "Ship from Team " + getTeam();
	}

	@Override
	public float getSpeed()
	{
		return 10f;
	}

	@Override
	public void renderShapes()
	{
		Drawer.drawRect(4, 4, 4, 4, Color.RED);
	}

	@Override
	public void renderText()
	{
		Drawer.drawTextOnWorld(getName(), position);
	}

}

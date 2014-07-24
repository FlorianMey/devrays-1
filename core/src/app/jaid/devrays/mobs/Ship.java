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

	protected Inventory inventory = new Inventory();
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
		return 0.3f * BRAKING_FACTOR;
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
	public Weapon getWeapon()
	{
		return inventory.equipment.weapons[selectedWeapon];
	}

	public void lastWeapon()
	{
		if (selectedWeapon == 0)
			selectedWeapon = inventory.equipment.weapons.length - 1;
		else
			selectedWeapon = selectedWeapon - 1;
	}

	public void nextWeapon()
	{
		if (selectedWeapon + 1 == inventory.equipment.weapons.length)
			selectedWeapon = 0;
		else
			selectedWeapon = selectedWeapon + 1;
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

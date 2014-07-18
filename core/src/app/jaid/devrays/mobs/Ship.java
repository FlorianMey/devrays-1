package app.jaid.devrays.mobs;

import app.jaid.devrays.entity.Mob;
import app.jaid.devrays.entity.Team;
import app.jaid.devrays.geo.Point;
import app.jaid.devrays.items.Inventory;
import app.jaid.devrays.items.Weapon;

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

	public Ship(Point position) {
		super(position);
	}

	public Ship(Point position, Team team) {
		super(position, team);
	}

	public Weapon getCurrentWeapon()
	{
		return inventory.equipment.weapons[selectedWeapon];
	}

}

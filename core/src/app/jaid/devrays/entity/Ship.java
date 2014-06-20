package app.jaid.devrays.entity;

import app.jaid.devrays.items.Inventory;
import app.jaid.devrays.items.Weapon;
import app.jaid.devrays.math.Point;

public class Ship extends Mob {

	private Inventory	inventory	= new Inventory();

	public Ship(Point position) {
		super(position);
	}

	public Weapon getCurrentWeapon()
	{
		return inventory.equipment.weapons[0];
	}

}

package app.jaid.devrays.items;

/**
 * Equipment instances describe available weapons of a ship and its upgrade slots. Ship stats are based on Equipment.
 *
 * @author jaid
 */
public class Equipment {

	public Weapon[] arsenal;

	public Equipment()
	{
		arsenal = new Weapon[] { new Weapon(WeaponDescriptor.getAll().first()), new Weapon(WeaponDescriptor.getAll().get(1)), new Weapon(WeaponDescriptor.getAll().get(2)) };
	}
}

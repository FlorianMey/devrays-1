package app.jaid.devrays.items;

/**
 * Equipment instances describe available weapons of a ship and its upgrade slots. Ship stats are based on Equipment.
 * 
 * @author jaid
 */
public class Equipment {

	public Weapon[] weapons;

	public Equipment() {
		weapons = new Weapon[] { Weapon.DEFAULT };
	}
}

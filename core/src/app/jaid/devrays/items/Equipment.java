package app.jaid.devrays.items;

import com.badlogic.gdx.utils.Array;

/**
 * Equipment instances describe available weapons of a ship and its upgrade slots. Ship stats are based on Equipment.
 *
 * @author jaid
 */
public class Equipment {

	public Array<Weapon> arsenal = new Array<Weapon>(4);

	public Equipment()
	{
	}

	public Array<Weapon> getArsenal()
	{
		return arsenal;
	}
}

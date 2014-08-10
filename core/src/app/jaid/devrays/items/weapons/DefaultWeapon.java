package app.jaid.devrays.items.weapons;

import app.jaid.devrays.entity.*;
import app.jaid.devrays.items.Weapon;
import app.jaid.devrays.items.WeaponDescriptor;

public class DefaultWeapon extends Weapon {

	public DefaultWeapon(WeaponDescriptor descriptor, Mob owner)
	{
		super(descriptor, owner);
	}

	@Override
	protected void shoot(Bullet bullet)
	{
	}

}

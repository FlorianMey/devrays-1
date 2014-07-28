package app.jaid.devrays.items.weapons;

import app.jaid.devrays.entity.Bullet;
import app.jaid.devrays.entity.Mob;

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

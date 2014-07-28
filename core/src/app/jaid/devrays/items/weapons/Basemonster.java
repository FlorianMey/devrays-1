package app.jaid.devrays.items.weapons;

import app.jaid.devrays.entity.Bullet;
import app.jaid.devrays.entity.Mob;
import app.jaid.devrays.geo.Angle;

public class Basemonster extends Weapon {

	public Basemonster(WeaponDescriptor descriptor, Mob owner)
	{
		super(descriptor, owner);
	}

	@Override
	public Angle getShootAngle()
	{
		return owner.getPosition().angleTo(owner.getTarget().getCenterPosition());
	}

	@Override
	protected void shoot(Bullet bullet)
	{
	}
}

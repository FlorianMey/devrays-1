package app.jaid.devrays.mobs;

import app.jaid.devrays.debug.Log;
import app.jaid.devrays.entity.*;
import app.jaid.devrays.geo.Angle;
import app.jaid.devrays.geo.Point;
import app.jaid.devrays.graphics.Gfx;
import app.jaid.devrays.input.Movement;
import app.jaid.devrays.io.Media;
import app.jaid.devrays.items.Weapon;
import app.jaid.devrays.items.WeaponDescriptor;
import app.jaid.devrays.screen.ingame.Environment;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * {@link Ship} that is assigned to a playing human. An already existing instance can be retrieved from
 * {@link Environment#getPlayer}. Movement controlling is provided by {@link Movement}.
 *
 * @author jaid
 */
public class Player extends Ship {

	public Player(Point position)
	{
		super(position, Team.PLAYERS);
		texture = new Sprite(Media.getSprite("ship"));
		healthPoints = 100;
		inventory.equipment.arsenal.add(Weapon.create(WeaponDescriptor.getAll().first(), this));
	}

	@Override
	public Entity getTarget()
	{
		return null;
	}

	@Override
	public boolean hit(Bullet bullet, Angle hitAngle)
	{
		Gfx.setHudStrength(1);
		Gfx.setHudAngle(hitAngle.getRadians());

		Log.debug(hitAngle);

		return super.hit(bullet, hitAngle);
	}

	@Override
	public boolean updatePersonal()
	{
		if (Movement.shooting)
			getWeapon().tryToShoot();

		Movement.push(this, getSpeed());

		return true;
	}
}

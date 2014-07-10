package app.jaid.devrays.entity;

import app.jaid.devrays.Core;
import app.jaid.devrays.io.Media;
import app.jaid.devrays.items.Weapon;
import app.jaid.devrays.math.Point;
import app.jaid.devrays.screen.ingame.Environment;

public class Bullet implements Entity {

	public static void add(Mob mob, Weapon weapon)
	{
		Environment.addBullet(new Bullet(mob, weapon));
	}

	private Mob		fromMob;
	private Point	position;
	private Weapon	usedWeapon;

	public Bullet(Mob mob, Weapon weapon) {
		position = new Point(mob.getPosition());
		fromMob = mob;
		usedWeapon = weapon;
	}

	@Override
	public Point getPosition()
	{
		return position;
	}

	@Override
	public Team getTeam()
	{
		return fromMob.getTeam();
	}

	@Override
	public void render()
	{
		Core.getBatch().draw(Media.getSprite("bullet"), position.x, position.y, 1, 1);
	}

	@Override
	public boolean update()
	{
		position.x += usedWeapon.getBulletSpeed() * Core.delta;
		return true;
	}
}

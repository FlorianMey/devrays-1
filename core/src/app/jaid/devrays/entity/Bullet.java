package app.jaid.devrays.entity;

import app.jaid.devrays.Core;
import app.jaid.devrays.geo.Point;
import app.jaid.devrays.items.Weapon;
import app.jaid.devrays.physics.Colliding;
import app.jaid.devrays.screen.ingame.Environment;
import app.jaid.jtil.JRand;

import com.badlogic.gdx.graphics.Color;

/**
 * Bullets that get shot by attacking {@link Mob} instances. May hit other mobs (depends on relation between its team
 * and the hit mob's team) or walls. The bullet's behaviour mostly depends on the {@link Weapon} it got shot from.
 *
 * @author jaid
 */
public class Bullet implements Entity {

	public static void add(Weapon weapon)
	{
		Bullet bullet = new Bullet(weapon);
		Environment.get().getBullets().add(bullet);
	}

	private Point position;
	protected float speed;
	private Weapon weapon;

	public Bullet(Weapon weapon)
	{
		position = new Point(weapon.getOwner().getBulletSpawnLocation());
		this.weapon = weapon;
		speed = JRand.vary(weapon.getBulletSpeed(), weapon.getBulletSpeedVariation());
	}

	@Override
	public Point getCenterPosition()
	{
		return null;
	}

	@Override
	public float getHeight()
	{
		return weapon.getBulletHeight();
	}

	@Override
	public Colliding getHitbox()
	{
		return getPosition();
	}

	@Override
	public String getName()
	{
		return null;
	}

	@Override
	public Point getPosition()
	{
		return position;
	}

	@Override
	public Team getTeam()
	{
		return weapon.getOwner().getTeam();
	}

	@Override
	public float getWidth()
	{
		return weapon.getBulletWidth();
	}

	@Override
	public void render()
	{
		Core.getBatch().setColor(weapon.getBulletColor());
		Core.getBatch().draw(weapon.getSprite(), position.x, position.y, getWidth(), getHeight());
		Core.getBatch().setColor(Color.WHITE);
	}

	@Override
	public void renderShapes()
	{
	}

	@Override
	public void renderText()
	{
	}

	@Override
	public boolean update()
	{
		position.x += speed * Core.delta;
		return true;
	}
}

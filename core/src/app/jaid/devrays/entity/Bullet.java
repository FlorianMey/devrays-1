package app.jaid.devrays.entity;

import app.jaid.devrays.Core;
import app.jaid.devrays.math.Point;
import app.jaid.devrays.screen.ingame.Environment;

import com.badlogic.gdx.graphics.Texture;

public class Bullet implements Entity {

	public static final Texture	texture	= Core.getSprite("bullet");

	public static void add(Mob mob)
	{
		Environment.addBullet(new Bullet(mob));
	}

	Point	position;

	public Bullet(Mob mob) {
		position = new Point(mob.getPosition());
	}

	@Override
	public Point getPosition()
	{
		return null;
	}

	@Override
	public Team getTeam()
	{
		return null;
	}

	@Override
	public void render()
	{
		Core.getBatch().draw(texture, position.x, position.y, 1, 1);
	}

	@Override
	public boolean update()
	{
		position.x += Core.delta * 10;
		return true;
	}

}

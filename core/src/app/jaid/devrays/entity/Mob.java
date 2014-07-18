package app.jaid.devrays.entity;

import app.jaid.devrays.Core;
import app.jaid.devrays.geo.Angle;
import app.jaid.devrays.geo.Point;
import app.jaid.devrays.graphics.Drawer;
import app.jaid.devrays.physics.Colliding;
import app.jaid.jtil.JTil;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Main living {@link Entity}. Has a weapon to shot {@link Bullet} objects and can be hit by bullets.
 *
 * @author jaid
 */
public class Mob implements Entity {

	private Angle angle = new Angle();
	private Point position;
	private float speed;
	private Team team;
	public TextureRegion texture;

	public Mob(Point position) {
		this.position = position;
	}

	public Mob(Point position, Team team) {
		this(position);
		this.team = team;
	}

	public void damage(int damage)
	{

	}

	public float getBraking()
	{
		return 0.5f * 15;
	}

	@Override
	public Point getCenter()
	{
		return position.add(getWidth() / 2, getHeight() / 2);
	}

	@Override
	public float getHeight()
	{
		return texture.getRegionHeight() / 16;
	}

	@Override
	public Colliding getHitbox()
	{
		return getPosition();
	}

	public int getHP()
	{
		return 0;
	}

	public int getMaxHP()
	{
		return 0;
	}

	@Override
	public String getName()
	{
		return "Mob from Team " + getTeam();
	}

	@Override
	public Point getPosition()
	{
		return position;
	}

	public float getSpeed()
	{
		return 0.5f * 20;
	}

	public float getSteering()
	{
		return 1f;
	}

	@Override
	public Team getTeam()
	{
		return team != null ? team : Team.OTHER;
	}

	@Override
	public float getWidth()
	{
		return texture.getRegionWidth() / 16;
	}

	public void heal(int amount)
	{

	}

	private void moveByVelocity()
	{
		position.move(angle, speed * Core.delta);
		speed = JTil.normalize(speed, 5 * getBraking() * Core.delta);
	}

	public void push(Angle direction, float power)
	{
		if (speed == 0 || getSteering() == 1)
			angle.setTo(direction);
		else
			angle = angle.moveTo(direction, angle.getShortestRotateDirection(direction) * getSteering() * power * Core.delta * 5);

		speed = Math.max(speed, power);
	}

	@Override
	public void render()
	{
		Core.getBatch().draw(texture, position.x, position.y, getWidth(), getHeight());
	}

	@Override
	public void renderShapes()
	{
		Drawer.drawRect(4, 4, 4, 4, Color.RED);
	}

	@Override
	public void renderText()
	{
		Drawer.drawTextOnWorld(getName(), position);
	}

	public void teleport(Point newPosition)
	{
		position.set(newPosition);
	}

	public void teleportCenter(Point newPosition)
	{
		position.set(newPosition.subtract(getWidth() / 2, getHeight() / 2));
	}

	@Override
	public boolean update()
	{
		if (getBraking() != 1)
			moveByVelocity();

		return true;
	}
}
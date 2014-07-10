package app.jaid.devrays.entity;

import app.jaid.devrays.Core;
import app.jaid.devrays.math.Angle;
import app.jaid.devrays.math.Point;
import app.jaid.jtil.JTil;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Mob implements Entity {

	private Angle			angle	= Angle.create();
	private Point			position;
	private float			speed;
	public TextureRegion	texture;
	private Team			team;

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

	public int getHP()
	{
		return 0;
	}

	public int getMaxHP()
	{
		return 0;
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
		Core.getBatch().draw(texture, position.x, position.y, texture.getRegionWidth() / 16, texture.getRegionHeight() / 16);
	}

	@Override
	public boolean update()
	{
		if (getBraking() != 1)
			moveByVelocity();

		return true;
	}
}
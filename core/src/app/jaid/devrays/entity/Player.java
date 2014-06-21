package app.jaid.devrays.entity;

import app.jaid.devrays.Core;
import app.jaid.devrays.input.Movement;
import app.jaid.devrays.math.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Player extends Ship {

	public Player(Point position) {
		super(position, Team.PLAYERS);
	}

	public boolean isShooting()
	{
		return Gdx.input.isKeyPressed(Keys.SPACE);
	}

	@Override
	public boolean update()
	{
		shootLoad += Core.delta;

		if (isShooting() && shootLoad >= getCurrentWeapon().getShootFrequency())
		{
			Bullet.add(this, getCurrentWeapon());
			shootLoad = 0;
		}

		Movement.push(this, getSpeed());
		super.update();
		return true;
	}
}

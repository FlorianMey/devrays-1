package app.jaid.devrays.mobs;

import app.jaid.devrays.Core;
import app.jaid.devrays.entity.Bullet;
import app.jaid.devrays.entity.Team;
import app.jaid.devrays.geo.Point;
import app.jaid.devrays.input.Movement;
import app.jaid.devrays.io.Media;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player extends Ship {

	public Player(Point position) {
		super(position, Team.PLAYERS);
		texture = new Sprite(Media.getSprite("ship"));
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

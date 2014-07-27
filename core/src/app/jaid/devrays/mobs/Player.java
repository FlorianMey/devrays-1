package app.jaid.devrays.mobs;

import app.jaid.devrays.entity.Entity;
import app.jaid.devrays.entity.Team;
import app.jaid.devrays.geo.Point;
import app.jaid.devrays.input.Movement;
import app.jaid.devrays.io.Media;
import app.jaid.devrays.screen.ingame.Environment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
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
	}

	@Override
	public Entity getTarget()
	{
		return null;
	}

	public boolean isShooting()
	{
		return Gdx.input.isKeyPressed(Keys.SPACE);
	}

	@Override
	public boolean update()
	{
		if (isShooting())
			getWeapon().tryToShoot();

		Movement.push(this, getSpeed());
		super.update();

		return true;
	}
}

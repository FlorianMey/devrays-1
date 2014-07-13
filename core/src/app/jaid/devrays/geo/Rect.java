package app.jaid.devrays.geo;

import app.jaid.devrays.physics.Colliding;

import com.badlogic.gdx.math.Rectangle;

public class Rect extends Rectangle implements Colliding {

	private static final long	serialVersionUID	= 8473795929210348387L;

	@Override
	public boolean collidesWith(Colliding other)
	{
		return false;
	}
}

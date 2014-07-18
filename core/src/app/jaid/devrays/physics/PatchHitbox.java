package app.jaid.devrays.physics;

import app.jaid.devrays.geo.Rect;

/**
 * Hitbox that contains multiple {@link Rect} hitboxes. If the other {@link Colliding} overlaps with any of those
 * hitboxes, {@link #collidesWith} returns true.
 *
 * @author jaid
 */
public class PatchHitbox implements Colliding {

	@Override
	public boolean collidesWith(Colliding other)
	{
		return false;
	}

}

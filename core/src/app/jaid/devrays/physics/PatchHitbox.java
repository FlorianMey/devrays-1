package app.jaid.devrays.physics;

import app.jaid.devrays.geo.Rect;

import com.badlogic.gdx.graphics.Color;

/**
 * Hitbox that contains multiple {@link Rect} hitboxes. If the other {@link Colliding} overlaps with any of those
 * hitboxes, {@link #collidesWith} returns true.
 *
 * @author jaid
 */
public class PatchHitbox implements Colliding {

	@Override
	public boolean intersects(Colliding other)
	{
		return false;
	}

	@Override
	public void renderWorldBounds(Color color)
	{
	}

}

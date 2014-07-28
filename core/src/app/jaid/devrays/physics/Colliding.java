package app.jaid.devrays.physics;

import com.badlogic.gdx.graphics.Color;

/**
 * Interface for objects that can indicate if they overlap with other objects that implement Colliding.
 *
 * @author jaid
 */
public interface Colliding {

	public abstract boolean collidesWith(Colliding other);

	public abstract void renderWorldBounds(Color color);
}

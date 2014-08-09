package app.jaid.devrays.entity;

import app.jaid.devrays.geo.Point;
import app.jaid.devrays.physics.Colliding;

/**
 * Interface for living and non living entities, gets implemented by all interacting objects in the world.
 *
 * @author jaid
 */
public interface Entity {

	public abstract Point getCenterPosition();

	public abstract float getHeight();

	public abstract Colliding getHitbox();

	public abstract float getLifetime();

	public abstract String getName();

	public abstract Point getPosition();

	public abstract Team getTeam();

	public abstract float getWidth();

	public abstract void render();

	public abstract void renderShapes();

	public abstract void renderText();

	public abstract boolean update();
}

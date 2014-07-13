package app.jaid.devrays.entity;

import app.jaid.devrays.geo.Point;
import app.jaid.devrays.physics.Colliding;

public interface Entity {

	public abstract Point getCenter();

	public abstract float getHeight();

	public abstract Colliding getHitbox();

	public abstract String getName();

	public abstract Point getPosition();

	public abstract Team getTeam();

	public abstract float getWidth();

	public abstract void render();

	public abstract void renderShapes();

	public abstract void renderText();

	public abstract boolean update();

}

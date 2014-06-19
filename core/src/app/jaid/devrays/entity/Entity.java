package app.jaid.devrays.entity;

import app.jaid.devrays.math.Point;

public interface Entity {

	public abstract float getBraking();

	public abstract Point getPosition();

	public abstract float getSpeed();

	public abstract float getSteering();

	public abstract void render();

	public abstract boolean update();

}

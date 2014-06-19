package app.jaid.devrays.entity;

import app.jaid.devrays.math.Point;

public interface Entity {

	public abstract Point getPosition();

	public abstract Team getTeam();

	public abstract void render();

	public abstract boolean update();

}

package app.jaid.devrays.entity;

import app.jaid.devrays.input.Movement;
import app.jaid.devrays.math.Point;

public class Player extends Mob {

	public Player(Point position) {
		super(position);
	}

	@Override
	public boolean update()
	{
		Movement.push(this, 1);
		super.update();
		return true;
	}
}

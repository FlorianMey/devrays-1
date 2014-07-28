package app.jaid.devrays.entity.monsters;

import app.jaid.devrays.entity.Team;
import app.jaid.devrays.geo.Point;
import app.jaid.devrays.graphics.Drawer;

public class Varock extends Monster {

	protected Varock(Point position, Team team)
	{
		super(position, team);
		wayPoint = getTarget().getCenterPosition();
	}

	@Override
	public void act()
	{
		getWeapon().tryToShoot();
	}

	@Override
	public void renderShapes()
	{
	}

	@Override
	public void renderText()
	{
		Drawer.drawTextOnWorld(getName(), getPosition());
	}

}

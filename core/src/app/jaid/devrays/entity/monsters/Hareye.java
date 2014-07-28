package app.jaid.devrays.entity.monsters;

import app.jaid.devrays.entity.Team;
import app.jaid.devrays.geo.Point;
import app.jaid.devrays.graphics.Drawer;
import app.jaid.devrays.items.weapons.Weapon;
import app.jaid.jtil.JRand;

public class Hareye extends Monster {

	private float placeX, placeY;

	protected Hareye(Point position, Team team)
	{
		super(position, team);
		placeX = JRand.random(0, 9f);
		placeY = JRand.random(0, 9f);
		wayPoint = getTarget().getPosition().add(placeX, placeY);
	}

	@Override
	public void act()
	{
	}

	@Override
	public Weapon getWeapon()
	{
		return null;
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

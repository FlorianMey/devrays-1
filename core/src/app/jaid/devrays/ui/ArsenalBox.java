package app.jaid.devrays.ui;

import app.jaid.devrays.mobs.Ship;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class ArsenalBox extends Table {

	private final Ship ship;
	private Label weaponName = new Label("", Hud.oldSkin);

	public ArsenalBox(Ship ship)
	{
		super(Hud.oldSkin);
		this.ship = ship;

		add("Weapon:").spaceRight(20);
		add(weaponName);
		weaponName.setColor(Color.YELLOW);
	}

	@Override
	public void act(float delta)
	{
		super.act(delta);
		weaponName.setText(ship.getWeapon().getName());
	}

}

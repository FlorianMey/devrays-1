package app.jaid.devrays.ui;

import app.jaid.devrays.items.Weapon;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;

public class ArsenalBox extends Table {

	private int selectedWeapon;
	private Label weaponName = new Label("", Hud.oldSkin);
	private Array<Weapon> weapons;

	public ArsenalBox(Array<Weapon> weapons, int selectedWeapon)
	{
		super(Hud.oldSkin);
		this.weapons = weapons;

		add("Weapon:").spaceRight(20);
		add(weaponName);
		weaponName.setColor(Color.YELLOW);

		switchWeapon(selectedWeapon);
	}

	@Override
	public void act(float delta)
	{
		super.act(delta);
	}

	public void switchWeapon(int selectedWeapon)
	{
		this.selectedWeapon = selectedWeapon;
		weaponName.setText(weapons.get(selectedWeapon).getName());
	}

}

package app.jaid.devrays.mobs;

import app.jaid.devrays.entity.Mob;
import app.jaid.devrays.entity.Team;
import app.jaid.devrays.geo.Point;
import app.jaid.devrays.geo.Rect;
import app.jaid.devrays.items.Inventory;
import app.jaid.devrays.items.Weapon;
import app.jaid.devrays.physics.Colliding;
import app.jaid.devrays.ui.ArsenalBox;
import app.jaid.devrays.ui.Hud;

import com.badlogic.gdx.utils.Array;

/**
 * Special kind of mobs whose instances are either controlled by a human (see {@link Player}) or are NPCs who have the
 * same conditions as players.
 *
 * @author jaid
 */
public abstract class Ship extends Mob {

	protected Inventory inventory = new Inventory();

	public Ship(Point position, Team team)
	{
		super(position, team);
	}

	@Override
	public void die()
	{

	}

	public Array<Weapon> getArsenal()
	{
		return inventory.equipment.arsenal;
	}

	@Override
	public float getBraking()
	{
		return 0.3f * BRAKING_FACTOR;
	}

	@Override
	public Colliding getHitbox()
	{
		return new Rect(position, getWidth(), getHeight());
	}

	@Override
	public String getName()
	{
		return "Ship from Team " + getTeam();
	}

	@Override
	public float getSpeed()
	{
		return 10f;
	}

	@Override
	public Weapon getWeapon()
	{
		return inventory.equipment.arsenal.get(selectedWeapon);
	}

	public void lastWeapon()
	{
		switchWeapon((selectedWeapon == 0 ? getArsenal().size : selectedWeapon) - 1);
	}

	public void nextWeapon()
	{
		switchWeapon((selectedWeapon + 1) % getArsenal().size);
	}

	@Override
	public void renderShapes()
	{
	}

	@Override
	public void renderText()
	{
		// Drawer.drawTextOnWorld(getName(), position);
	}

	public void switchWeapon(int selectedWeapon)
	{
		if (selectedWeapon < 0 || selectedWeapon >= getArsenal().size)
			throw new IllegalArgumentException(getName() + " can't switch its selected weapon from " + this.selectedWeapon + " to unknown index " + selectedWeapon + ".");

		this.selectedWeapon = selectedWeapon;
		((ArsenalBox) Hud.get().getArsenalBoxCell().getActor()).switchWeapon(selectedWeapon);
	}

}

package app.jaid.devrays.ui;

import app.jaid.devrays.input.InputManager;
import app.jaid.devrays.input.Movement;
import app.jaid.devrays.screen.ingame.IngameScreen;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

/**
 * Input listener layer of {@link InputManager} that is related to shortcuts for ingame HUD.
 *
 * @author jaid
 */
public class IngameInput implements InputProcessor {

	@Override
	public boolean keyDown(int keycode)
	{
		if (keycode == Keys.W)
		{
			Movement.up = true;
			return true;
		}

		if (keycode == Keys.S)
		{
			Movement.down = true;
			return true;
		}

		if (keycode == Keys.A)
		{
			Movement.left = true;
			return true;
		}

		if (keycode == Keys.D)
		{
			Movement.right = true;
			return true;
		}

		return false;
	}

	@Override
	public boolean keyTyped(char character)
	{
		return false;
	}

	@Override
	public boolean keyUp(int keycode)
	{

		if (keycode == Keys.W)
		{
			Movement.up = false;
			return true;
		}

		if (keycode == Keys.S)
		{
			Movement.down = false;
			return true;
		}

		if (keycode == Keys.A)
		{
			Movement.left = false;
			return true;
		}

		if (keycode == Keys.D)
		{
			Movement.right = false;
			return true;
		}

		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
		return false;
	}

	@Override
	public boolean scrolled(int amount)
	{
		if (amount > 0)
		{
			IngameScreen.getEnvironment().getPlayer().nextWeapon();
			return true;
		}
		else
		{
			IngameScreen.getEnvironment().getPlayer().lastWeapon();
			return true;
		}

		// return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		return false;
	}

}

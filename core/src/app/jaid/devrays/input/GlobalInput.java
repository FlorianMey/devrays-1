package app.jaid.devrays.input;

import app.jaid.devrays.Core;
import app.jaid.devrays.debug.CommandExecutor;
import app.jaid.devrays.ui.Hud;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

/**
 * Input listeners with highest priority that are available everywhere and not only in certain screens.
 *
 * @author jaid
 */
public class GlobalInput implements InputProcessor {

	@Override
	public boolean keyDown(int keycode)
	{
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
		switch (keycode)
		{
			case Keys.ENTER:
				if (!(Core.getHudStage().getKeyboardFocus() == Hud.getConsole().getTextField()))
				{
					Movement.reset();
					Hud.getConsole().focus(InputCore.isCtrlPressed() ? "/" : "");
					return true;
				}
				return false;

			case Keys.F11:
				CommandExecutor.run("fullscreen");
				return true;

			case Keys.F10:
				CommandExecutor.run("dmgeffect " + (InputCore.isCtrlPressed() ? InputCore.isShiftPressed() ? "1.5" : "1" : "0.5"));
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
		return false;
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

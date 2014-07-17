package app.jaid.devrays.input;

import app.jaid.devrays.debug.CommandExecutor;
import app.jaid.devrays.ui.Hud;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

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
		if (keycode == Keys.ENTER && !Hud.getConsole().isVisible())
		{
			Hud.getConsole().focus(InputCore.isCtrlPressed() ? "/" : "");
			return true;
		}

		if (keycode == Keys.F11)
			CommandExecutor.run("fullscreen");

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

package app.jaid.devrays.debug;

import app.jaid.devrays.ui.Hud;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Stat object that has a stat reference that gets polled every frame and rendered in a screen corner (FPS for
 * example).
 *
 * @author jaid
 */
public class Stat extends Label {

	private Object value;

	Stat(Object value)
	{
		super(String.valueOf(value), Hud.oldSkin);
		this.value = value;
	}

	private String getOutput()
	{
		if (value instanceof CoreStat)
			return ((CoreStat) value).getValue();

		return String.valueOf(value);
	}

	public void update()
	{
		setText(getOutput());
	}

}

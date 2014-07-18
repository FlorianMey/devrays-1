package app.jaid.devrays.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;

/**
 * Util methods for display- and resolution calculations.
 *
 * @author jaid
 */
public class DisplayUtils {

	public static DisplayMode getBiggestDisplayMode()
	{
		int max = 0;
		DisplayMode currentlyBiggest = null;

		for (DisplayMode displayMode : Gdx.graphics.getDisplayModes())
			if (displayMode.width * displayMode.height > max)
			{
				max = displayMode.width * displayMode.height;
				currentlyBiggest = displayMode;
			}

		return currentlyBiggest;
	}

}

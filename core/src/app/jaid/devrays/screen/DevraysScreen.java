package app.jaid.devrays.screen;

import com.badlogic.gdx.Screen;

/**
 * Provides essential functions for splitting the main loop into more context related methods. All actual screens
 * implement this one instead of GDX Screen.
 *
 * @author jaid
 */
public interface DevraysScreen extends Screen {

	public abstract void renderShapes();

	public abstract void renderText();

	public abstract void update();

}

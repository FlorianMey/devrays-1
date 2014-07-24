package app.jaid.devrays.input;

import app.jaid.devrays.Core;
import app.jaid.devrays.ui.IngameInput;

import com.badlogic.gdx.InputMultiplexer;

/**
 * Holds a set of InputProcessors and defines input priorities.
 *
 * @author jaid
 */
public class InputManager extends InputMultiplexer {

	public InputManager()
	{
		addProcessor(new GlobalInput());
		addProcessor(Core.getHudStage());
		addProcessor(new IngameInput());
	}

}

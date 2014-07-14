package app.jaid.devrays.input;

import app.jaid.devrays.Core;
import app.jaid.devrays.ui.HudInput;

import com.badlogic.gdx.InputMultiplexer;

public class InputManager extends InputMultiplexer {

	public InputManager() {
		addProcessor(new GlobalInput());
		addProcessor(new HudInput());
		addProcessor(Core.getHudStage());
	}

}

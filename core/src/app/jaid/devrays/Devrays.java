package app.jaid.devrays;

import app.jaid.devrays.screen.DevraysScreen;
import app.jaid.devrays.screen.ingame.IngameScreen;

import com.badlogic.gdx.Game;

public class Devrays extends Game {

	@Override
	public void create()
	{
		Core.init();
		setScreen(new IngameScreen());
	}

	@Override
	public void render()
	{
		Core.tick();
		((DevraysScreen) getScreen()).update();

		Core.getBatch().begin();
		super.render();
		Core.getBatch().end();
	}

	@Override
	public void resize(int width, int height)
	{
		Core.resize(width, height);
		super.resize(width, height);
	}
}

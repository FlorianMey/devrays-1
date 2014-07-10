package app.jaid.devrays;

import app.jaid.devrays.screen.DevraysScreen;
import app.jaid.devrays.screen.ingame.IngameScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class DevraysGame extends Game {

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

		DevraysScreen currentDevraysScreen = (DevraysScreen) getScreen();
		currentDevraysScreen.update();

		Core.getBatch().begin();
		Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		super.render();
		Core.getBatch().end();

		Core.getUiBatch().begin();
		currentDevraysScreen.renderText();
		Core.getUiBatch().end();
	}

	@Override
	public void resize(int width, int height)
	{
		Core.resize(width, height);
		super.resize(width, height);
	}
}

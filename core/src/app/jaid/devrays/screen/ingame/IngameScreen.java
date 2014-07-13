package app.jaid.devrays.screen.ingame;

import app.jaid.devrays.geo.Point;
import app.jaid.devrays.mobs.Player;
import app.jaid.devrays.screen.DevraysScreen;
import app.jaid.devrays.screen.WorldCamera;

public class IngameScreen implements DevraysScreen {

	private static IngameScreen	instance;

	public static Environment getEnvironment()
	{
		return instance.environment;
	}

	public static IngameScreen getInstance()
	{
		return instance;
	}

	WorldCamera			camera;
	private Environment	environment;

	@Override
	public void dispose()
	{
	}

	@Override
	public void hide()
	{
		instance = null;
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void render(float delta)
	{
		environment.render();
	}

	@Override
	public void renderShapes()
	{
		environment.renderShapes();
	}

	@Override
	public void renderText()
	{
		environment.renderText();
	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void resume()
	{
	}

	@Override
	public void show()
	{
		instance = this;
		environment = new Environment();
		camera = new WorldCamera();

		environment.player = new Player(new Point());
		environment.mobs.add(environment.player);
	}

	@Override
	public void update()
	{
		environment.update();
	}
}
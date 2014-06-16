package app.jaid.devrays.screen.ingame;

import app.jaid.devrays.Core;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class IngameScreen implements Screen {

	SpriteBatch	batch;
	Texture		ship;

	@Override
	public void dispose()
	{
	}

	@Override
	public void hide()
	{
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void render(float delta)
	{
		batch.begin();
		batch.draw(ship, 5, 5, 64, 32);
		batch.end();
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
		batch = new SpriteBatch();
		ship = Core.getSprite("ship");
	}

	public void update()
	{

	}

}

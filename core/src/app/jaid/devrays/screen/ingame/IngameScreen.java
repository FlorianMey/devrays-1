package app.jaid.devrays.screen.ingame;

import app.jaid.devrays.Core;
import app.jaid.devrays.entity.Player;
import app.jaid.devrays.math.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class IngameScreen implements Screen {

	Player	player;

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
		update();

		Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		Core.batch.begin();
		player.render();
		Core.batch.end();
	}

	@Override
	public void resize(int width, int height)
	{
		Core.resize(width, height);
	}

	@Override
	public void resume()
	{
	}

	@Override
	public void show()
	{
		player = new Player(new Point());
		player.sprite = new Sprite(Core.getSprite("ship"));
	}

	public void update()
	{
		player.update();
	}

}

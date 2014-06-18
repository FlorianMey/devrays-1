package app.jaid.devrays.screen.ingame;

import app.jaid.devrays.Core;
import app.jaid.devrays.entity.Player;
import app.jaid.devrays.math.Point;
import app.jaid.devrays.screen.DevraysScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;

public class IngameScreen implements DevraysScreen {

	OrthographicCamera	camera;
	public float		cameraHeight	= 12;
	public float		cameraWidth;
	Player				player;

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
		Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		player.render();
	}

	@Override
	public void resize(int width, int height)
	{
		updateCamera(cameraHeight);
	}

	@Override
	public void resume()
	{
	}

	@Override
	public void show()
	{
		camera = new OrthographicCamera();
		updateCamera(16);

		player = new Player(new Point());
		player.sprite = new Sprite(Core.getSprite("ship"));
	}

	@Override
	public void update()
	{
		player.update();
	}

	public void updateCamera(float cameraHeight)
	{
		this.cameraHeight = cameraHeight;
		cameraWidth = cameraHeight * Core.screenWidth / Core.screenHeight;
		camera.viewportWidth = cameraWidth;
		camera.viewportHeight = cameraHeight;
		camera.update();
		Core.getBatch().setProjectionMatrix(camera.combined);
	}
}
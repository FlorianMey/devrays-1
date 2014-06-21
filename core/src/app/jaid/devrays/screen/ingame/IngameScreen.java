package app.jaid.devrays.screen.ingame;

import app.jaid.devrays.Core;
import app.jaid.devrays.entity.Player;
import app.jaid.devrays.math.Point;
import app.jaid.devrays.screen.DevraysScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class IngameScreen implements DevraysScreen {

	private static IngameScreen	instance;

	public static Environment getEnvironment()
	{
		return getInstance().environment;
	}

	public static IngameScreen getInstance()
	{
		return instance;
	}

	OrthographicCamera	camera;
	public float		cameraHeight	= 12;
	public float		cameraWidth;
	private Environment	environment;
	Player				player;

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
		player.render();
		environment.render();
	}

	@Override
	public void renderText()
	{
		Core.testfont.setMarkupEnabled(true);
		Core.testfont.draw(Core.getUiBatch(), "[WHITE]D[RED]evrays [WHITE]F[RED]ont[WHITE]test.", 200, 200);
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
		instance = this;
		environment = new Environment();
		camera = new OrthographicCamera();
		updateCamera(16);

		player = new Player(new Point());
		player.sprite = new Sprite(Core.getSprite("ship"));
	}

	@Override
	public void update()
	{
		player.update();
		environment.update();
	}

	private void updateCamera(float cameraHeight)
	{
		this.cameraHeight = cameraHeight;
		cameraWidth = cameraHeight * Core.screenWidth / Core.screenHeight;
		camera.viewportWidth = cameraWidth;
		camera.viewportHeight = cameraHeight;
		camera.update();
		Core.getBatch().setProjectionMatrix(camera.combined);
	}
}
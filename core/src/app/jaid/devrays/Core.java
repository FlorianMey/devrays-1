package app.jaid.devrays;

import app.jaid.devrays.input.InputCore;
import app.jaid.devrays.io.Media;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class Core {
	public static boolean				debug;
	public static float					delta;
	private static OrthographicCamera	hudCamera;
	public static int					screenWidth, screenHeight;
	public static float					speed;
	public static long					startTime, now;
	private static Stage				worldStage;				// hudStage;

	public static Camera getCamera()
	{
		return worldStage.getCamera();
	}

	/*
	 * public static Batch getHudBatch() { return hudStage.getBatch(); }
	 * 
	 * public static Stage getHudStage() { return hudStage; }
	 */

	public static Camera getHudCamera()
	{
		return hudCamera;
	}

	public static int getRuntime()
	{
		return (int) (now - startTime);
	}

	public static Batch getWorldBatch()
	{
		return worldStage.getBatch();
	}

	static void init()
	{
		startTime = TimeUtils.millis();

		Media.addAtlas((TextureAtlas) Media.get("textures/world.atlas"));
		Media.play = (BitmapFont) Media.get("fonts/play.fnt");
		Media.play.setMarkupEnabled(true);

		worldStage = new Stage(new ExtendViewport(24, 16));
		// hudStage = new Stage(new ScreenViewport(), worldStage.getBatch());
		hudCamera = new OrthographicCamera(Core.screenWidth, Core.screenHeight);
		hudCamera.setToOrtho(false, Core.screenWidth, Core.screenHeight);

		speed = 1;
	}

	public static void resize(int width, int height)
	{
		screenWidth = width;
		screenHeight = height;
		worldStage.getViewport().update(screenWidth, screenHeight);
		hudCamera.setToOrtho(false, Core.screenWidth, Core.screenHeight);
	}

	public static void tick()
	{
		delta = Gdx.graphics.getDeltaTime() * speed;
		now = TimeUtils.millis();
		InputCore.updateCursor(Gdx.input.getX(), Gdx.input.getY());
	}

	public static void zoom(float change)
	{
		worldStage.getViewport().setWorldSize(worldStage.getViewport().getWorldWidth() + change, worldStage.getViewport().getWorldHeight() + change);
	}
}

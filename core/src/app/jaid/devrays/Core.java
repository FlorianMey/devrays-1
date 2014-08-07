package app.jaid.devrays;

import app.jaid.devrays.debug.CommandExecutor;
import app.jaid.devrays.debug.Log;
import app.jaid.devrays.graphics.Drawer;
import app.jaid.devrays.input.InputCore;
import app.jaid.devrays.input.InputManager;
import app.jaid.devrays.io.Media;
import app.jaid.devrays.io.SystemIO;
import app.jaid.devrays.ui.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.*;

/**
 * Contains static fields and methods that get accessed from many classes, such as util methods, profiling results and
 * camera controlling.
 *
 * @author jaid
 */
public class Core {
	private static Batch batch;
	private static FileHandle dataFolder;
	public static boolean debug;
	public static float delta, deltaPeak;
	private static Json json = new Json();
	public static int screenWidth, screenHeight;
	public static float speed = 1;
	public static long startTime, now;
	private static SystemIO systemIo = new SystemIO();
	private static OrthographicCamera worldCamera, hudCamera;
	private static Stage worldStage, hudStage;
	private static Viewport worldViewport, hudViewport;

	public static Batch getBatch()
	{
		return batch;
	}

	public static FileHandle getDataFile(String fileName)
	{
		return dataFolder.child(fileName);
	}

	public static float getDeltaPeak()
	{
		return deltaPeak;
	}

	public static Camera getHudCamera()
	{
		return hudCamera;
	}

	public static Stage getHudStage()
	{
		return hudStage;
	}

	public static Viewport getHudViewport()
	{
		return hudViewport;
	}

	public static Json getJson()
	{
		return json;
	}

	public static int getRuntime()
	{
		return (int) (now - startTime);
	}

	public static Camera getWorldCamera()
	{
		return worldCamera;
	}

	public static Stage getWorldStage()
	{
		return worldStage;
	}

	public static Viewport getWorldViewport()
	{
		return worldViewport;
	}

	static void init()
	{
		startTime = TimeUtils.millis();
		Log.registerPrinter(systemIo);

		dataFolder = Gdx.files.external("devrays_data");
		if (!dataFolder.exists())
			dataFolder.file().mkdir();

		Media.addAtlas((TextureAtlas) Media.get("textures/world.atlas"));
		Media.play = (BitmapFont) Media.get("fonts/play.fnt");
		Media.play.setMarkupEnabled(true);

		batch = new SpriteBatch(1024);
		worldCamera = new OrthographicCamera();
		worldViewport = new ExtendViewport(24, 16, worldCamera);
		worldStage = new Stage(worldViewport, batch);

		hudCamera = new OrthographicCamera();
		hudViewport = new ScreenViewport(hudCamera);
		hudStage = new Stage(hudViewport, batch);

		Gdx.input.setInputProcessor(new InputManager());
		Hud.getConsole().init();

		CommandExecutor.run("run init.devrcmd -internal");
	}

	public static void resize(int width, int height)
	{
		screenWidth = width;
		screenHeight = height;

		worldViewport.update(screenWidth, screenHeight, true);
		hudViewport.update(screenWidth, screenHeight, true);
		Drawer.getShapeRenderer().setProjectionMatrix(hudCamera.combined);
	}

	public static void update()
	{
		delta = Gdx.graphics.getDeltaTime() * speed;
		if (Gdx.graphics.getRawDeltaTime() > deltaPeak)
			deltaPeak = Gdx.graphics.getRawDeltaTime();

		now = TimeUtils.millis();
		InputCore.update();
	}

	public static void zoom(float change)
	{
		// worldViewport.setWorldSize(worldViewport.getWorldWidth() + change, worldViewport.getWorldHeight() + change);
	}
}

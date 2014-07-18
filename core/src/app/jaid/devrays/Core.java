package app.jaid.devrays;

import app.jaid.devrays.debug.Log;
import app.jaid.devrays.debug.Shell;
import app.jaid.devrays.input.InputCore;
import app.jaid.devrays.input.InputManager;
import app.jaid.devrays.io.Media;
import app.jaid.devrays.io.SystemIO;
import app.jaid.devrays.ui.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Contains static fields and methods that get accessed from many classes, such as util methods, profiling results and
 * camera controlling.
 *
 * @author jaid
 */
public class Core {
	public static boolean debug;
	public static float delta, deltaPeak;
	private static OrthographicCamera hudCamera;
	private static Json json = new Json();
	public static int screenWidth, screenHeight;
	public static float speed = 1;
	public static long startTime, now;
	private static SystemIO systemIo = new SystemIO();
	private static Stage worldStage, hudStage;

	public static Batch getBatch()
	{
		return worldStage.getBatch();
	}

	public static Camera getCamera()
	{
		return worldStage.getCamera();
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

	public static Json getJson()
	{
		return json;
	}

	public static int getRuntime()
	{
		return (int) (now - startTime);
	}

	static void init()
	{
		startTime = TimeUtils.millis();
		Log.registerPrinter(systemIo);
		Shell.setCommandLib(Gdx.files.internal("meta/commands.json"));
		Shell.setShortcuts(Gdx.files.internal("meta/shortcuts.json"));

		Media.addAtlas((TextureAtlas) Media.get("textures/world.atlas"));
		Media.play = (BitmapFont) Media.get("fonts/play.fnt");
		Media.play.setMarkupEnabled(true);

		worldStage = new Stage(new ExtendViewport(24, 16));

		hudStage = new Stage(new ScreenViewport(), worldStage.getBatch());
		hudCamera = new OrthographicCamera(Core.screenWidth, Core.screenHeight);
		hudCamera.setToOrtho(false, Core.screenWidth, Core.screenHeight);
		Gdx.input.setInputProcessor(new InputManager());
		Hud.getConsole().init();
	}

	public static void resize(int width, int height)
	{
		screenWidth = width;
		screenHeight = height;
		worldStage.getViewport().update(screenWidth, screenHeight);
		hudStage.getViewport().update(screenWidth, screenHeight, true);
	}

	public static void tick()
	{
		delta = Gdx.graphics.getDeltaTime() * speed;
		if (Gdx.graphics.getRawDeltaTime() > deltaPeak)
			deltaPeak = Gdx.graphics.getRawDeltaTime();

		now = TimeUtils.millis();
		InputCore.update();
	}

	public static void zoom(float change)
	{
		worldStage.getViewport().setWorldSize(worldStage.getViewport().getWorldWidth() + change, worldStage.getViewport().getWorldHeight() + change);
	}
}

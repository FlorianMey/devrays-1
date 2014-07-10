package app.jaid.devrays;

import app.jaid.devrays.io.Media;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.TimeUtils;

public class Core {
	public static boolean		debug;
	public static float			delta;
	public static int			screenWidth, screenHeight;
	public static float			speed;
	private static Stage		stage;
	public static long			startTime, now;
	private static SpriteBatch	uiSpriteBatch;

	public static Batch getBatch()
	{
		return stage.getBatch();
	}

	public static Camera getCamera()
	{
		return stage.getCamera();
	}

	public static int getRuntimeMs()
	{
		return (int) (now - startTime);
	}

	public static Batch getUiBatch()
	{
		return uiSpriteBatch;
	}

	static void init()
	{
		startTime = TimeUtils.millis();

		Media.addAtlas((TextureAtlas) Media.get("textures/world.atlas"));
		Media.testfont = (BitmapFont) Media.get("fonts/testfont.fnt");
		Media.testfont.setMarkupEnabled(true);

		stage = new Stage();
		uiSpriteBatch = new SpriteBatch();

		speed = 1;
	}

	public static void resize(int width, int height)
	{
		screenWidth = width;
		screenHeight = height;
	}

	public static void tick()
	{
		delta = Gdx.graphics.getDeltaTime() * speed;
		now = TimeUtils.millis();
	}
}

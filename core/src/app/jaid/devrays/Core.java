package app.jaid.devrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.TimeUtils;

public class Core {
	public static AssetManager	assetManager;
	public static boolean		debug;
	public static float			delta;
	public static int			screenWidth, screenHeight;
	public static float			speed;
	private static Stage		stage;
	public static long			startTime, now;
	public static BitmapFont	testfont;
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

	public static Texture getSprite(String filename)
	{
		if (!filename.startsWith("images/"))
			filename = "images/" + filename;

		if (!filename.endsWith(".png"))
			filename += ".png";

		if (!assetManager.isLoaded(filename))
		{
			assetManager.load(filename, Texture.class);
			assetManager.finishLoading();
		}

		return assetManager.get(filename);
	}

	public static Batch getUiBatch()
	{
		return uiSpriteBatch;
	}

	static void init()
	{
		startTime = TimeUtils.millis();

		stage = new Stage();
		uiSpriteBatch = new SpriteBatch();

		assetManager = new AssetManager();
		assetManager.load("fonts/testfont.fnt", BitmapFont.class);
		assetManager.finishLoading();
		testfont = assetManager.get("fonts/testfont.fnt", BitmapFont.class);
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

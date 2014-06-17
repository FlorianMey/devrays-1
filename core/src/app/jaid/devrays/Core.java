package app.jaid.devrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class Core {
	public static AssetManager	assetManager;
	public static SpriteBatch	batch;
	public static boolean		debug;
	public static float			delta;
	public static int			screenWidth, screenHeight;
	public static float			speed;
	public static long			time;

	public static Texture getSprite(String filename)
	{
		if (!filename.endsWith(".png"))
			filename += ".png";

		if (!assetManager.isLoaded(filename))
		{
			assetManager.load(filename, Texture.class);
			assetManager.finishLoading();
		}

		return assetManager.get(filename);
	}

	static void init()
	{
		batch = new SpriteBatch();
		assetManager = new AssetManager();
		speed = 1;
	}

	public static void tick()
	{
		delta = Gdx.graphics.getDeltaTime() * speed;
		time = TimeUtils.millis();
	}

}

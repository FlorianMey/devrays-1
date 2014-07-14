package app.jaid.devrays.io;

import java.util.HashMap;

import app.jaid.devrays.debug.Log;
import app.jaid.jtil.JFiles;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Media {

	private static final AssetManager assetManager = new AssetManager();
	private static final HashMap<String, Class<?>> extensions;
	private static HashMap<String, TextureRegion> index = new HashMap<String, TextureRegion>();
	public static BitmapFont play;

	static
	{
		extensions = new HashMap<String, Class<?>>();
		extensions.put("png", Texture.class);
		extensions.put("jpg", Texture.class);
		// extensions.put("jpeg", Texture.class); Rather make sure all nontransparent textures are .jpg
		extensions.put("atlas", TextureAtlas.class);
		extensions.put("mp3", Music.class);
		extensions.put("fnt", BitmapFont.class);
		extensions.put("json", Skin.class);
	}

	public static void addAtlas(TextureAtlas atlas)
	{
		for (AtlasRegion region : atlas.getRegions())
			index.put(region.name, region);
	}

	public static Object get(String fileName)
	{
		if (!assetManager.isLoaded(fileName))
		{
			Log.debug("Resource atlas/" + fileName + " loaded.");
			load(fileName);
			assetManager.finishLoading();
		}

		return assetManager.get(fileName);
	}

	public static TextureRegion getSprite(String name)
	{
		TextureRegion texture = index.get(name);
		return texture != null ? texture : index.get("none");
	}

	private static Class<?> guessTypeByFileName(String fileName)
	{
		return extensions.get(JFiles.getExtension(fileName));
	}

	private static void load(String fileName)
	{
		assetManager.load(fileName, guessTypeByFileName(fileName));
	}

	public static void unload(String fileName)
	{
		assetManager.unload(fileName);
	}
}

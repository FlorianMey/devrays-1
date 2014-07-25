package app.jaid.devrays.io;

import java.util.HashMap;

import app.jaid.devrays.Core;
import app.jaid.devrays.debug.Log;
import app.jaid.jtil.JFiles;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

/**
 * Wrapper for GDX AssetManager that loads resources and provides access to them in static methods.
 *
 * @author jaid
 */
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

	@SuppressWarnings("unchecked")
	public static <T> Array<T> loadJsonArray(Class<T> type, FileHandle... files)
	{
		Array<T> descriptors = new Array<T>(128);
		Class<?> arrayClass = java.lang.reflect.Array.newInstance(type, 0).getClass();

		for (FileHandle file : files)
			if (file.exists())
				descriptors.addAll((T[]) Core.getJson().fromJson(arrayClass, file));

		Log.debug("Loaded " + descriptors.size + " " + type.getSimpleName() + " objects from " + files.length + " JSON file" + (files.length != 1 ? "s" : "") + ".");
		return descriptors;
	}

	public static void unload(String fileName)
	{
		assetManager.unload(fileName);
	}
}

package app.jaid.devrays.graphics;

import app.jaid.devrays.debug.DebugFlags;
import app.jaid.devrays.debug.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class Gfx {

	public static final ShaderProgram HUD_SHADER = new ShaderProgram(Gdx.files.internal("glsl/default.vert"), Gdx.files.internal("glsl/hudnoise.frag"));

	static
	{
		if (DebugFlags.debugMode)
			ShaderProgram.pedantic = false;

		Log.debug(HUD_SHADER.isCompiled() + HUD_SHADER.getLog());
	}

}

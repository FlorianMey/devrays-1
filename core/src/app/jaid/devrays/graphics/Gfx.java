package app.jaid.devrays.graphics;

import app.jaid.devrays.Core;
import app.jaid.devrays.debug.DebugFlags;
import app.jaid.devrays.debug.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Interpolation;

public class Gfx {

	public static final ShaderProgram DEFAULT_SHADER;
	private static final long HUD_DAMAGE_DISPLAY_TIME = 500;
	public static final ShaderProgram HUD_SHADER;
	private static float hudDamageAngle, hudInitialDamageStrength;
	private static long lastHudDamageStrengthSet;
	private static FileHandle shaderFolder;

	static
	{
		ShaderProgram.pedantic = false;
		shaderFolder = DebugFlags.debugMode ? Gdx.files.internal("glsl") : Gdx.files.internal("glsl/opt");

		DEFAULT_SHADER = getShader(null, null);
		HUD_SHADER = getShader(null, shaderFolder.child("hud.frag").readString());
	}

	public static float getHudStrength()
	{
		long timeSinceSet = Core.now - lastHudDamageStrengthSet;

		if (timeSinceSet > HUD_DAMAGE_DISPLAY_TIME)
			return 0;

		return Interpolation.circle.apply(0, hudInitialDamageStrength, (HUD_DAMAGE_DISPLAY_TIME - timeSinceSet) / (float) HUD_DAMAGE_DISPLAY_TIME);
	}

	private static ShaderProgram getShader(String vertexSource, String fragmentSource)
	{
		ShaderProgram shader = new ShaderProgram(vertexSource != null ? vertexSource : shaderFolder.child("default.vert").readString(), fragmentSource != null ? fragmentSource : shaderFolder.child("default.frag").readString());

		if (!shader.isCompiled())
		{
			Log.exception("Could not compile shader.\n" + HUD_SHADER.getLog());
			return DEFAULT_SHADER;
		}

		return shader;

	}

	public static void setHudAngle(float hudDamageAngle)
	{
		Gfx.hudDamageAngle = hudDamageAngle;
	}

	public static void setHudStrength(float hudInitialDamageStrength)
	{
		Gfx.hudInitialDamageStrength = hudInitialDamageStrength;
		lastHudDamageStrengthSet = Core.now;
	}

	public static void updateHudShader()
	{
		Gfx.HUD_SHADER.setUniformf("u_resolution", Core.screenWidth, Core.screenHeight);
		Gfx.HUD_SHADER.setUniformf("u_screen_center", Core.screenWidth / 2f, Core.screenHeight / 2f);
		Gfx.HUD_SHADER.setUniformf("u_runtime", Core.getRuntime());
		Gfx.HUD_SHADER.setUniformf("u_damage_angle", Gfx.hudDamageAngle);
		Gfx.HUD_SHADER.setUniformf("u_damage_strength", getHudStrength());
	}

}

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

		DEFAULT_SHADER = new ShaderProgram(shaderFolder.child("default.vert"), shaderFolder.child("default.frag"));
		HUD_SHADER = new ShaderProgram(shaderFolder.child("default.vert"), shaderFolder.child("hud.frag"));

		Log.debug(HUD_SHADER.isCompiled() ? "HUD shader compiled." : "HUD shader compilation failed.\n" + HUD_SHADER.getLog());
	}

	public static float getHudStrength()
	{
		long timeSinceSet = Core.now - lastHudDamageStrengthSet;

		if (timeSinceSet > HUD_DAMAGE_DISPLAY_TIME)
			return 0;

		return Interpolation.circle.apply(0, hudInitialDamageStrength, (HUD_DAMAGE_DISPLAY_TIME - timeSinceSet) / (float) HUD_DAMAGE_DISPLAY_TIME);
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

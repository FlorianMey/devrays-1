package app.jaid.devrays;

import app.jaid.devrays.debug.DebugFlags;
import app.jaid.devrays.debug.Stats;
import app.jaid.devrays.graphics.Drawer;
import app.jaid.devrays.graphics.Gfx;
import app.jaid.devrays.io.SystemIO;
import app.jaid.devrays.screen.DevraysScreen;
import app.jaid.devrays.screen.ingame.IngameScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Gets instantiated by the platform specific launcher. Invokes inits in {@link #create}, provides main loop
 * {@link #render} and manages screens ({@link DevraysScreen}).
 *
 * @author jaid
 */
public class DevraysGame extends Game {

	private static DevraysScreen currentScreen;
	private static FrameBuffer hudFbo;

	public static DevraysScreen getDevraysScreen()
	{
		return currentScreen;
	}

	@Override
	public void create()
	{
		Core.init();
		setDevraysScreen(new IngameScreen());
		GLProfiler.enable();
	}

	@Override
	public void render()
	{
		// Update routines

		Core.tick();
		SystemIO.readInput();
		Core.getHudStage().act();
		currentScreen.update();

		// World rendering

		Core.getBatch().setProjectionMatrix(Core.getCamera().combined);
		Core.getBatch().begin();
		Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		super.render();
		Core.getBatch().end();

		// Shape rendering

		Drawer.getShapeRenderer().begin(ShapeType.Line);
		currentScreen.renderShapes();
		Drawer.getShapeRenderer().end();

		// HUD rendering to FBO

		hudFbo.begin();
		Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		Core.getHudStage().draw();
		hudFbo.end();

		// FBO rendering to screen (hudnoise.frag applied)

		Core.getBatch().setShader(Gfx.HUD_SHADER);
		Core.getBatch().begin();
		Gfx.HUD_SHADER.setUniformf("u_runtime", Core.getRuntime());
		Gfx.HUD_SHADER.setUniformf("u_resolution", Core.screenWidth, Core.screenHeight);
		Core.getBatch().draw(hudFbo.getColorBufferTexture(), 0, 0);
		Core.getBatch().end();

		Core.getBatch().setShader(null);

		if (DebugFlags.debugMode)
			Table.drawDebug(Core.getHudStage());

		// Text rendering

		Core.getBatch().begin();
		Stats.render();
		currentScreen.renderText();
		Core.getBatch().end();

		// Finishing frame

		GLProfiler.reset();
	}

	@Override
	public void resize(int width, int height)
	{
		hudFbo = new FrameBuffer(Format.RGBA8888, width, height, false);
		Core.resize(width, height);
		super.resize(width, height);
	}

	public void setDevraysScreen(DevraysScreen screen)
	{
		setScreen(screen);
		currentScreen = screen;
	}
}

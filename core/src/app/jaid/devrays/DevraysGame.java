package app.jaid.devrays;

import app.jaid.devrays.debug.Shell;
import app.jaid.devrays.debug.Stats;
import app.jaid.devrays.graphics.Drawer;
import app.jaid.devrays.screen.DevraysScreen;
import app.jaid.devrays.screen.ingame.IngameScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class DevraysGame extends Game {

	private static DevraysScreen currentScreen;

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
		Core.tick();
		Shell.readInput();
		currentScreen.update();

		Core.getBatch().setProjectionMatrix(Core.getCamera().combined);
		Core.getBatch().begin();
		Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		super.render();
		Core.getBatch().end();

		Drawer.getShapeRenderer().begin(ShapeType.Line);
		currentScreen.renderShapes();
		Drawer.getShapeRenderer().end();

		Core.getHudStage().act();
		Core.getHudStage().draw();
		Table.drawDebug(Core.getHudStage());

		Core.getBatch().begin();
		Stats.render();
		currentScreen.renderText();
		Core.getBatch().end();

		GLProfiler.reset();
	}

	@Override
	public void resize(int width, int height)
	{
		Core.resize(width, height);
		super.resize(width, height);
	}

	public void setDevraysScreen(DevraysScreen screen)
	{
		setScreen(screen);
		currentScreen = screen;
	}
}

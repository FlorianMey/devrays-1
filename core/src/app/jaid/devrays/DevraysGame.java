package app.jaid.devrays;

import app.jaid.devrays.debug.Stats;
import app.jaid.devrays.graphics.Drawer;
import app.jaid.devrays.screen.DevraysScreen;
import app.jaid.devrays.screen.ingame.IngameScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.profiling.GLProfiler;

public class DevraysGame extends Game {

	@Override
	public void create()
	{
		Core.init();
		setScreen(new IngameScreen());
		GLProfiler.enable();
	}

	@Override
	public void render()
	{
		Core.tick();

		DevraysScreen currentDevraysScreen = (DevraysScreen) getScreen();
		currentDevraysScreen.update();

		Core.getWorldBatch().setProjectionMatrix(Core.getCamera().combined);

		Core.getWorldBatch().begin();
		Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		super.render();
		Core.getWorldBatch().end();

		Drawer.getShapeRenderer().begin(ShapeType.Line);
		currentDevraysScreen.renderShapes();
		Drawer.getShapeRenderer().end();

		Core.getWorldBatch().setProjectionMatrix(Core.getHudCamera().combined);

		Core.getWorldBatch().begin();
		currentDevraysScreen.renderText();
		Stats.render();
		Core.getWorldBatch().end();

		Stats.trackCommons();
		GLProfiler.reset();
	}

	@Override
	public void resize(int width, int height)
	{
		Core.resize(width, height);
		super.resize(width, height);
	}
}

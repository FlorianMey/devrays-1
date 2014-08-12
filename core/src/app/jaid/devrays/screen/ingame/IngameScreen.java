package app.jaid.devrays.screen.ingame;

import app.jaid.devrays.entity.Monster;
import app.jaid.devrays.entity.MonsterDescriptor;
import app.jaid.devrays.geo.Point;
import app.jaid.devrays.mobs.Player;
import app.jaid.devrays.screen.DevraysScreen;
import app.jaid.devrays.ui.ArsenalBox;
import app.jaid.devrays.ui.Hud;
import app.jaid.jtil.JRand;

/**
 * Most important Screen of the game, contains an {@link Environment} and will simulate / render the game world.
 *
 * @author jaid
 */
public class IngameScreen implements DevraysScreen {

	private static IngameScreen instance;

	public static Environment getEnvironment()
	{
		return instance.environment;
	}

	public static IngameScreen getInstance()
	{
		return instance;
	}

	private Environment environment;

	public IngameScreen()
	{
		instance = this;
		environment = new Environment();
		environment.addPlayer(new Player(new Point()));
		environment.getMobs().add(environment.getPlayer());

		Hud.get().getArsenalBoxCell().setActor(new ArsenalBox(environment.getPlayer().getArsenal(), 0));

		environment.getMobs().add(Monster.create(environment.getPlayer().getPosition().add(JRand.vary(20, 10), JRand.vary(20, 10)), MonsterDescriptor.getById("varock")));
		// for (int i = 0; i != 5; i++)
		// environment.getMobs().add(Monster.create(environment.getPlayer().getPosition().add(JRand.vary(20, 10),
		// JRand.vary(20, 10)), MonsterDescriptor.getById("hareye")));
	}

	@Override
	public void dispose()
	{
	}

	@Override
	public void hide()
	{
		instance = null;
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void render(float delta)
	{
		environment.render();
	}

	@Override
	public void renderShapes()
	{
		environment.renderShapes();
	}

	@Override
	public void renderText()
	{
		environment.renderText();
	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void resume()
	{
	}

	@Override
	public void show()
	{
	}

	@Override
	public void update()
	{
		environment.update();
	}
}